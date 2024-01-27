package com.devx.software.ferias.Entities.Minutas;

import com.devx.software.ferias.Entities.Users.UserEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "a_minuta__a_tarea")
public class MinutaTareaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_minuta")
    @JsonBackReference
    private MinutasEntity minuta;

    /*
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_usuario")
    private UserEntity usuario;
    */

    @Column
    private String nombreTarea;

    @Column
    private String entregable;

    @Column
    private Date fechaTermino;

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

    public MinutasEntity getMinuta() {
        return minuta;
    }

    public void setMinuta(MinutasEntity minuta) {
        this.minuta = minuta;
    }

    public String getNombreTarea() {
        return nombreTarea;
    }

    public void setNombreTarea(String nombreTarea) {
        this.nombreTarea = nombreTarea;
    }

    public String getEntregable() {
        return entregable;
    }

    public void setEntregable(String entregable) {
        this.entregable = entregable;
    }

    public Date getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(Date fechaTermino) {
        this.fechaTermino = fechaTermino;
    }

    /*
    public UserEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UserEntity usuario) {
        this.usuario = usuario;
    }

     */

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
