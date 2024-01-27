package com.devx.software.ferias.DTOs.Tasks;

import com.devx.software.ferias.DTOs.Agenda.AgendaDTO;
import com.devx.software.ferias.DTOs.Users.UserListDTO;

import java.util.Date;
import java.util.List;


public class TaskListDTO {

    private Long id;
    private UserListDTO usuarioId;
    private String tarea;

    private String descripcion;
    private String entregable;
    private Date fechaInicio;
    private Date fechaTermino;
    private Boolean estatus;
    private Date createdAt;
    private Date updatedAt;
    private List<UserListDTO> usuarios;
    private List<AgendaDTO> tareasAgenda;

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

    public String getTarea() {
        return tarea;
    }

    public void setTarea(String tarea) {
        this.tarea = tarea;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEntregable() {
        return entregable;
    }

    public void setEntregable(String entregable) {
        this.entregable = entregable;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(Date fechaTermino) {
        this.fechaTermino = fechaTermino;
    }

    public Boolean getEstatus() {
        return estatus;
    }

    public void setEstatus(Boolean estatus) {
        this.estatus = estatus;
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

    public List<UserListDTO> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<UserListDTO> usuarios) {
        this.usuarios = usuarios;
    }

    public List<AgendaDTO> getTareasAgenda() {
        return tareasAgenda;
    }

    public void setTareasAgenda(List<AgendaDTO> tareasAgenda) {
        this.tareasAgenda = tareasAgenda;
    }
}
