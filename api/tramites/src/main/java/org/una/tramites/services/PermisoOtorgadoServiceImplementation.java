package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.dtos.PermisoOtorgadoDTO;
import org.una.tramites.entities.PermisoOtorgado;
import org.una.tramites.repositories.IPermisoOtorgadoRepository;
import org.una.tramites.utils.MapperUtils;

@Service
public class PermisoOtorgadoServiceImplementation implements IPermisoOtorgadoService {

    @Autowired
    private IPermisoOtorgadoRepository permisoOtorgadoRepository;

        private Optional<List<PermisoOtorgadoDTO>> listToDtoList(List<PermisoOtorgado> list) {
        if (list != null) {
            List<PermisoOtorgadoDTO> usuariosDTO = MapperUtils.DtoListFromEntityList(list, PermisoOtorgadoDTO.class);
            return Optional.ofNullable(usuariosDTO);
        } else {
            return null;
        }
    }

    private Optional<List<PermisoOtorgadoDTO>> listToDtoList(Optional<List<PermisoOtorgado>> list) {
        if (list.isPresent()) {
            return listToDtoList(list.get());
        } else {
            return null;
        }
    }

    private Optional<PermisoOtorgadoDTO> oneToDto(Optional<PermisoOtorgado> one) {
        if (one.isPresent()) {
            PermisoOtorgadoDTO usuarioDTO = MapperUtils.DtoFromEntity(one.get(), PermisoOtorgadoDTO.class);
            return Optional.ofNullable(usuarioDTO);
        } else {
            return null;
        }
    }
    
    
    
    @Override
    @Transactional(readOnly = true)
    public Optional<PermisoOtorgadoDTO> findById(Long id) {
        return oneToDto(permisoOtorgadoRepository.findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<PermisoOtorgadoDTO>> findByUsuarioId(Long usuarioId) {
        return listToDtoList(permisoOtorgadoRepository.findByUsuarioId(usuarioId));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<PermisoOtorgadoDTO>> findByPermisoId(Long permisoId) {
        return listToDtoList(permisoOtorgadoRepository.findByPermisoId(permisoId));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<PermisoOtorgadoDTO>> findByUsuarioIdAndEstado(Long usuarioId, boolean estado) {
        return listToDtoList(permisoOtorgadoRepository.findByUsuarioIdAndEstado(usuarioId, estado));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<PermisoOtorgadoDTO>> findByPermisoIdAndEstado(Long permisoId, boolean estado) {
        return listToDtoList(permisoOtorgadoRepository.findByPermisoIdAndEstado(permisoId, estado));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<PermisoOtorgadoDTO>> findByFechaRegistroBetween(Date startDate, Date endDate) {
        return listToDtoList(permisoOtorgadoRepository.findByFechaRegistroBetween(startDate, endDate));
    }
 
    @Override
    @Transactional
    public PermisoOtorgadoDTO create(PermisoOtorgadoDTO permisoOtorgadoDTO) { 
        PermisoOtorgado permisoOtorgado = MapperUtils.EntityFromDto(permisoOtorgadoDTO, PermisoOtorgado.class);
        permisoOtorgado = permisoOtorgadoRepository.save(permisoOtorgado);
        return MapperUtils.DtoFromEntity(permisoOtorgado, PermisoOtorgadoDTO.class);
    }

    @Override
    @Transactional
    public Optional<PermisoOtorgadoDTO> update(PermisoOtorgadoDTO permisoOtorgadoDTO, Long id) {
        if (permisoOtorgadoRepository.findById(id).isPresent()) { 
            PermisoOtorgado permisoOtorgado  = MapperUtils.EntityFromDto(permisoOtorgadoDTO, PermisoOtorgado.class);
            permisoOtorgado = permisoOtorgadoRepository.save(permisoOtorgado);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(permisoOtorgado, PermisoOtorgadoDTO.class));
        } else {
            return null;
        } 
    }

    @Override
    @Transactional
    public void delete(Long id) { 
        permisoOtorgadoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        permisoOtorgadoRepository.deleteAll();
    }

}
