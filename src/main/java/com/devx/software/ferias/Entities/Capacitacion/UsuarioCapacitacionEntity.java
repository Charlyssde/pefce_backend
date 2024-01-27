package com.devx.software.ferias.Entities.Capacitacion;

import com.devx.software.ferias.Entities.Capacitacion.CapacitacionesEntity;
import com.devx.software.ferias.Entities.Users.UserEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "a_capacitacion__a_usuario")
public class UsuarioCapacitacionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_usuario")
    private UserEntity usuario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_capacitacion")
    private CapacitacionesEntity capacitacion;

    @Column
    private Boolean notificado;

    @Column
    private Boolean concluyo;

    @Column
    private Date fechaRegistro;

    @Column
    private String uuidFinalizado;

    @Column
    private Date createdAt;

    @Column
    private Date updatedAt;
    @Column(nullable = true)
    private Boolean constancia;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isConstancia() {
        return constancia;
    }

    public void setConstancia(Boolean constancia) {
        this.constancia = constancia;
    }

    public UserEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UserEntity usuario) {
        this.usuario = usuario;
    }

    public CapacitacionesEntity getCapacitacion() {
        return capacitacion;
    }

    public void setCapacitacion(CapacitacionesEntity capacitacion) {
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

    public String getUuidFinalizado() {
        return uuidFinalizado;
    }

    public void setUuidFinalizado(String uuidFinalizado) {
        this.uuidFinalizado = uuidFinalizado;
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
