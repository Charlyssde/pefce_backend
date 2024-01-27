package com.devx.software.ferias.DTOs.Capacitacion;

import com.devx.software.ferias.DTOs.Capacitacion.CapacitacionesDTO;
import com.devx.software.ferias.DTOs.Users.UserDTO;

import java.util.Date;

public class UsuarioCapacitacionDTO {

    private Long id;

    private UserDTO usuario;

    private CapacitacionesDTO capacitacion;

    private Boolean notificado;

    private Boolean concluyo;

    private Date fechaRegistro;

    private Date createdAt;

    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UserDTO usuario) {
        this.usuario = usuario;
    }

    public CapacitacionesDTO getCapacitacion() {
        return capacitacion;
    }

    public void setCapacitacion(CapacitacionesDTO capacitacion) {
        this.capacitacion = capacitacion;
    }

    public Boolean getNotificado() {
        return notificado;
    }

    public void setNotificado(Boolean notificado) {
        this.notificado = notificado;
    }

    public Boolean getConcluyo() {
        return concluyo;
    }

    public void setConcluyo(Boolean concluyo) {
        this.concluyo = concluyo;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
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
