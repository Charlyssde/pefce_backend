package com.devx.software.ferias.Enums;

public enum EstatuSolicitudesEnum {
    ENVIADA(1), ACEPTADA(2), CANCELADA(3), REZACHADA(4), FINALIZADA(5), DEVUELTA(6);


    private int status;

    EstatuSolicitudesEnum(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
