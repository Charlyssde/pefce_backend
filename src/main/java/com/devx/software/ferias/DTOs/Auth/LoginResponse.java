package com.devx.software.ferias.DTOs.Auth;

import com.devx.software.ferias.DTOs.Profiles.ProfileAuthDTO;
import com.devx.software.ferias.Entities.Logs.SessionEntity;

import java.util.List;

public class LoginResponse {

    private Long id;

    private String nombre;

    private String email;

    private String token;

    private SessionEntity logsSesion;

    private Boolean sesionActiva;

    private List<ProfileAuthDTO> perfiles;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public SessionEntity getLogsSesion() {
        return logsSesion;
    }

    public void setLogsSesion(SessionEntity logsSesion) {
        this.logsSesion = logsSesion;
    }

    public Boolean getSesionActiva() {
        return sesionActiva;
    }

    public void setSesionActiva(Boolean sesionActiva) {
        this.sesionActiva = sesionActiva;
    }

    public List<ProfileAuthDTO> getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(List<ProfileAuthDTO> perfiles) {
        this.perfiles = perfiles;
    }
}
