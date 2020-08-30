 
package org.una.tramites.loaders;
 
public enum Permisos {
    CREAR_USUARIO("USU1"),
    MODIFICAR_USUARIO("USU2"),
    INACTIVAR_USUARIO("USU3"),
    CONSULTAR_USUARIO("USU4"),
    CREAR_DEPARTAMENTO("DEP1"),
    MODIFICAR_DEPARTAMENTO("DEP2"),
    INACTIVAR_DEPARTAMENTO("DEP3"),
    CONSULTAR_DEPARTAMENTO("DEP4"),
    REGISTRAR_TRAMITE("TRA1"),
    MODIFICAR_TRAMITE("TRA2"),
    INACTIVAR_TRAMITE("TRA3"),
    FINALIZAR_TRAMITE("TRA4"),
    CONSULTAR_TRAMITE("TRA5"),
    CONSULTAR_TODOS_TRAMITES("TRA6"),
    DISEÑAR_TRAMITES_VARIACIONES_REQUISITOS("TRD1"),
    CONSULTAR_REPORTES_TRANSACCIONES("TRU1");
//TODO: completar esta lista
    private String codigo;

    Permisos(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }
}