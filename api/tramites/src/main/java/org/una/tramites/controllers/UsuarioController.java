package org.una.tramites.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.una.tramites.dtos.UsuarioDTO;
import org.una.tramites.entities.Usuario;
import org.una.tramites.services.IUsuarioService;
import org.una.tramites.utils.MapperUtils;

@RestController
@RequestMapping("/usuarios")
@Api(tags = {"Usuarios"})
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("/")
    @ApiOperation(value = "Obtiene una lista de todos los Usuarios", response = UsuarioDTO.class, responseContainer = "List", tags = "Usuarios")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<Usuario>> result = usuarioService.findAll();
            if (result.isPresent()) {
                List<UsuarioDTO> usuariosDTO = MapperUtils.DtoListFromEntityList(result.get(), UsuarioDTO.class);
                return new ResponseEntity<>(usuariosDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un Usuario por su Id", response = UsuarioDTO.class, tags = "Usuarios")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<Usuario> usuarioFound = usuarioService.findById(id);
            if (usuarioFound.isPresent()) {
                UsuarioDTO usuarioDto = MapperUtils.DtoFromEntity(usuarioFound.get(), UsuarioDTO.class);
                return new ResponseEntity<>(usuarioDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/login")
    @ResponseBody
    @ApiOperation(value = "Inicio de sesión para conseguir un token de acceso", response = UsuarioDTO.class, tags = "Seguridad")
    public ResponseEntity<?> login(@PathVariable(value = "cedula") String cedula, @PathVariable(value = "password") String password) {
        try {
            Usuario usuario = new Usuario();
            usuario.setCedula(cedula);
            usuario.setPasswordEncriptado(password);
            Optional<Usuario> usuarioFound = usuarioService.login(usuario);
            if (usuarioFound.isPresent()) {
                UsuarioDTO usuarioDto = MapperUtils.DtoFromEntity(usuarioFound.get(), UsuarioDTO.class);
                return new ResponseEntity<>(usuarioDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/cedula/{termino}")
    @ApiOperation(value = "Obtiene una lista de Usuarios por cédula", response = UsuarioDTO.class, responseContainer = "List", tags = "Usuarios")
    public ResponseEntity<?> findByCedulaAproximate(@PathVariable(value = "termino") String term) {
        try {
            Optional<List<Usuario>> result = usuarioService.findByCedulaAproximate(term);
            if (result.isPresent()) {
                List<UsuarioDTO> usuariosDTO = MapperUtils.DtoListFromEntityList(result.get(), UsuarioDTO.class);
                return new ResponseEntity<>(usuariosDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/nombre/{termino}")
    @ApiOperation(value = "Obtiene una lista de Usuarios por nombre completo", response = UsuarioDTO.class, responseContainer = "List", tags = "Usuarios")
    public ResponseEntity<?> findByNombreCompletoAproximateIgnoreCase(@PathVariable(value = "termino") String term) {
        try {
            Optional<List<Usuario>> result = usuarioService.findByNombreCompletoAproximateIgnoreCase(term);
            if (result.isPresent()) {
                List<UsuarioDTO> usuariosDTO = MapperUtils.DtoListFromEntityList(result.get(), UsuarioDTO.class);
                return new ResponseEntity<>(usuariosDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ApiOperation(value = "Permite crear un Usuario", response = UsuarioDTO.class, tags = "Usuarios")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody Usuario usuario) {
        try {
            Usuario usuarioCreated = usuarioService.create(usuario);
            UsuarioDTO usuarioDto = MapperUtils.DtoFromEntity(usuarioCreated, UsuarioDTO.class);
            return new ResponseEntity<>(usuarioDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Permite modificar un Usuario a partir de su Id", response = UsuarioDTO.class, tags = "Usuarios")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody Usuario usuarioModified) {
        try {
            Optional<Usuario> usuarioUpdated = usuarioService.update(usuarioModified, id);
            if (usuarioUpdated.isPresent()) {
                UsuarioDTO usuarioDto = MapperUtils.DtoFromEntity(usuarioUpdated.get(), UsuarioDTO.class);
                return new ResponseEntity<>(usuarioDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Permite eliminar un Usuario a partir de su Id", response = UsuarioDTO.class, tags = "Usuarios")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            usuarioService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    @ApiOperation(value = "Permite eliminar todos los Usuarios", response = UsuarioDTO.class, tags = "Usuarios")
    public ResponseEntity<?> deleteAll() {
        try {
            usuarioService.deleteAll();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/departamento/jefe/{id}")
    @ApiOperation(value = "Obtiene el Usuario que sea jefe del Departamento correspondiente al Id", response = UsuarioDTO.class, tags = "Usuarios")
    public ResponseEntity<?> findJefeByDepartamento(@PathVariable(value = "id") Long id) {
        try {

            Optional<Usuario> usuarioFound = usuarioService.findJefeByDepartamento(id);
            if (usuarioFound.isPresent()) {
                UsuarioDTO usuarioDto = MapperUtils.DtoFromEntity(usuarioFound.get(), UsuarioDTO.class);
                return new ResponseEntity<>(usuarioDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/departamento/{id}")
    @ApiOperation(value = "Obtiene una lista de Usuarios por Id del Departamento", response = UsuarioDTO.class, responseContainer = "List", tags = "Usuarios")
    public ResponseEntity<?> findByDepartamentoId(@PathVariable(value = "id") Long id) {
        try {
            Optional<List<Usuario>> result = usuarioService.findByDepartamentoId(id);
            if (result.isPresent()) {
                List<UsuarioDTO> usuariosDTO = MapperUtils.DtoListFromEntityList(result.get(), UsuarioDTO.class);
                return new ResponseEntity<>(usuariosDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
