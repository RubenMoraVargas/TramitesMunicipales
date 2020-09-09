package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.dtos.DepartamentoDTO;
import org.una.tramites.entities.Departamento;
import org.una.tramites.repositories.IDepartamentoRepository;
import org.una.tramites.utils.MapperUtils;

@Service
public class DepartamentoServiceImplementation implements IDepartamentoService {

    @Autowired
    private IDepartamentoRepository departamentoRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<DepartamentoDTO>> findAll() {
        List<Departamento> result = departamentoRepository.findAll();
        List<DepartamentoDTO> departamentosDTO
                = MapperUtils.DtoListFromEntityList(result, DepartamentoDTO.class);

        return Optional.ofNullable(departamentosDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DepartamentoDTO> findById(Long id) {
        Optional<Departamento> result = departamentoRepository.findById(id);
        if (result.isPresent()) {
            DepartamentoDTO departamentoDTO = MapperUtils.DtoFromEntity(result.get(), DepartamentoDTO.class);
            return Optional.ofNullable(departamentoDTO);
        } else {
            return null;
        }
    }

    @Override
    public Optional<List<DepartamentoDTO>> findByEstado(boolean estado) {
        Optional<List<Departamento>> result = departamentoRepository.findByEstado(estado);
        if (result.isPresent()) {
            List<DepartamentoDTO> departamentosDTO
                    = MapperUtils.DtoListFromEntityList(result.get(), DepartamentoDTO.class);

            return Optional.ofNullable(departamentosDTO);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public DepartamentoDTO create(DepartamentoDTO departamentoDTO) {
        Departamento departamento = MapperUtils.EntityFromDto(departamentoDTO, Departamento.class);
        departamento = departamentoRepository.save(departamento);
        return MapperUtils.EntityFromDto(departamento, DepartamentoDTO.class);
    }

    @Override
    @Transactional
    public Optional<DepartamentoDTO> update(DepartamentoDTO departamentoDTO, Long id) {
        if (departamentoRepository.findById(id).isPresent()) {
            Departamento departamento = MapperUtils.EntityFromDto(departamentoDTO, Departamento.class);
            departamento = departamentoRepository.save(departamento);
            return Optional.ofNullable(MapperUtils.EntityFromDto(departamento, DepartamentoDTO.class));
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
