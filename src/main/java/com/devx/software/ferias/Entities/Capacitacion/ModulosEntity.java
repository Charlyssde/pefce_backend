package com.devx.software.ferias.Entities.Capacitacion;

import com.devx.software.ferias.Entities.Capacitacion.CapacitacionesEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "a_capacitacion__a_modulos")
public class ModulosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_capacitacion")
    private CapacitacionesEntity capacitacion;

    @OneToMany(mappedBy = "modulo")
    private Set<TemasEntity> temas;

    @Column
    private String nombre;

    @Column
    private String descripcion;

    @Column
    private Date createdAt;

    @Column
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

    public Set<TemasEntity> getTemas() {
        return temas;
    }

    public void setTemas(Set<TemasEntity> temas) {
        this.temas = temas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
