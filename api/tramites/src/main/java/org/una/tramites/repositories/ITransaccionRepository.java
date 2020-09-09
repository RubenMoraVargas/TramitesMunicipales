package org.una.tramites.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.una.tramites.entities.Transaccion; 

public interface ITransaccionRepository extends JpaRepository<Transaccion, Long> {  
    @Query(value = "SELECT t FROM Transaccion t JOIN t.permisoOtorgado po JOIN po.usuario u where u.id=:usuarioId and t.fechaRegistro BETWEEN :startDate AND :endDate")
    public Optional<List<Transaccion>> findByUsuarioIdAndFechaRegistroBetween(Long usuarioId, Date startDate, Date endDate);
 
    
    @Query(value = "SELECT t FROM Transaccion t JOIN t.permisoOtorgado po JOIN po.permiso p where p.id=:permisoId and t.fechaRegistro BETWEEN :startDate AND :endDate")
     public Optional<List<Transaccion>> findByPermisoIdAndFechaRegistroBetween(Long permisoId, Date startDate, Date endDate);
 
    @Query(value = "SELECT t FROM Transaccion t where UPPER(t.objeto) like CONCAT('%',UPPER(:objeto),'%') and t.fechaRegistro BETWEEN :startDate AND :endDate")
    public Optional<List<Transaccion>> findByObjetoAndFechaRegistroBetween(String objeto, Date startDate, Date endDate);

    public Optional<List<Transaccion>> findByFechaRegistroBetween(Date startDate, Date endDate);
 
}
