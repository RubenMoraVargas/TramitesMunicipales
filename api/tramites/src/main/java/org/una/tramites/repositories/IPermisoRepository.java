package org.una.tramites.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.tramites.entities.Permiso;

public interface IPermisoRepository extends JpaRepository<Permiso, Long> {

    public Optional<List<Permiso>> findByEstado(boolean estado);

    public Optional<Permiso> findByCodigo(String codigo);

    public Optional<List<Permiso>> findByFechaRegistroBetween(Date startDate, Date endDate);

    public Optional<List<Permiso>> findByFechaModificacionBetween(Date startDate, Date endDate);

}
