package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.tramites.entities.Transaccion;

public interface ITransaccionService {

    public Optional<Transaccion> findById(Long id);

    public Optional<List<Transaccion>> findByUsuarioIdAndFechaRegistroBetween(Long usuarioId, Date startDate, Date endDate);

    public Optional<List<Transaccion>> findByPermisoIdAndFechaRegistroBetween(Long permisoId, Date startDate, Date endDate);

    public Optional<List<Transaccion>> findByObjetoAndFechaRegistroBetween(String objeto, Date startDate, Date endDate);

    public Optional<List<Transaccion>> findByFechaRegistroBetween(Date startDate, Date endDate);

    public Transaccion create(Transaccion transaccion);
}
