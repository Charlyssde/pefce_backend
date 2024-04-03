package com.devx.software.ferias.Enums;

public enum TipoRespuesta {
    MUY_BUENA(1), BUENA(2), DEFICIENTE(3), MUY_DEFICIENTE(4);

    private int tipoRespuesta;

    TipoRespuesta(int tipoRespuesta) {
        this.tipoRespuesta = tipoRespuesta;
    }

    public int getTipoRespuesta() {
        return tipoRespuesta;
    }
}
