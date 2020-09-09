package org.una.tramites.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RestController;
import org.una.tramites.dtos.PermisoOtorgadoDTO;
import org.una.tramites.services.IPermisoOtorgadoService;

@RestController
@RequestMapping("/permisos-otorgados")
@Api(tags = {"Permisos"})
public class PermisoOtorgadoController {

    @Autowired
    private IPermisoOtorgadoService permisoOtorgadoService;

    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un PermisoOtorgado por su Id", response = PermisoOtorgadoDTO.class, tags = "Permisos")
    @PreAuthorize("hasAuthority('PERMISO_OTORGADO_CONSULTAR')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(permisoOtorgadoService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/usuario/{id}")
    @ApiOperation(value = "Obtiene una lista de  los PermisoOtorgados por Usuario", response = PermisoOtorgadoDTO.class, responseContainer = "List", tags = "Permisos")
    @PreAuthorize("hasAuthority('PERMISO_OTORGADO_CONSULTAR')")
    public ResponseEntity<?> findByUsuarioId(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(permisoOtorgadoService.findByUsuarioId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/permiso/{id}")
    @ApiOperation(value = "Obtiene una lista de  los PermisoOtorgados por Permiso", response = PermisoOtorgadoDTO.class, responseContainer = "List", tags = "Permisos")
    @PreAuthorize("hasAuthority('PERMISO_OTORGADO_CONSULTAR')")
    public ResponseEntity<?> findByPermisoId(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(permisoOtorgadoService.findByPermisoId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/usuario/{id}/estado/{estado}")
    @ApiOperation(value = "Obtiene una lista de los PermisoOtorgados por Usuario y estado", response = PermisoOtorgadoDTO.class, responseContainer = "List", tags = "Permisos")
    @PreAuthorize("hasAuthority('PERMISO_OTORGADO_CONSULTAR')")
    public ResponseEntity<?> findByUsuarioIdAndEstado(@PathVariable(value = "id") Long id, @PathVariable(value = "estado") boolean estado) {
        try {
            return new ResponseEntity(permisoOtorgadoService.findByUsuarioIdAndEstado(id, estado), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/permiso/{id}/estado/{estado}")
    @ApiOperation(value = "Obtiene una lista de los PermisoOtorgados por Permiso y estado", response = PermisoOtorgadoDTO.class, responseContainer = "List", tags = "Permisos")
    @PreAuthorize("hasAuthority('PERMISO_OTORGADO_CONSULTAR')")
    public ResponseEntity<?> findByPermisoIdAndEstado(@PathVariable(value = "id") Long id, @PathVariable(value = "estado") boolean estado) {

        try {
            return new ResponseEntity(permisoOtorgadoService.findByPermisoIdAndEstado(id, estado), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/fecha-registro/{inicio}/{fin}")
    @ApiOperation(value = "Obtiene una lista de los PermisoOtorgados por Fecha de registro", response = PermisoOtorgadoDTO.class, responseContainer = "List", tags = "Permisos")
    @PreAuthorize("hasAuthority('PERMISO_OTORGADO_CONSULTAR')")
    public ResponseEntity<?> findByFechaRegistroBetween(@PathVariable(value = "inicio") Date startDate, @PathVariable(value = "fin") Date endDate) {
        try {
            return new ResponseEntity(permisoOtorgadoService.findByFechaRegistroBetween(startDate, endDate), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/")
    @ApiOperation(value = "Permite crear un PermisoOtorgado", response = PermisoOtorgadoDTO.class, tags = "Permisos")
    @PreAuthorize("hasAuthority('PERMISO_OTORGADO_CREAR')")
    public ResponseEntity<?> create(@Valid @RequestBody PermisoOtorgadoDTO permisoOtorgadoDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(permisoOtorgadoService.create(permisoOtorgadoDTO), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Permite modificar un PermisoOtorgado a partir de su Id", response = PermisoOtorgadoDTO.class, tags = "Permisos")
    @PreAuthorize("hasAuthority('PERMISO_OTORGADO_MODIFICAR')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody PermisoOtorgadoDTO permisoOtorgadoDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<PermisoOtorgadoDTO> usuarioUpdated = permisoOtorgadoService.update(permisoOtorgadoDTO, id);
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
    @ApiOperation(value = "Permite eliminar un PermisoOtorgado a partir de su Id", response = PermisoOtorgadoDTO.class, tags = "Permisos")
    @PreAuthorize("hasAuthority('PERMISO_OTORGADO_ELIMINAR')")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            permisoOtorgadoService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    @ApiOperation(value = "Permite eliminar todos los PermisoOtorgados", response = PermisoOtorgadoDTO.class, tags = "Permisos")
    @PreAuthorize("hasAuthority('PERMISO_OTORGADO_ELIMINAR_TODOS')")
    public ResponseEntity<?> deleteAll() {
        try {
            permisoOtorgadoService.deleteAll();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
