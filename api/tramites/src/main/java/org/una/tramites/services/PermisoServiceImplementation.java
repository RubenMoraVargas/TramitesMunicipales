package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.dtos.PermisoDTO;
import org.una.tramites.entities.Permiso;
import org.una.tramites.repositories.IPermisoRepository;
import org.una.tramites.utils.MapperUtils;

@Service
public class PermisoServiceImplementation implements IPermisoService {

    @Autowired
    private IPermisoRepository permisoRepository;

    private Optional<List<PermisoDTO>> listToDtoList(Optional<List<Permiso>> list) {
        if (list.isPresent()) {
            List<PermisoDTO> permisoDTO = MapperUtils.DtoListFromEntityList(list.get(), PermisoDTO.class);
            return Optional.ofNullable(permisoDTO);
        } else {
            return null;
        }
    }

    private Optional<PermisoDTO> oneToDto(Optional<Permiso> one) {
        if (one.isPresent()) {
            PermisoDTO permisoDTO = MapperUtils.DtoFromEntity(one.get(), PermisoDTO.class);
            return Optional.ofNullable(permisoDTO);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PermisoDTO> findById(Long id) {
        return oneToDto(permisoRepository.findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<PermisoDTO>> findByEstado(boolean estado) {
        return listToDtoList(permisoRepository.findByEstado(estado));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<PermisoDTO>> findByFechaRegistroBetween(Date startDate, Date endDate) {
        return listToDtoList(permisoRepository.findByFechaRegistroBetween(startDate, endDate));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PermisoDTO> findByCodigo(String codigo) {
        return oneToDto(permisoRepository.findByCodigo(codigo));
    }

    @Override
    @Transactional(readOnly = true)
    public Long countByEstado(boolean estado) {
        return permisoRepository.countByEstado(estado);
    }

    @Override
    @Transactional
    public PermisoDTO create(PermisoDTO permisoDTO) {
        Permiso permiso = MapperUtils.EntityFromDto(permisoDTO, Permiso.class);
        permiso = permisoRepository.save(permiso);
        return MapperUtils.DtoFromEntity(permiso, PermisoDTO.class);
    }

    @Override
    @Transactional
    public Optional<PermisoDTO> update(PermisoDTO permisoDTO, Long id) {
        if (permisoRepository.findById(id).isPresent()) {
            Permiso permiso = MapperUtils.EntityFromDto(permisoDTO, Permiso.class);
            permiso = permisoRepository.save(permiso);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(permiso, PermisoDTO.class));
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
