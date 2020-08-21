package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.entities.Departamento;
import org.una.tramites.repositories.IDepartamentoRepository;

@Service
public class DepartamentoServiceImplementation implements IDepartamentoService {

    @Autowired
    private IDepartamentoRepository departamentoRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Departamento>> findAll() {
        return Optional.ofNullable(departamentoRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Departamento> findById(Long id) {
        return departamentoRepository.findById(id);
    }
  
    @Override
    public Optional<List<Departamento>> findByEstado(boolean estado) {
         return departamentoRepository.findByEstado(estado) ;
    }
    @Override
    @Transactional
    public Departamento create(Departamento usuario) {
        return departamentoRepository.save(usuario);
    }

    @Override
    @Transactional
    public Optional<Departamento> update(Departamento usuario, Long id) {
        if (departamentoRepository.findById(id).isPresent()) {
            return Optional.ofNullable(departamentoRepository.save(usuario));
        } else {
            return null;
        }

    }

    @Override
    @Transactional
    public void delete(Long id) {

        departamentoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        departamentoRepository.deleteAll();
    }

 

}
