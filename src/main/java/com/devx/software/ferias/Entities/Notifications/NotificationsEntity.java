package com.devx.software.ferias.Entities.Notifications;

import com.devx.software.ferias.Entities.Users.UserEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "a_notificaciones")
public class NotificationsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = true)
    @JoinColumn(name = "destinatario_id")
    private UserEntity destinatario;

    @ManyToOne(optional = true)
    @JoinColumn(name = "canal_id")
    private NotificationsChannelsEntity canal;

    @Column
    private String texto;

    @Column
    private Long tipo;

    @Column
    private Boolean vista;

    @Column
    private Date leida;

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

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Long getTipo() {
        return tipo;
    }

    public void setTipo(Long tipo) {
        this.tipo = tipo;
    }

    public Boolean getVista() {
        return vista;
    }

    public void setVista(Boolean vista) {
        this.vista = vista;
    }

    public Date getLeida() {
        return leida;
    }

    public void setLeida(Date leida) {
        this.leida = leida;
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
