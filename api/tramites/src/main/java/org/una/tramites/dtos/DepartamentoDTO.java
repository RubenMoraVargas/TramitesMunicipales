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
public class DepartamentoDTO {
 
    private Long id; 
    private String nombre;  
    private boolean estado; 
    private Date fechaRegistro; 
    private Date fechaModificacion;  
     
}

