 
package org.una.tramites.fxapp.services;

import org.una.tramites.fxapp.dtos.Sesion;
 
public class SesionService {

    private static Sesion sesion;

    public static Sesion getSesion() {
        return sesion;
    }

    public static void setSesion(Sesion newSesion) {
         sesion = newSesion;
    }
 
}
