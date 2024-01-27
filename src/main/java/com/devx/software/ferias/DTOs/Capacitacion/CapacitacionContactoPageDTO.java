package com.devx.software.ferias.DTOs.Capacitacion;

import java.util.Date;

public class CapacitacionContactoPageDTO {

    private Long id;

    private CapacitacionesDTO capacitacion;

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

    public CapacitacionesDTO getCapacitacion() {
        return capacitacion;
    }

    public void setCapacitacion(CapacitacionesDTO capacitacion) {
        this.capacitacion = capacitacion;
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
