package com.devx.software.ferias.Enums;

public enum TipoAgendaEnum {
    PERSONAL("PERSONAL"),
    MEETING("MEETING"),
    TAREA("TAREA"),
    EVENTO("EVENTO"),
    CAPACITACION("CAPACITACIÓN");

    private String tipo;

    TipoAgendaEnum(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
