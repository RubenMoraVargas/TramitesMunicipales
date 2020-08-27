package org.una.tramites.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.una.tramites.entities.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

    public Usuario findByCedulaAndPasswordEncriptado(String cedula, String passwordEncriptado);

    public Optional<List<Usuario>> findByCedulaContaining(String cedula);

    public Optional<Usuario> findByCedula(String cedula);

    public Optional<List<Usuario>> findByNombreCompletoContainingIgnoreCase(String nombreCompleto);

    public Optional<List<Usuario>> findByDepartamentoId(Long id);

    @Query("SELECT u FROM Usuario u LEFT JOIN u.departamento d WHERE u.esJefe=1 AND d.id=:id")
    public Usuario findJefeByDepartamento(Long id);

}
