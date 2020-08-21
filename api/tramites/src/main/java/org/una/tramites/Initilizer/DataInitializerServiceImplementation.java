package org.una.tramites.Initilizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.una.tramites.entities.Departamento;
import org.una.tramites.entities.Permiso;
import org.una.tramites.entities.PermisoOtorgado;
import org.una.tramites.entities.Usuario;
import org.una.tramites.repositories.IDepartamentoRepository;
import org.una.tramites.repositories.IPermisoOtorgadoRepository;
import org.una.tramites.repositories.IPermisoRepository;
import org.una.tramites.repositories.ITransaccionRepository;
import org.una.tramites.repositories.IUsuarioRepository;

public class DataInitializerServiceImplementation implements IDataInitializerService {

    @Autowired
    private IDepartamentoRepository departamentoRepository;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private IPermisoRepository permisoRepository;

    @Autowired
    private IPermisoOtorgadoRepository permisoOtorgadoRepository;

    @Autowired
    private ITransaccionRepository transaccionRepository;

    @Override
    public void initDevelopData() {

        Departamento patentesDepartamento = new Departamento();
        patentesDepartamento.setNombre("Licencias y Patentes");
        patentesDepartamento = departamentoRepository.save(patentesDepartamento);
 
        Departamento cajasDepartamento = new Departamento();
        cajasDepartamento.setNombre("Centro Integrado de Atención Tributaria");
        cajasDepartamento = departamentoRepository.save(cajasDepartamento);
 
        Usuario cajeroUsuario = new Usuario();
        cajeroUsuario.setCedula("0123456789");
        cajeroUsuario.setNombreCompleto("Usuario Prueba simulacion cajero");
        cajeroUsuario.setPasswordEncriptado("0123456789");
        cajeroUsuario.setDepartamento(cajasDepartamento);
        cajeroUsuario = usuarioRepository.save(cajeroUsuario);
 
        Usuario coordinadorDepartamentoUsuario = new Usuario();
        coordinadorDepartamentoUsuario.setCedula("9876543210");
        coordinadorDepartamentoUsuario.setNombreCompleto("Usuario Prueba simulacion Coordinador");
        coordinadorDepartamentoUsuario.setEsJefe(true);
        coordinadorDepartamentoUsuario.setPasswordEncriptado("9876543210");
        coordinadorDepartamentoUsuario.setDepartamento(patentesDepartamento);
        coordinadorDepartamentoUsuario = usuarioRepository.save(coordinadorDepartamentoUsuario);
         
        
        Permiso ingresarAlSistemaPermiso = new Permiso();
        ingresarAlSistemaPermiso.setCodigo("SIS1");
        ingresarAlSistemaPermiso.setDescripcion("Ingresar Al Sistema");  
        ingresarAlSistemaPermiso = permisoRepository.save(ingresarAlSistemaPermiso);
        
        Permiso consultarUsuarioPermiso = new Permiso();
        consultarUsuarioPermiso.setCodigo("USU1");
        consultarUsuarioPermiso.setDescripcion("COnsultar usuario");  
        consultarUsuarioPermiso = permisoRepository.save(consultarUsuarioPermiso);
         
        
        PermisoOtorgado permisoOtorgado1= new PermisoOtorgado();
        permisoOtorgado1.setPermiso(ingresarAlSistemaPermiso);
        permisoOtorgado1.setUsuario(cajeroUsuario);
        permisoOtorgado1 = permisoOtorgadoRepository.save(permisoOtorgado1);
         
        PermisoOtorgado permisoOtorgado2= new PermisoOtorgado();
        permisoOtorgado2.setPermiso(consultarUsuarioPermiso);
        permisoOtorgado2.setUsuario(coordinadorDepartamentoUsuario);
        permisoOtorgado2 = permisoOtorgadoRepository.save(permisoOtorgado2);
         
    }
 
    @Override
    public void deleteAllData() {
        transaccionRepository.deleteAll();
        permisoOtorgadoRepository.deleteAll();
        permisoRepository.deleteAll();
        usuarioRepository.deleteAll();
        departamentoRepository.deleteAll();
    }

}
