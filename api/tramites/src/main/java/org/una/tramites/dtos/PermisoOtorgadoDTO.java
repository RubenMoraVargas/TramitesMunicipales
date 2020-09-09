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
    private UsuarioDTO usuario;   
    private PermisoDTO permiso; 
    private boolean estado; 
    private Date fechaRegistro;   
     
}

