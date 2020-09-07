package org.una.tramites.services;

import org.una.tramites.dtos.AuthenticationRequest;
import org.una.tramites.dtos.AuthenticationResponse;

public interface IAutenticacionService {

    public AuthenticationResponse login(AuthenticationRequest authenticationRequest);

}
