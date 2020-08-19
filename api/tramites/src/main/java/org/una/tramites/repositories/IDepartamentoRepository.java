
package org.una.tramites.repositories;
 
import org.springframework.data.jpa.repository.JpaRepository; 
import org.una.tramites.entities.Departamento;

public interface IDepartamentoRepository extends JpaRepository<Departamento, Long> {
   
   }
