package com.devx.software.ferias.DTOs.PromocionDigital;

import java.util.List;

public class EmailDTO {

    private List<String> destinos;

    private String asunto;

    private List<String> mensaje;

    public List<String> getDestinos() {
        return destinos;
    }

    public void setDestinos(List<String> destinos) {
        this.destinos = destinos;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public List<String> getMensaje() {
        return mensaje;
    }

    public void setMensaje(List<String> mensaje) {
        this.mensaje = mensaje;
    }
}
