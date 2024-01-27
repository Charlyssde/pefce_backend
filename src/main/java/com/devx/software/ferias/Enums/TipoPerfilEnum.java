package com.devx.software.ferias.Enums;

public enum TipoPerfilEnum {
    EMPRESA("empresa"), ROOT("root"), INSTITUCION("institucion");

    private String tipo;

    TipoPerfilEnum(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
