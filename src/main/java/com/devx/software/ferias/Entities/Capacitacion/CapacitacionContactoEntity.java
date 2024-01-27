package com.devx.software.ferias.Entities.Capacitacion;

import com.devx.software.ferias.Entities.Capacitacion.ContactosEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "a_capacitacion__a_contacto")
public class CapacitacionContactoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "id_capacitacion")
    private CapacitacionesEntity capacitacion;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "id_contacto")
    private ContactosEntity contacto;


    private Boolean activo;

    private Date createdAt;

    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CapacitacionesEntity getCapacitacion() {
        return capacitacion;
    }

    public void setCapacitacion(CapacitacionesEntity capacitacion) {
        this.capacitacion = capacitacion;
    }

    public ContactosEntity getContacto() {
        return contacto;
    }

    public void setContacto(ContactosEntity contacto) {
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
