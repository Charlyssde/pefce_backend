package com.devx.software.ferias.Entities.Notifications;

import javax.persistence.*;

@Entity
@Table(name = "a_notificaciones_acciones")
public class NotificationsActionsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String accion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
}
