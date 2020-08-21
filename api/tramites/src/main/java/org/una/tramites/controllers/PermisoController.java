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
import org.una.tramites.dtos.PermisoDTO;
import org.una.tramites.entities.Permiso;
import org.una.tramites.services.IPermisoService;  
import org.una.tramites.utils.MapperUtils;

@RestController
@RequestMapping("/permisos")
@Api(tags = {"Permisos"})
public class PermisoController {

    @Autowired
    private IPermisoService permisoService;

    @GetMapping("/estado/{estado}")
    @ApiOperation(value = "Obtiene una lista de los Permisos por estado", response = PermisoDTO.class, responseContainer = "List", tags = "Permisos")
    public @ResponseBody
    ResponseEntity<?> findByEstado(@PathVariable(value = "estado") boolean estado) {
        try {
            Optional<List<Permiso>> result = permisoService.findByEstado(estado);
            if (result.isPresent()) {
                List<PermisoDTO> permisosDTO = MapperUtils.DtoListFromEntityList(result.get(), PermisoDTO.class);
                return new ResponseEntity<>(permisosDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un Permiso por su Id", response = PermisoDTO.class, tags = "Permisos")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<Permiso> permisoFound = permisoService.findById(id);
            if (permisoFound.isPresent()) {
                PermisoDTO permisoDto = MapperUtils.DtoFromEntity(permisoFound.get(), PermisoDTO.class);
                return new ResponseEntity<>(permisoDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   
    @GetMapping("/fecha-registro/{inicio}/{fin}")
    @ApiOperation(value = "Obtiene una lista de los Permisos entre un rango de Fechas de Registro", response = PermisoDTO.class, responseContainer = "List", tags = "Permisos")
    public @ResponseBody
    ResponseEntity<?> findByFechaRegistroBetween(@PathVariable(value = "inicio") Date startDate,@PathVariable(value = "fin") Date endDate) {
        try {
            Optional<List<Permiso>> result = permisoService.findByFechaRegistroBetween(startDate,endDate );
            if (result.isPresent()) {
                List<PermisoDTO> permisosDTO = MapperUtils.DtoListFromEntityList(result.get(), PermisoDTO.class);
                return new ResponseEntity<>(permisosDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
 
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ApiOperation(value = "Permite crear un Permiso", response = PermisoDTO.class, tags = "Permisos")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody Permiso permiso) {
        try {
            Permiso permisoCreated = permisoService.create(permiso);
            PermisoDTO permisoDto = MapperUtils.DtoFromEntity(permisoCreated, PermisoDTO.class);
            return new ResponseEntity<>(permisoDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Permite modificar un Permiso a partir de su Id", response = PermisoDTO.class, tags = "Permisos")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody Permiso permisoModified) {
        try {
            Optional<Permiso> permisoUpdated = permisoService.update(permisoModified, id);
            if (permisoUpdated.isPresent()) {
                PermisoDTO permisoDto = MapperUtils.DtoFromEntity(permisoUpdated.get(), PermisoDTO.class);
                return new ResponseEntity<>(permisoDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/{id}") 
    @ApiOperation(value = "Permite eliminar un Permiso a partir de su Id", response = PermisoDTO.class, tags = "Permisos")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            permisoService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    @ApiOperation(value = "Permite eliminar todos los Permisos", response = PermisoDTO.class,  tags = "Permisos")
    public ResponseEntity<?> deleteAll() {
        try {
            permisoService.deleteAll();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
