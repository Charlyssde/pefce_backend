package com.devx.software.ferias.DTOs.Agenda;

public class AgendaRequest {
    private AgendaDTO agenda;
    private Long userId;

    public AgendaDTO getAgenda() {
        return agenda;
    }

    public void setAgenda(AgendaDTO agenda) {
        this.agenda = agenda;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
