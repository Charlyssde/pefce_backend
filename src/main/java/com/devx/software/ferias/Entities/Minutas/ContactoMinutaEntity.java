package com.devx.software.ferias.Entities.Minutas;

import com.devx.software.ferias.Entities.Minutas.MinutasEntity;
import com.devx.software.ferias.Entities.Capacitacion.ContactosEntity;

import javax.persistence.*;

@Entity
@Table(name = "a_minuta__a_contacto")
public class ContactoMinutaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_minuta")
    private MinutasEntity minuta;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_contacto")
    private ContactosEntity contacto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MinutasEntity getMinuta() {
        return minuta;
    }

    public void setMinuta(MinutasEntity minuta) {
        this.minuta = minuta;
    }

    public ContactosEntity getContacto() {
        return contacto;
    }

    public void setContacto(ContactosEntity contacto) {
        this.contacto = contacto;
    }
}
