package org.una.tramites.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.una.tramites.dtos.AuthenticationRequest;
import org.una.tramites.dtos.UsuarioDTO;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.una.tramites.dtos.AuthenticationResponse;
import org.una.tramites.services.IAutenticacionService;

@RestController
@RequestMapping("/autenticacion")
@Api(tags = {"Seguridad"})
public class AutenticacionController {

    @Autowired
    private IAutenticacionService autenticacionService;

    @PostMapping("/login")
    @ResponseBody
    @ApiOperation(value = "Inicio de sesión para conseguir un token de acceso", response = UsuarioDTO.class, tags = "Seguridad")
    public ResponseEntity<?> login(@Valid @RequestBody AuthenticationRequest authenticationRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity("La información no esta bien formada o no coincide con el formato esperado", HttpStatus.BAD_REQUEST);
        }
        try {
            AuthenticationResponse authenticationResponse = autenticacionService.login(authenticationRequest);
            if (authenticationResponse != null) {
                return new ResponseEntity(authenticationResponse, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No se encontro la información del usuario para autenticarlo correctamente", HttpStatus.UNAUTHORIZED);
            } 
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>("Los credenciales son incorrectos", HttpStatus.UNAUTHORIZED);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
