package com.devx.software.ferias.DTOs.Projects;



import com.devx.software.ferias.DTOs.Users.UserDTO;

import java.util.Date;


public class ProjectHistoryDTO {

    private Long id;
    private UserDTO usuarioId;
    private String accion;
    private Long tipoId;
    private Date createdAt;
    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDTO getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(UserDTO usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public Long getTipoId() {
        return tipoId;
    }

    public void setTipoId(Long tipoId) {
        this.tipoId = tipoId;
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
