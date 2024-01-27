package com.devx.software.ferias.Entities.Capacitacion;

import com.devx.software.ferias.Entities.Encuestas.EncuestaEntity;
//import com.devx.software.ferias.zx_entities.ReunionesEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "a_capacitaciones")
public class CapacitacionesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombre;

    @Column
    private Date fechaInicio;

    @Column
    private Date fechaFin;

    @Column
    private Boolean activo;

    @Column
    private Date createdAt;

    @Column
    private Date updatedAt;

    @Column
    private String descripcion;

    @Column(nullable = true)
    private String imagenPerfil;

    @Column(nullable = true)
    private String portada;

    @Column
    private String subarea;

    @Column
    private Long tipo;
/*
    @OneToOne(optional = true, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "link", nullable = true)
    private ReunionesEntity link;
*/
    @Column(nullable = true)
    private String lugar;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagenPerfil() {
        return imagenPerfil;
    }

    public void setImagenPerfil(String imagenPerfil) {
        this.imagenPerfil = imagenPerfil;
    }

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }

    public String getSubarea() {
        return subarea;
    }

    public void setSubarea(String subarea) {
        this.subarea = subarea;
    }

    public Long getTipo() {
        return tipo;
    }

    public void setTipo(Long tipo) {
        this.tipo = tipo;
    }
/*
    public ReunionesEntity getLink() {
        return link;
    }

    public void setLink(ReunionesEntity link) {
        this.link = link;
    }
*/
    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }
}
