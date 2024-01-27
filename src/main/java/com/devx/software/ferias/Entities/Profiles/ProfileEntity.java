package com.devx.software.ferias.Entities.Profiles;

import com.devx.software.ferias.Entities.Users.UserEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "a_perfiles")
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "area", nullable = false)
    private String area;

    @Column
    private Long nivel;

    @Column(name = "estatus", nullable = false, columnDefinition = "default true")
    private Boolean estatus;

    @Column
    private Long perfilId;

    @Column(name = "created_at", nullable = false, columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    private Date createdAt;

    @Column(name = "updated_at", nullable = true, columnDefinition = "timestamp")
    private Date updatedAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "perfilId", cascade = {CascadeType.REMOVE})
    private List<ProfileEntity> perfiles;

    @OneToMany(mappedBy = "perfil")
    private List<ProfileMenuEntity> permisos;

    @ManyToMany(mappedBy = "perfiles")
    @JsonBackReference
    private List<UserEntity> usuarios = new ArrayList<>();

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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Long getNivel() {
        return nivel;
    }

    public void setNivel(Long nivel) {
        this.nivel = nivel;
    }

    public Boolean getEstatus() {
        return estatus;
    }

    public void setEstatus(Boolean estatus) {
        this.estatus = estatus;
    }

    public Long getPerfilId() {
        return perfilId;
    }

    public void setPerfilId(Long perfilId) {
        this.perfilId = perfilId;
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

    public List<ProfileEntity> getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(List<ProfileEntity> perfiles) {
        this.perfiles = perfiles;
    }

    public List<ProfileMenuEntity> getPermisos() {
        return permisos;
    }

    public void setPermisos(List<ProfileMenuEntity> permisos) {
        this.permisos = permisos;
    }

    public List<UserEntity> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<UserEntity> usuarios) {
        this.usuarios = usuarios;
    }
}
