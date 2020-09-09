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
public class UsuarioDTO {

    private Long id;
    private String nombreCompleto;
    private String cedula;
    private boolean estado;
    private Date fechaRegistro;
    private Date fechaModificacion;
    private DepartamentoDTO departamento;
    private boolean esJeFe;
    private String passwordEncriptado;

}
