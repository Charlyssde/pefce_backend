package com.devx.software.ferias.DTOs.Projects;

import com.devx.software.ferias.DTOs.Users.UserListDTO;
import java.util.Date;


public class ProjectColaboratorDTO {

    private Long id;
    private UserListDTO usuarioId;
    private String rol;
    private boolean activo;
    private Date createdAt;
    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserListDTO getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(UserListDTO usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
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
