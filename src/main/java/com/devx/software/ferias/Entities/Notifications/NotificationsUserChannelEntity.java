package com.devx.software.ferias.Entities.Notifications;

import com.devx.software.ferias.Entities.Users.UserEntity;

import javax.persistence.*;

@Entity
@Table(name = "a_notificaciones_canales_usuario")
public class NotificationsUserChannelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "canal_id")
    private NotificationsChannelsEntity canal;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id")
    private UserEntity usuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NotificationsChannelsEntity getCanal() {
        return canal;
    }

    public void setCanal(NotificationsChannelsEntity canal) {
        this.canal = canal;
    }

    public UserEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UserEntity usuario) {
        this.usuario = usuario;
    }
}
