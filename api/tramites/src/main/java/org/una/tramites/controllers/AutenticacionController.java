package org.una.tramites.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.una.tramites.dtos.AuthenticationRequest;
import org.una.tramites.dtos.UsuarioDTO;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.una.tramites.services.IAutenticacionService;

@RestController
@RequestMapping("/autenticacion")
@Api(tags = {"Seguridad"})
public class AutenticacionController {

    @Autowired
    private IAutenticacionService autenticacionService;

    @PostMapping("/login") 
    @ApiOperation(value = "Inicio de sesión para conseguir un token de acceso", response = UsuarioDTO.class, tags = "Seguridad")
    public ResponseEntity<?> login(@Valid @RequestBody AuthenticationRequest authenticationRequest, BindingResult bindingResult) {

        final String MENSAJE_VERIFICAR_CREDENCIALES = "Debe verificar y proporcionar credenciales correctos para realizar esta acción";
        final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(autenticacionService.login(authenticationRequest), HttpStatus.OK);

            } catch (UsernameNotFoundException | BadCredentialsException e) {
                return new ResponseEntity(MENSAJE_VERIFICAR_CREDENCIALES, HttpStatus.UNAUTHORIZED);

            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

}
