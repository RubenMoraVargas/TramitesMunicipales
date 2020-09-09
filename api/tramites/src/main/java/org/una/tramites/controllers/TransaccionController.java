package org.una.tramites.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Date;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RestController;
import org.una.tramites.dtos.TransaccionDTO;
import org.una.tramites.services.ITransaccionService;

@RestController
@RequestMapping("/transacciones")
@Api(tags = {"Transacciones"})
public class TransaccionController {

    @Autowired
    private ITransaccionService transaccionService;

    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un Transaccion por su Id", response = TransaccionDTO.class, tags = "Transacciones")
    @PreAuthorize("hasAuthority('TRANSACCION_CONSULTAR')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(transaccionService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/fecha-registro/{inicio}/{fin}")
    @ApiOperation(value = "Obtiene una lista de las transacciones entre fechas de registro", response = TransaccionDTO.class, responseContainer = "List", tags = "Transacciones")
    @PreAuthorize("hasAuthority('TRANSACCION_CONSULTAR')")
    public ResponseEntity<?> findByFechaRegistroBetween(@PathVariable(value = "inicio") Date startDate, @PathVariable(value = "fin") Date endDate) {
        try {
            return new ResponseEntity(transaccionService.findByFechaRegistroBetween(startDate, endDate), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("usuario/{id}/fecha-registro/{inicio}/{fin}")
    @ApiOperation(value = "Obtiene una lista de las transacciones por Usuario entre fechas de registro", response = TransaccionDTO.class, responseContainer = "List", tags = "Transacciones")
    @PreAuthorize("hasAuthority('TRANSACCION_CONSULTAR')")
    public ResponseEntity<?> findByUsuarioIdAndFechaRegistroBetween(@PathVariable(value = "id") Long id, @PathVariable(value = "inicio") Date startDate, @PathVariable(value = "fin") Date endDate) {
        try {
            return new ResponseEntity(transaccionService.findByUsuarioIdAndFechaRegistroBetween(id, startDate, endDate), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("permiso/{id}/fecha-registro/{inicio}/{fin}")
    @ApiOperation(value = "Obtiene una lista de las transacciones por Permiso entre fechas de registro", response = TransaccionDTO.class, responseContainer = "List", tags = "Transacciones")
    @PreAuthorize("hasAuthority('TRANSACCION_CONSULTAR')")
    public ResponseEntity<?> findByPermisoIdAndFechaRegistroBetween(@PathVariable(value = "id") Long id, @PathVariable(value = "inicio") Date startDate, @PathVariable(value = "fin") Date endDate) {
        try {
            return new ResponseEntity(transaccionService.findByPermisoIdAndFechaRegistroBetween(id, startDate, endDate), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("objeto/{term}/fecha-registro/{inicio}/{fin}")
    @ApiOperation(value = "Obtiene una lista de las transacciones por Objeto entre fechas de registro", response = TransaccionDTO.class, responseContainer = "List", tags = "Transacciones")
    @PreAuthorize("hasAuthority('TRANSACCION_CONSULTAR')")
    public ResponseEntity<?> findByObjetoAndFechaRegistroBetween(@PathVariable(value = "term") String objeto, @PathVariable(value = "inicio") Date startDate, @PathVariable(value = "fin") Date endDate) {
        try {
            return new ResponseEntity(transaccionService.findByObjetoAndFechaRegistroBetween(objeto, startDate, endDate), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    @ApiOperation(value = "Permite crear un Transaccion", response = TransaccionDTO.class, tags = "Transacciones")
    @PreAuthorize("hasAuthority('TRANSACCION_CREAR')")
    public ResponseEntity<?> create(@Valid @RequestBody TransaccionDTO departamentoDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(transaccionService.create(departamentoDTO), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

}
