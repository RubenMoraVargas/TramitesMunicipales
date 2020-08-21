package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.tramites.entities.PermisoOtorgado;

public interface IPermisoOtorgadoService {
    public Optional <PermisoOtorgado> findById(Long usuarioId);

    public Optional<List<PermisoOtorgado>> findByUsuarioId(Long usuarioId);

    public Optional<List<PermisoOtorgado>> findByPermisoId(Long permisoId);

    public Optional<List<PermisoOtorgado>> findByUsuarioIdAndEstado(Long usuarioId, boolean estado);

    public Optional<List<PermisoOtorgado>> findByPermisoIdAndEstado(Long permisoId, boolean estado);

    public Optional<List<PermisoOtorgado>> findByFechaRegistroBetween(Date startDate, Date endDate);
 
    public PermisoOtorgado create(PermisoOtorgado permisoOtorgado);

    public Optional<PermisoOtorgado> update(PermisoOtorgado permisoOtorgado, Long id);

    public void delete(Long id);

    public void deleteAll();

}
