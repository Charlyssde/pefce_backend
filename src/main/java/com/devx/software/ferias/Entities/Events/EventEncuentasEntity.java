/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devx.software.ferias.Entities.Events;

import com.devx.software.ferias.Entities.Capacitacion.CapacitacionesEntity;
import com.devx.software.ferias.Entities.Capacitacion.ContactosEntity;
import com.devx.software.ferias.Entities.Encuestas.EncuestaEntity;
import com.devx.software.ferias.Entities.Encuestas.Encuestas;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author domingo
 */

@Entity
@Table(name = "eventos_encuestas")
public class EventEncuentasEntity {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "id_evento")
    private EventEntity evento;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "id_encuesta")
    private Encuestas encuesta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EventEntity getEvento() {
        return evento;
    }

    public void setEvento(EventEntity evento) {
        this.evento = evento;
    }

    public Encuestas getEncuesta() {
        return encuesta;
    }

    public void setEncuesta(Encuestas encuesta) {
        this.encuesta = encuesta;
    }
    
    
}
