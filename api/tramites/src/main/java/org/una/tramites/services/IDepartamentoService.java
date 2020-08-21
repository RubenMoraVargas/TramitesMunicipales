package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.entities.Departamento;

public interface IDepartamentoService {

    public Optional<List<Departamento>> findAll();

    public Optional<Departamento> findById(Long id);

    public Optional<List<Departamento>> findByEstado(boolean estado);

    public Departamento create(Departamento departamento);

    public Optional<Departamento> update(Departamento usuario, Long id);

    public void delete(Long id);

    public void deleteAll();

}
