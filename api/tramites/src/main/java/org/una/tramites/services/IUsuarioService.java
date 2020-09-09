package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.dtos.PermisoOtorgadoDTO;
import org.una.tramites.dtos.UsuarioDTO;  

public interface IUsuarioService {

    public Optional<List<UsuarioDTO>> findAll();

    public Optional<UsuarioDTO> findById(Long id);

    public Optional<List<UsuarioDTO>> findByCedulaAproximate(String cedula);

    public Optional<List<UsuarioDTO>> findByNombreCompletoAproximateIgnoreCase(String nombreCompleto);

    public UsuarioDTO create(UsuarioDTO usuario);

    public Optional<UsuarioDTO> update(UsuarioDTO usuario, Long id);

    public void delete(Long id);

    public void deleteAll();

    public Optional<List<UsuarioDTO>> findByDepartamentoId(Long id);

    public Optional<UsuarioDTO> findJefeByDepartamento(Long id);

    public Optional<UsuarioDTO> findByCedula(String cedula);

    public List<PermisoOtorgadoDTO> findPermisosOtorgadosByCedula(String cedula);

}
