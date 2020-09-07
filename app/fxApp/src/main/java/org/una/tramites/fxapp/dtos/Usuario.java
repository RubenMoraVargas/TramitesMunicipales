package org.una.tramites.fxapp.dtos;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Usuario {

   private Long id;
    private String nombreCompleto;
    private String cedula;
    private boolean esJeFe;
    private boolean estado;
    private Departamento departamento;
    private Date fechaRegistro;
    private Date fechaModificacion;

}
