
package org.una.tramites.repositories;
 
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository; 
import org.una.tramites.entities.Departamento; 

public interface IDepartamentoRepository extends JpaRepository<Departamento, Long> {
    public Optional<List<Departamento>> findByEstado( boolean estado); 
   }
