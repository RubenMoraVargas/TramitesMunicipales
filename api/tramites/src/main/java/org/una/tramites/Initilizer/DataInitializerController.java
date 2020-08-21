package org.una.tramites.Initilizer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.una.tramites.dtos.DepartamentoDTO; 

@RestController
@RequestMapping("/data-initializer")
@Api(tags = {"Seguridad"})
public class DataInitializerController {

    @Autowired
    private IDataInitializerService dataInitializerService;

    @GetMapping("/")
    @ApiOperation(value = "Agrega información de desarrollo para probar la aplicación",  tags = "Seguridad")
    public ResponseEntity<?> initDevelopData() {
        try {
            dataInitializerService.initDevelopData();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    @ApiOperation(value = "Elimina la información del sistema. Cuidado!!!",   tags = "Seguridad")
    public ResponseEntity<?> deleteAllData() {
        try {
            dataInitializerService.deleteAllData();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
