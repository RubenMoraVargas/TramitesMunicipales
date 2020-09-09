package org.una.tramites.services;
 
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.dtos.AuthenticationRequest;
import org.una.tramites.dtos.AuthenticationResponse; 
import org.una.tramites.dtos.UsuarioDTO; 
import org.una.tramites.jwt.JwtProvider; 

@Service
public class AutenticacionServiceImplementation implements IAutenticacionService {

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    @Transactional(readOnly = true)
    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getCedula(), authenticationRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();

        Optional<UsuarioDTO> usuario = usuarioService.findByCedula(authenticationRequest.getCedula());

        if (usuario.isPresent()) {
            authenticationResponse.setJwt(jwtProvider.generateToken(authenticationRequest));

            authenticationResponse.setUsuario(usuario.get());
            authenticationResponse.setPermisos(
                    usuarioService.findPermisosOtorgadosByCedula(authenticationRequest.getCedula()));

            return authenticationResponse;
        } else {
            throw new UsernameNotFoundException("No se ha encontrado información del usuario");
        }

    }
}
