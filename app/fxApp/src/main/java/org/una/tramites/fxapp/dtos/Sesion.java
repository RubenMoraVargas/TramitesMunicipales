package org.una.tramites.fxapp.dtos;

import java.util.Date;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class Sesion {

    public Sesion() {
        this.fechaRegistro = new Date();
    }

    private String token;
    private Usuario usuario;
    private List<PermisoOtorgado> permisos;
    @Setter(AccessLevel.NONE)
    private Date fechaRegistro;

}
