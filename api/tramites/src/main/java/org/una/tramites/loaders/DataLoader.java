package org.una.tramites.loaders;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.una.tramites.entities.Permiso;
import org.una.tramites.entities.PermisoOtorgado;
import org.una.tramites.entities.Usuario;
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
    private IPermisoService permisoService;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IPermisoOtorgadoService permisoOtorgadoService;

    @Override
    public void run(ApplicationArguments args) {
        if (permisoService.countByEstado(true) <= 0) {
            createPermisos();

        }
        if (usuarioService.findByCedula(cedula).isEmpty()) {
            createAdmin(getPermisoCrearUsuario());
            System.out.println("Se agrega el usuario inicial");

        } else {
            System.out.println("Se encontro el admin");

        }

    }

    private void createPermisos() {
        for (Permisos permiso : Permisos.values()) {
            Permiso nuevoPermiso = new Permiso();
            nuevoPermiso.setCodigo(permiso.getCodigo());
            nuevoPermiso.setDescripcion(permiso.name());
            permisoService.create(nuevoPermiso);
        } 
    }

    private Permiso getPermisoCrearUsuario() {

        Permiso permisoCrearUsuario;
        final String codigoPermiso = "USU1";
        Optional<Permiso> permisoBuscado = permisoService.findByCodigo(codigoPermiso);

        if (permisoBuscado.isPresent()) {
            permisoCrearUsuario = permisoBuscado.get();
        } else {
            permisoCrearUsuario = new Permiso();
            permisoCrearUsuario.setCodigo(codigoPermiso);
            permisoCrearUsuario.setDescripcion("Crear usuario");
            permisoCrearUsuario = permisoService.create(permisoCrearUsuario);
        }
        return permisoCrearUsuario;
    }

    private void createAdmin(Permiso permisoCrearUsuario) {
        Usuario usuario = new Usuario();
        usuario.setNombreCompleto("Usuario Admin");
        usuario.setCedula(cedula);
        usuario.setPasswordEncriptado(password);
        usuario = usuarioService.create(usuario);

        PermisoOtorgado permisoOtorgado = new PermisoOtorgado();
        permisoOtorgado.setPermiso(permisoCrearUsuario);
        permisoOtorgado.setUsuario(usuario);
        permisoOtorgadoService.create(permisoOtorgado);
    }
}
