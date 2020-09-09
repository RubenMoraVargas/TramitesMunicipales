package org.una.tramites.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.una.tramites.dtos.UsuarioDTO;
import org.una.tramites.services.IUsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;

@RestController
@RequestMapping("/usuarios")
@Api(tags = {"Usuarios"}) 
    public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping("/")
    @ApiOperation(value = "Obtiene una lista de todos los Usuarios", response = UsuarioDTO.class, responseContainer = "List", tags = "Usuarios")
    @PreAuthorize("hasAuthority('USUARIO_CONSULTAR_TODO')")
    public ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity(usuarioService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USUARIO_CONSULTAR')")
    @ApiOperation(value = "Obtiene un Usuario por su Id", response = UsuarioDTO.class, tags = "Usuarios")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(usuarioService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cedula/{termino}")
    @ApiOperation(value = "Obtiene una lista de Usuarios por cédula", response = UsuarioDTO.class, responseContainer = "List", tags = "Usuarios")
    @PreAuthorize("hasAuthority('USUARIO_CONSULTAR')")
    public ResponseEntity<?> findByCedulaAproximate(@PathVariable(value = "termino") String term) {
        try {
            return new ResponseEntity(usuarioService.findByCedulaAproximate(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/nombre/{termino}")
    @ApiOperation(value = "Obtiene una lista de Usuarios por nombre completo", response = UsuarioDTO.class, responseContainer = "List", tags = "Usuarios")
    @PreAuthorize("hasAuthority('USUARIO_CONSULTAR')")
    public ResponseEntity<?> findByNombreCompletoAproximateIgnoreCase(@PathVariable(value = "termino") String term) {
        try {
            return new ResponseEntity(usuarioService.findByNombreCompletoAproximateIgnoreCase(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/departamento/{id}")
    @ApiOperation(value = "Obtiene una lista de Usuarios por Id del Departamento", response = UsuarioDTO.class, responseContainer = "List", tags = "Usuarios")
    @PreAuthorize("hasAuthority('USUARIO_CONSULTAR')")
    public ResponseEntity<?> findByDepartamentoId(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(usuarioService.findByDepartamentoId(id), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    @ApiOperation(value = "Permite crear un Usuario", response = UsuarioDTO.class, tags = "Usuarios")
    @PreAuthorize("hasAuthority('USUARIO_CREAR')")
    public ResponseEntity<?> create(@Valid @RequestBody UsuarioDTO usuarioDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(usuarioService.create(usuarioDTO), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Permite modificar un Usuario a partir de su Id", response = UsuarioDTO.class, tags = "Usuarios")
    @PreAuthorize("hasAuthority('USUARIO_MODIFICAR')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody UsuarioDTO usuarioDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<UsuarioDTO> usuarioUpdated = usuarioService.update(usuarioDTO, id);
                if (usuarioUpdated.isPresent()) {
                    return new ResponseEntity(usuarioUpdated, HttpStatus.OK);
                } else {
                    return new ResponseEntity(HttpStatus.NOT_FOUND);
                }
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Permite eliminar un Usuario a partir de su Id", response = UsuarioDTO.class, tags = "Usuarios")
    @PreAuthorize("hasAuthority('USUARIO_ELIMINAR')")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            usuarioService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    @ApiOperation(value = "Permite eliminar todos los Usuarios", response = UsuarioDTO.class, tags = "Usuarios")
    @PreAuthorize("hasAuthority('USUARIO_ELIMINAR_TODO')")
    public ResponseEntity<?> deleteAll() {
        try {
            usuarioService.deleteAll();
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
