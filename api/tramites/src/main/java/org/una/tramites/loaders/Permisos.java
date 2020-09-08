 
package org.una.tramites.loaders;
 
public enum Permisos {
    USUARIO_CREAR("USU1"),
    USUARIO_MODIFICAR("USU2"),
    USUARIO_INACTIVAR("USU3"),
    USUARIO_CONSULTAR("USU4"),
    USUARIO_CONSULTAR_TODO("USU5"),
    USUARIO_ELIMINAR("USU6"), 
    USUARIO_ELIMINAR_TODO("USU7"),
    DEPARTAMENTO_CREAR("DEP1"),
    DEPARTAMENTO_MODIFICAR("DEP2"),
    DEPARTAMENTO_INACTIVAR("DEP3"),
    DEPARTAMENTO_CONSULTAR("DEP4"),
    TRAMITE_REGISTRAR("TRA1"),
    TRAMITE_MODIFICAR("TRA2"),
    TRAMITE_INACTIVAR("TRA3"),
    TRAMITE_FINALIZAR("TRA4"),
    TRAMITE_CONSULTAR("TRA5"),
    TRAMITE_CONSULTAR_TODO("TRA6"),
    TRAMITE_DISEÑAR("TRD1"),
    TRANSACCION_CONSULTAR("TRU1");
//TODO: completar esta lista
    private String codigo;

    Permisos(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }
}