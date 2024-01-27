package com.devx.software.ferias.Entities.Capacitacion;

import com.devx.software.ferias.Entities.Capacitacion.PreguntasEntity;
import com.devx.software.ferias.Entities.Capacitacion.ModulosEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "a_capacitacion__a_temas")
public class TemasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_modulo")
    @JsonBackReference
    private ModulosEntity modulo;

    @OneToMany(mappedBy = "tema")
    private Set<PreguntasEntity> preguntas;

    @Column
    private String nombre;

    @Column
    private String descripcion;

    @Column
    private String tipoRecurso;

    @Column
    private String recurso;

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

    public ModulosEntity getModulo() {
        return modulo;
    }

    public void setModulo(ModulosEntity modulo) {
        this.modulo = modulo;
    }

    public Set<PreguntasEntity> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(Set<PreguntasEntity> preguntas) {
        this.preguntas = preguntas;
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

    public String getTipoRecurso() {
        return tipoRecurso;
    }

    public void setTipoRecurso(String tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }

    public String getRecurso() {
        return recurso;
    }

    public void setRecurso(String recurso) {
        this.recurso = recurso;
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
