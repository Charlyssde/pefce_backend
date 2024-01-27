package com.devx.software.ferias.Entities.Notifications;


import com.devx.software.ferias.Entities.Users.UserEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "a_notificaciones_cau")
public class NotificationsCauEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "accion_id")
    private NotificationsActionsEntity accion;

    @ManyToOne(optional = true)
    @JoinColumn(name = "destinatario_id")
    private UserEntity destinatario;

    @ManyToOne(optional = true)
    @JoinColumn(name = "canal")
    private NotificationsChannelsEntity canal;

    @Column
    private Long tipo;

    @Column
    private Date createdAt;

    @Column
    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NotificationsActionsEntity getAccion() {
        return accion;
    }

    public void setAccion(NotificationsActionsEntity accion) {
        this.accion = accion;
    }

    public UserEntity getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(UserEntity destinatario) {
        this.destinatario = destinatario;
    }

    public NotificationsChannelsEntity getCanal() {
        return canal;
    }

    public void setCanal(NotificationsChannelsEntity canal) {
        this.canal = canal;
    }

    public Long getTipo() {
        return tipo;
    }

    public void setTipo(Long tipo) {
        this.tipo = tipo;
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
