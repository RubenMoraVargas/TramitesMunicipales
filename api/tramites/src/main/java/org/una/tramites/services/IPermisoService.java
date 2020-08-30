package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.tramites.entities.Permiso;

public interface IPermisoService {

    public Optional<Permiso> findById(Long id);

    public Optional<Permiso> findByCodigo(String codigo);

    public Optional<List<Permiso>> findByEstado(boolean estado);

    public Optional<List<Permiso>> findByFechaRegistroBetween(Date startDate, Date endDate);

    public Permiso create(Permiso permiso);

    public Optional<Permiso> update(Permiso permiso, Long id);

    public void delete(Long id);

    public void deleteAll();

    public Long countByEstado(boolean estado);
}
