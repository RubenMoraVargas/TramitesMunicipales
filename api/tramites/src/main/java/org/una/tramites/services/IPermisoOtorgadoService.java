package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.tramites.dtos.PermisoOtorgadoDTO;  

public interface IPermisoOtorgadoService {
    public Optional <PermisoOtorgadoDTO> findById(Long usuarioId);

    public Optional<List<PermisoOtorgadoDTO>> findByUsuarioId(Long usuarioId);

    public Optional<List<PermisoOtorgadoDTO>> findByPermisoId(Long permisoId);

    public Optional<List<PermisoOtorgadoDTO>> findByUsuarioIdAndEstado(Long usuarioId, boolean estado);

    public Optional<List<PermisoOtorgadoDTO>> findByPermisoIdAndEstado(Long permisoId, boolean estado);

    public Optional<List<PermisoOtorgadoDTO>> findByFechaRegistroBetween(Date startDate, Date endDate);
 
    public PermisoOtorgadoDTO create(PermisoOtorgadoDTO permisoOtorgado);

    public Optional<PermisoOtorgadoDTO> update(PermisoOtorgadoDTO permisoOtorgado, Long id);

    public void delete(Long id);

    public void deleteAll();

}
