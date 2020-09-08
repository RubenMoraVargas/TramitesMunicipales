package org.una.tramites.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.entities.Usuario;
import org.una.tramites.repositories.IUsuarioRepository;

@Service
public class UsuarioServiceImplementation implements UserDetailsService, IUsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder bCrypt;

    private Usuario encriptarPassword(Usuario usuario) {
        String password = usuario.getPasswordEncriptado();
        System.err.println(usuario.getPasswordEncriptado());
        if (!password.isBlank()) {
            usuario.setPasswordEncriptado(bCrypt.encode(password));
            System.err.println(usuario.getPasswordEncriptado());
        }
        return usuario;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Usuario>> findAll() {
        return Optional.ofNullable(usuarioRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Usuario>> findByCedulaAproximate(String cedula) {
        return usuarioRepository.findByCedulaContaining(cedula);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Usuario>> findByNombreCompletoAproximateIgnoreCase(String nombreCompleto) {
        return usuarioRepository.findByNombreCompletoContainingIgnoreCase(nombreCompleto);
    }

    @Override
    @Transactional
    public Usuario create(Usuario usuario) {
        usuario = encriptarPassword(usuario);
        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public Optional<Usuario> update(Usuario usuario, Long id) {
        if (usuarioRepository.findById(id).isPresent()) {
            usuario = encriptarPassword(usuario);
            return Optional.ofNullable(usuarioRepository.save(usuario));
        } else {
            return null;
        }

    }

    @Override
    @Transactional
    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        usuarioRepository.deleteAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Usuario>> findByDepartamentoId(Long id) {
        return usuarioRepository.findByDepartamentoId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> findJefeByDepartamento(Long id) {
        return Optional.ofNullable(usuarioRepository.findJefeByDepartamento(id));
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioBuscado = usuarioRepository.findByCedula(username);
        if (usuarioBuscado.isPresent()) {
            Usuario usuario = usuarioBuscado.get();
            List<GrantedAuthority> roles = new ArrayList<>();

            usuario.getPermisosOtorgados().forEach(permisoOtorgado -> {
                roles.add(new SimpleGrantedAuthority(permisoOtorgado.getPermiso().getCodigo()));
            });
            UserDetails userDetails = new User(usuario.getCedula(), usuario.getPasswordEncriptado(), roles);
            return userDetails;
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> findByCedula(String cedula) {
        return usuarioRepository.findByCedula(cedula);
    }

}
