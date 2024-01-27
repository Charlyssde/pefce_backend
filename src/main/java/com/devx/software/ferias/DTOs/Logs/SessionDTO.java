package com.devx.software.ferias.DTOs.Logs;

import com.devx.software.ferias.DTOs.Profiles.ProfileListDTO;
import com.devx.software.ferias.DTOs.Profiles.ProfileWithoutPermissionsDTO;
import com.devx.software.ferias.DTOs.Users.UserDTO;

import java.util.Date;

public class SessionDTO {
    private Long id;
    private UserDTO usuario;
    private Long perfil;
    private ProfileListDTO profile;
    private Date sesionInicio;
    private Date sesionFin;
    private Boolean estatus;

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

    public ProfileListDTO getProfile() {
        return profile;
    }

    public void setProfile(ProfileListDTO profile) {
        this.profile = profile;
    }
}
