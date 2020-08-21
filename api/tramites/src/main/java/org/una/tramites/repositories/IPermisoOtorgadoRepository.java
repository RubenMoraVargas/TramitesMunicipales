package org.una.tramites.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.tramites.entities.PermisoOtorgado; 

public interface IPermisoOtorgadoRepository extends JpaRepository<PermisoOtorgado, Long> {

    public Optional<List<PermisoOtorgado>> findByUsuarioId(Long usuarioId);

    public Optional<List<PermisoOtorgado>> findByPermisoId(Long permisoId);

    public Optional<List<PermisoOtorgado>> findByUsuarioIdAndEstado(Long usuarioId, boolean estado);

    public Optional<List<PermisoOtorgado>> findByPermisoIdAndEstado(Long permisoId, boolean estado);

    public Optional<List<PermisoOtorgado>> findByFechaRegistroBetween(Date startDate, Date endDate);

    
}
