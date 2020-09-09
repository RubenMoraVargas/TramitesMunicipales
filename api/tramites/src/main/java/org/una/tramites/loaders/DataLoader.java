package org.una.tramites.loaders;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.una.tramites.dtos.PermisoDTO;
import org.una.tramites.dtos.PermisoOtorgadoDTO;
import org.una.tramites.dtos.UsuarioDTO;
import org.una.tramites.services.IPermisoOtorgadoService;
import org.una.tramites.services.IPermisoService;
import org.una.tramites.services.IUsuarioService;

@Component
public class DataLoader implements ApplicationRunner {

    @Value("${app.admin-user}")
    private String cedula;

    @Value("${app.password}")
    private String password;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IPermisoService permisoService;

    @Autowired
    private IPermisoOtorgadoService permisoOtorgadoService;

    private void createPermisos() {
        for (Permisos permiso : Permisos.values()) {
            PermisoDTO nuevoPermiso = new PermisoDTO();
            nuevoPermiso.setCodigo(permiso.getCodigo());
            nuevoPermiso.setDescripcion(permiso.name());
            permisoService.create(nuevoPermiso);
        }
    }

    @Override
    public void run(ApplicationArguments args) {

        if (permisoService.countByEstado(true) <= 0) {
            createPermisos();
        }

        if (usuarioService.findByCedula(cedula).isEmpty()) {

            PermisoDTO permiso;

            Optional<PermisoDTO> permisoBuscado = permisoService.findByCodigo(Permisos.USUARIO_CREAR.getCodigo());

            if (permisoBuscado.isEmpty()) {
                permiso = new PermisoDTO();
                permiso.setCodigo(Permisos.USUARIO_CREAR.getCodigo());
                permiso.setDescripcion(Permisos.USUARIO_CREAR.name());
                permiso = permisoService.create(permiso);

            } else {
                permiso = permisoBuscado.get();
            }
            UsuarioDTO usuario = new UsuarioDTO();
            usuario.setNombreCompleto("Usuario Admin");
            usuario.setCedula(cedula);
            usuario.setPasswordEncriptado(password);
            usuarioService.create(usuario);

            PermisoOtorgadoDTO permisoOtorgado = new PermisoOtorgadoDTO();
            permisoOtorgado.setPermiso(permiso);
            permisoOtorgado.setUsuario(usuario);
            permisoOtorgadoService.create(permisoOtorgado);
            System.out.println("Se agrega el usuario inicial");
        } else {
            System.out.println("Se encontro el admin");
        }

    }
}
