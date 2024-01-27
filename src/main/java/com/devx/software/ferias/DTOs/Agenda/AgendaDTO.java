package com.devx.software.ferias.DTOs.Agenda;

import com.devx.software.ferias.DTOs.Tasks.TaskDTO;
import com.devx.software.ferias.DTOs.Users.UserDTO;
import com.devx.software.ferias.Entities.Tasks.TaskEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AgendaDTO {
    private Long id;
    private String titulo;
    private String descripcion;
    private Date inicio;
    private Date fin;
    private Boolean diaCompleto;
    private String tipoEvento;
    private Boolean estatus;
    private Date createdAt;
    private Date updatedAt;
    private List<UserDTO> usuariosAgenda;
    private final List<TaskDTO> tareasAgenda = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public Boolean getDiaCompleto() {
        return diaCompleto;
    }

    public void setDiaCompleto(Boolean diaCompleto) {
        this.diaCompleto = diaCompleto;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
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

    public List<UserDTO> getUsuariosAgenda() {
        return usuariosAgenda;
    }

    public void setUsuariosAgenda(List<UserDTO> usuariosAgenda) {
        this.usuariosAgenda = usuariosAgenda;
    }
}
