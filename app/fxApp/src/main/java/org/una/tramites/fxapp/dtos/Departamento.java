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
public class Departamento {

    @Setter(AccessLevel.NONE)
    private Long id;
    private String nombre;
    private boolean estado;
    @Setter(AccessLevel.NONE)
    private Date fechaRegistro;
    @Setter(AccessLevel.NONE)
    private Date fechaModificacion;

}
