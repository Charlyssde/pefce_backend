package com.devx.software.ferias.Entities.Users;

import com.devx.software.ferias.Entities.Profiles.ProfileEntity;

import javax.persistence.*;

@Entity
@Table(name = "a_usuario__a_perfil")
@IdClass(UserProfileKey.class)
public class UserProfileEntity {
    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id")
    private UserEntity usuario;

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "perfil_id")
    private ProfileEntity perfil;

    public UserEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UserEntity usuario) {
        this.usuario = usuario;
    }

    public ProfileEntity getPerfil() {
        return perfil;
    }

    public void setPerfil(ProfileEntity perfil) {
        this.perfil = perfil;
    }
}
