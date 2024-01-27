package com.devx.software.ferias.DTOs.Events;

import com.devx.software.ferias.DTOs.Capacitacion.ContactosPageDTO;

import java.util.Date;

public class EventContactPageDTO {

    private Long id;

    private EventsDTO evento;

    private ContactosPageDTO contacto;

    private Boolean activo;

    private Date createdAt;

    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EventsDTO getEvento() {
        return evento;
    }

    public void setEvento(EventsDTO evento) {
        this.evento = evento;
    }

    public ContactosPageDTO getContacto() {
        return contacto;
    }

    public void setContacto(ContactosPageDTO contacto) {
        this.contacto = contacto;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
