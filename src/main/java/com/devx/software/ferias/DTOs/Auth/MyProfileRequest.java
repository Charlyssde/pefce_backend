package com.devx.software.ferias.DTOs.Auth;

import com.devx.software.ferias.Entities.Users.UserEntity;

public class MyProfileRequest {
    private UserEntity usuario;
    private Boolean actualizarPassword;
    private String nuevaPassword;
    private String repetirPassword;

    public UserEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UserEntity usuario) {
        this.usuario = usuario;
    }

    public Boolean getActualizarPassword() {
        return actualizarPassword;
    }

    public void setActualizarPassword(Boolean actualizarPassword) {
        this.actualizarPassword = actualizarPassword;
    }

    public String getNuevaPassword() {
        return nuevaPassword;
    }

    public void setNuevaPassword(String nuevaPassword) {
        this.nuevaPassword = nuevaPassword;
    }

    public String getRepetirPassword() {
        return repetirPassword;
    }

    public void setRepetirPassword(String repetirPassword) {
        this.repetirPassword = repetirPassword;
    }
}
