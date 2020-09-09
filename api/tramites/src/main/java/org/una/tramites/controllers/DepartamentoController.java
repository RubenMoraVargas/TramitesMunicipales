package org.una.tramites.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.una.tramites.dtos.DepartamentoDTO;
import org.una.tramites.services.IDepartamentoService;

@RestController
@RequestMapping("/departamentos")
@Api(tags = {"Departamentos"}) 
    public class DepartamentoController {

    @Autowired
    private IDepartamentoService departamentoService;

    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping("/")
    @ApiOperation(value = "Obtiene una lista de todos los Departamentos", response = DepartamentoDTO.class, responseContainer = "List", tags = "Departamentos")
    @PreAuthorize("hasAuthority('DEPARTAMENTO_CONSULTAR')")
    public ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity(departamentoService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un Departamento por su Id", response = DepartamentoDTO.class, tags = "Departamentos")
    @PreAuthorize("hasAuthority('DEPARTAMENTO_CONSULTAR')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(departamentoService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/estado/{value}")
    @ApiOperation(value = "Obtiene una lista de los Departamentos por estado", response = DepartamentoDTO.class, responseContainer = "List", tags = "Departamentos")
    @PreAuthorize("hasAuthority('DEPARTAMENTO_CONSULTAR')")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "value") boolean value) {
        try {
            return new ResponseEntity(departamentoService.findByEstado(value), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        } 
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ApiOperation(value = "Permite crear un Departamento", response = DepartamentoDTO.class, tags = "Departamentos")
    @PreAuthorize("hasAuthority('DEPARTAMENTO_CREAR')")
    public ResponseEntity<?> create(@Valid @RequestBody DepartamentoDTO departamentoDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(departamentoService.create(departamentoDTO), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Permite modificar un Departamento a partir de su Id", response = DepartamentoDTO.class, tags = "Departamentos")
    @PreAuthorize("hasAuthority('DEPARTAMENTO_MODIFICAR')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody DepartamentoDTO departamentoDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<DepartamentoDTO> usuarioUpdated = departamentoService.update(departamentoDTO, id);
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
    @ApiOperation(value = "Permite eliminar un Departamento a partir de su Id", response = DepartamentoDTO.class, tags = "Departamentos")
    @PreAuthorize("hasAuthority('DEPARTAMENTO_ELIMINAR')")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            departamentoService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    @ApiOperation(value = "Permite eliminar todos los Departamentos", response = DepartamentoDTO.class, tags = "Departamentos")
    @PreAuthorize("hasAuthority('DEPARTAMENTO_ELIMINAR_TODOS')")
    public ResponseEntity<?> deleteAll() {
        try {
            departamentoService.deleteAll();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
