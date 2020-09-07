package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.dtos.AuthenticationRequest;
import org.una.tramites.dtos.AuthenticationResponse;
import org.una.tramites.dtos.PermisoOtorgadoDTO;
import org.una.tramites.dtos.UsuarioDTO;
import org.una.tramites.entities.Usuario;
import org.una.tramites.jwt.JwtProvider;
import org.una.tramites.utils.MapperUtils;

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

        Optional<Usuario> usuario = usuarioService.findByCedula(authenticationRequest.getCedula());

        if (usuario.isPresent()) {
            authenticationResponse.setJwt(jwtProvider.generateToken(authenticationRequest));
            UsuarioDTO usuarioDto = MapperUtils.DtoFromEntity(usuario.get(), UsuarioDTO.class);
            authenticationResponse.setUsuario(usuarioDto);
            List<PermisoOtorgadoDTO> permisosOtorgadosDto = MapperUtils.DtoListFromEntityList(usuario.get().getPermisosOtorgados(), PermisoOtorgadoDTO.class);
            authenticationResponse.setPermisos(permisosOtorgadosDto);

            return authenticationResponse;
        } else {
            return null;
        }

    }
}
