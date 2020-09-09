package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.dtos.TransaccionDTO;
import org.una.tramites.entities.Transaccion;
import org.una.tramites.repositories.ITransaccionRepository;
import org.una.tramites.utils.MapperUtils;

@Service
public class TransaccionServiceImplementation implements ITransaccionService {

    @Autowired
    private ITransaccionRepository transaccionRepository;

    private Optional<List<TransaccionDTO>> listToDtoList(Optional<List<Transaccion>> list) {
        if (list.isPresent()) {
            List<TransaccionDTO> usuariosDTO = MapperUtils.DtoListFromEntityList(list.get(), TransaccionDTO.class);
            return Optional.ofNullable(usuariosDTO);
        } else {
            return null;
        }
    }

    private Optional<TransaccionDTO> oneToDto(Optional<Transaccion> one) {
        if (one.isPresent()) {
            TransaccionDTO transaccionDTO = MapperUtils.DtoFromEntity(one.get(), TransaccionDTO.class);
            return Optional.ofNullable(transaccionDTO);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TransaccionDTO> findById(Long id) {
        return oneToDto(transaccionRepository.findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TransaccionDTO>> findByUsuarioIdAndFechaRegistroBetween(Long usuarioId, Date startDate, Date endDate) {
        return listToDtoList(transaccionRepository.findByUsuarioIdAndFechaRegistroBetween(usuarioId, startDate, endDate));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TransaccionDTO>> findByPermisoIdAndFechaRegistroBetween(Long permisoId, Date startDate, Date endDate) {
        return listToDtoList(transaccionRepository.findByPermisoIdAndFechaRegistroBetween(permisoId, startDate, endDate));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TransaccionDTO>> findByObjetoAndFechaRegistroBetween(String objeto, Date startDate, Date endDate) {
        return listToDtoList(transaccionRepository.findByObjetoAndFechaRegistroBetween(objeto, startDate, endDate));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TransaccionDTO>> findByFechaRegistroBetween(Date startDate, Date endDate) {
        return listToDtoList(transaccionRepository.findByFechaRegistroBetween(startDate, endDate));
    }

    @Override
    @Transactional
    public TransaccionDTO create(TransaccionDTO TransaccionDTO) {
        Transaccion transaccion = MapperUtils.EntityFromDto(TransaccionDTO, Transaccion.class);
        transaccion = transaccionRepository.save(transaccion);
        return MapperUtils.DtoFromEntity(transaccion, TransaccionDTO.class);
    }

}
