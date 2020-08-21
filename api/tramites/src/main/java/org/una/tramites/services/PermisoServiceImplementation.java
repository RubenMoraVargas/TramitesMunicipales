package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.entities.Permiso; 
import org.una.tramites.repositories.IPermisoRepository;

@Service
public class PermisoServiceImplementation implements IPermisoService {

    @Autowired
    private IPermisoRepository permisoRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Permiso> findById(Long id) {
        return permisoRepository.findById(id);
    }
     
    @Override
    @Transactional(readOnly = true)
    public Optional<List<Permiso>> findByEstado(boolean estado) {
        return permisoRepository.findByEstado(estado);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Permiso>> findByFechaRegistroBetween(Date startDate, Date endDate) {
        return permisoRepository.findByFechaRegistroBetween(startDate, endDate);
    } 
    
    @Override
    @Transactional
    public Permiso create(Permiso Permiso) {
        return permisoRepository.save(Permiso);
    }

    @Override
    @Transactional
    public Optional<Permiso> update(Permiso Permiso, Long id) {
        if (permisoRepository.findById(id).isPresent()) {
            return Optional.ofNullable(permisoRepository.save(Permiso));
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {

        permisoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        permisoRepository.deleteAll();
    }

}
