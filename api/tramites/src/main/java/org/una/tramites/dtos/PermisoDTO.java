package org.una.tramites.dtos;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PermisoDTO {

    private Long id;
    private String codigo;
    private String descripcion;
    private Date fechaRegistro;
    private Date fechaModificacion;
    private boolean estado;
}
