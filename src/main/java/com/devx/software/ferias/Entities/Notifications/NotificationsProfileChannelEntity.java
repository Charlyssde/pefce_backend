package com.devx.software.ferias.Entities.Notifications;

import com.devx.software.ferias.Entities.Profiles.ProfileEntity;

import javax.persistence.*;

@Entity
@Table(name = "a_notificaciones_canales_perfil")
public class NotificationsProfileChannelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "canal_id")
    private NotificationsChannelsEntity canal;

    @ManyToOne(optional = false)
    @JoinColumn(name = "perfil_id")
    private ProfileEntity perfil;

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

    public ProfileEntity getPerfil() {
        return perfil;
    }

    public void setPerfil(ProfileEntity perfil) {
        this.perfil = perfil;
    }
}
