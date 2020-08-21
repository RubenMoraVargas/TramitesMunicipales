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
public class PermisoOtorgadoDTO {
 
    private Long id;   
    private Long usuarioId;   
    private Long permisoId; 
    private boolean estado; 
    private Date fechaRegistro;   
     
}

