package org.una.tramites.dtos;
 
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

 
@Data
@AllArgsConstructor
@NoArgsConstructor 
public class UsuarioDTO {
 
    private Long id; 
    private String nombreCompleto;   
    private String cedula; 
    private byte estado; 
    private Date fechaRegistro; 
    private Date fechaModificacion; 
    private byte esJeFe;
     
}

