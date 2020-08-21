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
public class TransaccionDTO {
 
    private Long id; 
    private String objeto;  
    private String informacion;   
    private Date fechaRegistro;   
    private Long permisoOtorgadoId;  
     
}

