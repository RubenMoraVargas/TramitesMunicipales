package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional; 
import org.una.tramites.dtos.PermisoDTO; 

public interface IPermisoService {

    public Optional<PermisoDTO> findById(Long id);

    public Optional<PermisoDTO> findByCodigo(String codigo);

    public Optional<List<PermisoDTO>> findByEstado(boolean estado);

    public Optional<List<PermisoDTO>> findByFechaRegistroBetween(Date startDate, Date endDate);

    public PermisoDTO create(PermisoDTO permiso);

    public Optional<PermisoDTO> update(PermisoDTO permiso, Long id);

    public void delete(Long id);

    public void deleteAll();

    public Long countByEstado(boolean estado);
}
