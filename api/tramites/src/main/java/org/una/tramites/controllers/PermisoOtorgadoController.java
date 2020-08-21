package org.una.tramites.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Date;
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
import org.una.tramites.dtos.PermisoOtorgadoDTO;
import org.una.tramites.entities.PermisoOtorgado;
import org.una.tramites.services.IPermisoOtorgadoService;
import org.una.tramites.utils.MapperUtils;

@RestController
@RequestMapping("/permisos-otorgados")
@Api(tags = {"Permisos"})
public class PermisoOtorgadoController {

    @Autowired
    private IPermisoOtorgadoService permisoOtorgadoService;

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un PermisoOtorgado por su Id", response = PermisoOtorgadoDTO.class, tags = "Permisos")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<PermisoOtorgado> permisoOtorgadoFound = permisoOtorgadoService.findById(id);
            if (permisoOtorgadoFound.isPresent()) {
                PermisoOtorgadoDTO permisoOtorgadoDto = MapperUtils.DtoFromEntity(permisoOtorgadoFound.get(), PermisoOtorgadoDTO.class);
                return new ResponseEntity<>(permisoOtorgadoDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/usuario/{id}")
    @ApiOperation(value = "Obtiene una lista de  los PermisoOtorgados por Usuario", response = PermisoOtorgadoDTO.class, responseContainer = "List", tags = "Permisos")
    public @ResponseBody
    ResponseEntity<?> findByUsuarioId(@PathVariable(value = "id") Long id) {
        try {
            Optional<List<PermisoOtorgado>> result = permisoOtorgadoService.findByUsuarioId(id);
            if (result.isPresent()) {
                List<PermisoOtorgadoDTO> permisoOtorgadosDTO = MapperUtils.DtoListFromEntityList(result.get(), PermisoOtorgadoDTO.class);
                return new ResponseEntity<>(permisoOtorgadosDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/permiso/{id}")
    @ApiOperation(value = "Obtiene una lista de  los PermisoOtorgados por Permiso", response = PermisoOtorgadoDTO.class, responseContainer = "List", tags = "Permisos")
    public @ResponseBody
    ResponseEntity<?> findByPermisoId(@PathVariable(value = "id") Long id) {
        try {
            Optional<List<PermisoOtorgado>> result = permisoOtorgadoService.findByPermisoId(id);
            if (result.isPresent()) {
                List<PermisoOtorgadoDTO> permisoOtorgadosDTO = MapperUtils.DtoListFromEntityList(result.get(), PermisoOtorgadoDTO.class);
                return new ResponseEntity<>(permisoOtorgadosDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/usuario/{id}/estado/{estado}")
    @ApiOperation(value = "Obtiene una lista de los PermisoOtorgados por Usuario y estado", response = PermisoOtorgadoDTO.class, responseContainer = "List", tags = "Permisos")
    public @ResponseBody
    ResponseEntity<?> findByUsuarioIdAndEstado(@PathVariable(value = "id") Long id,@PathVariable(value = "estado") boolean estado) {
        try {
            Optional<List<PermisoOtorgado>> result = permisoOtorgadoService.findByUsuarioIdAndEstado(id, estado);
            if (result.isPresent()) {
                List<PermisoOtorgadoDTO> permisoOtorgadosDTO = MapperUtils.DtoListFromEntityList(result.get(), PermisoOtorgadoDTO.class);
                return new ResponseEntity<>(permisoOtorgadosDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/permiso/{id}/estado/{estado}")
    @ApiOperation(value = "Obtiene una lista de los PermisoOtorgados por Permiso y estado", response = PermisoOtorgadoDTO.class, responseContainer = "List", tags = "Permisos")
    public @ResponseBody
    ResponseEntity<?> findByPermisoIdAndEstado(@PathVariable(value = "id") Long id,@PathVariable(value = "estado") boolean estado) {
        try {
            Optional<List<PermisoOtorgado>> result = permisoOtorgadoService.findByPermisoIdAndEstado(id, estado);
            if (result.isPresent()) {
                List<PermisoOtorgadoDTO> permisoOtorgadosDTO = MapperUtils.DtoListFromEntityList(result.get(), PermisoOtorgadoDTO.class);
                return new ResponseEntity<>(permisoOtorgadosDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } 
    
    @GetMapping("/fecha-registro/{inicio}/{fin}")
    @ApiOperation(value = "Obtiene una lista de los PermisoOtorgados por Fecha de registro", response = PermisoOtorgadoDTO.class, responseContainer = "List", tags = "Permisos")
    public @ResponseBody
    ResponseEntity<?> findByFechaRegistroBetween(@PathVariable(value = "inicio") Date startDate,@PathVariable(value = "fin") Date endDate) {
        try {
            Optional<List<PermisoOtorgado>> result = permisoOtorgadoService.findByFechaRegistroBetween(startDate, endDate);
            if (result.isPresent()) {
                List<PermisoOtorgadoDTO> permisoOtorgadosDTO = MapperUtils.DtoListFromEntityList(result.get(), PermisoOtorgadoDTO.class);
                return new ResponseEntity<>(permisoOtorgadosDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ApiOperation(value = "Permite crear un PermisoOtorgado", response = PermisoOtorgadoDTO.class, tags = "Permisos")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody PermisoOtorgado permisoOtorgado) {
        try {
            PermisoOtorgado permisoOtorgadoCreated = permisoOtorgadoService.create(permisoOtorgado);
            PermisoOtorgadoDTO permisoOtorgadoDto = MapperUtils.DtoFromEntity(permisoOtorgadoCreated, PermisoOtorgadoDTO.class);
            return new ResponseEntity<>(permisoOtorgadoDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Permite modificar un PermisoOtorgado a partir de su Id", response = PermisoOtorgadoDTO.class, tags = "Permisos")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody PermisoOtorgado permisoOtorgadoModified) {
        try {
            Optional<PermisoOtorgado> permisoOtorgadoUpdated = permisoOtorgadoService.update(permisoOtorgadoModified, id);
            if (permisoOtorgadoUpdated.isPresent()) {
                PermisoOtorgadoDTO permisoOtorgadoDto = MapperUtils.DtoFromEntity(permisoOtorgadoUpdated.get(), PermisoOtorgadoDTO.class);
                return new ResponseEntity<>(permisoOtorgadoDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/{id}") 
    @ApiOperation(value = "Permite eliminar un PermisoOtorgado a partir de su Id", response = PermisoOtorgadoDTO.class, tags = "Permisos")
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
    public ResponseEntity<?> deleteAll() {
        try {
            permisoOtorgadoService.deleteAll();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
