package org.una.tramites.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.una.tramites.dtos.TransaccionDTO;
import org.una.tramites.entities.Transaccion;
import org.una.tramites.services.ITransaccionService;
import org.una.tramites.utils.MapperUtils;

@RestController
@RequestMapping("/transacciones")
@Api(tags = {"Transacciones"})
public class TransaccionController {

    @Autowired
    private ITransaccionService transaccionService;

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un Transaccion por su Id", response = TransaccionDTO.class, tags = "Transacciones")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<Transaccion> TransaccionFound = transaccionService.findById(id);
            if (TransaccionFound.isPresent()) {
                TransaccionDTO TransaccionDto = MapperUtils.DtoFromEntity(TransaccionFound.get(), TransaccionDTO.class);
                return new ResponseEntity<>(TransaccionDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/fecha-registro/{inicio}/{fin}")
    @ApiOperation(value = "Obtiene una lista de las transacciones entre fechas de registro", response = TransaccionDTO.class, responseContainer = "List", tags = "Transacciones")
    @ResponseBody
    public ResponseEntity<?> findByFechaRegistroBetween(@PathVariable(value = "inicio") Date startDate, @PathVariable(value = "fin") Date endDate) {
        try {
            Optional<List<Transaccion>> result = transaccionService.findByFechaRegistroBetween(startDate, endDate);
            if (result.isPresent()) {
                List<TransaccionDTO> transaccionesDTO = MapperUtils.DtoListFromEntityList(result.get(), TransaccionDTO.class);
                return new ResponseEntity<>(transaccionesDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("usuario/{id}/fecha-registro/{inicio}/{fin}")
    @ApiOperation(value = "Obtiene una lista de las transacciones por Usuario entre fechas de registro", response = TransaccionDTO.class, responseContainer = "List", tags = "Transacciones")
    @ResponseBody
    public ResponseEntity<?> findByUsuarioIdAndFechaRegistroBetween(@PathVariable(value = "id") Long id, @PathVariable(value = "inicio") Date startDate, @PathVariable(value = "fin") Date endDate) {
        try {
            Optional<List<Transaccion>> result = transaccionService.findByUsuarioIdAndFechaRegistroBetween(id, startDate, endDate);
            if (result.isPresent()) {
                List<TransaccionDTO> transaccionesDTO = MapperUtils.DtoListFromEntityList(result.get(), TransaccionDTO.class);
                return new ResponseEntity<>(transaccionesDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("permiso/{id}/fecha-registro/{inicio}/{fin}")
    @ApiOperation(value = "Obtiene una lista de las transacciones por Permiso entre fechas de registro", response = TransaccionDTO.class, responseContainer = "List", tags = "Transacciones")
    @ResponseBody
    public ResponseEntity<?> findByPermisoIdAndFechaRegistroBetween(@PathVariable(value = "id") Long id, @PathVariable(value = "inicio") Date startDate, @PathVariable(value = "fin") Date endDate) {
        try {
            Optional<List<Transaccion>> result = transaccionService.findByPermisoIdAndFechaRegistroBetween(id, startDate, endDate);
            if (result.isPresent()) {
                List<TransaccionDTO> transaccionesDTO = MapperUtils.DtoListFromEntityList(result.get(), TransaccionDTO.class);
                return new ResponseEntity<>(transaccionesDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("objeto/{term}/fecha-registro/{inicio}/{fin}")
    @ApiOperation(value = "Obtiene una lista de las transacciones por Objeto entre fechas de registro", response = TransaccionDTO.class, responseContainer = "List", tags = "Transacciones")
    @ResponseBody
    public ResponseEntity<?> findByObjetoAndFechaRegistroBetween(@PathVariable(value = "term") String term, @PathVariable(value = "inicio") Date startDate, @PathVariable(value = "fin") Date endDate) {
        try {
            Optional<List<Transaccion>> result = transaccionService.findByObjetoAndFechaRegistroBetween(term, startDate, endDate);
            if (result.isPresent()) {
                List<TransaccionDTO> transaccionesDTO = MapperUtils.DtoListFromEntityList(result.get(), TransaccionDTO.class);
                return new ResponseEntity<>(transaccionesDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ApiOperation(value = "Permite crear un Transaccion", response = TransaccionDTO.class, tags = "Transacciones")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody Transaccion Transaccion) {
        try {
            Transaccion TransaccionCreated = transaccionService.create(Transaccion);
            TransaccionDTO TransaccionDto = MapperUtils.DtoFromEntity(TransaccionCreated, TransaccionDTO.class);
            return new ResponseEntity<>(TransaccionDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
