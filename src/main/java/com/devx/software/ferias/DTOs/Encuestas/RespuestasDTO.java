package com.devx.software.ferias.DTOs.Encuestas;

public class RespuestasDTO {

    private long pregunta;
    private long respuesta;

    public RespuestasDTO() {
    }

    public RespuestasDTO(long pregunta, long respuesta) {
        this.pregunta = pregunta;
        this.respuesta = respuesta;
    }

    public long getPregunta() {
        return pregunta;
    }

    public void setPregunta(long pregunta) {
        this.pregunta = pregunta;
    }

    public long getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(long respuesta) {
        this.respuesta = respuesta;
    }

    @Override
    public String toString() {
        return "RespuestasDTO{" +
                "pregunta=" + pregunta +
                ", respuesta=" + respuesta +
                '}';
    }

}
