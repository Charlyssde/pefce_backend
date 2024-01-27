package com.devx.software.ferias.Entities.Logs;

import com.devx.software.ferias.Entities.Users.UserEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "a_sesiones")
public class SessionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UserEntity usuario;


    @Column(name = "perfil_id", nullable = true)
    private Long perfil;

    @Column(name = "sesion_inicio", nullable = false, columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    private Date sesionInicio;

    @Column(name = "sesion_fin", nullable = false, columnDefinition = "timestamp")
    private Date sesionFin;

    @Column(name = "estatus", nullable = false, columnDefinition = "default false")
    private Boolean estatus;

    public SessionEntity() {
    }

    public SessionEntity(UserEntity usuario, Long perfil, Date sesionInicio, Date sesionFin, Boolean estatus) {
        this.usuario = usuario;
        this.perfil = perfil;
        this.sesionInicio = sesionInicio;
        this.sesionFin = sesionFin;
        this.estatus = estatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UserEntity usuario) {
        this.usuario = usuario;
    }

    public Long getPerfil() {
        return perfil;
    }

    public void setPerfil(Long perfil) {
        this.perfil = perfil;
    }

    public Date getSesionInicio() {
        return sesionInicio;
    }

    public void setSesionInicio(Date sesionInicio) {
        this.sesionInicio = sesionInicio;
    }

    public Date getSesionFin() {
        return sesionFin;
    }

    public void setSesionFin(Date sesionFin) {
        this.sesionFin = sesionFin;
    }

    public Boolean getEstatus() {
        return estatus;
    }

    public void setEstatus(Boolean estatus) {
        this.estatus = estatus;
    }
}
