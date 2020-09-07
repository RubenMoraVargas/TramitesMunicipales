package org.una.tramites.fxapp.dtos;

import java.util.Date;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
class Permiso {

    @Setter(AccessLevel.NONE)
    private Long id;
    private Long usuarioId;
    private Long permisoId;
    private boolean estado;
    @Setter(AccessLevel.NONE)
    private Date fechaRegistro;
}
