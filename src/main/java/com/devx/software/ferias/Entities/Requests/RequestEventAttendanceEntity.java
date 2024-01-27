package com.devx.software.ferias.Entities.Requests;

import com.devx.software.ferias.Entities.Events.EventEntity;
import com.devx.software.ferias.Entities.Users.UserEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "a_solicitudes_asistencia_evento")
public class RequestEventAttendanceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "evento_id")
    private EventEntity eventoId;

    @OneToOne
    @JoinColumn(name = "usuario_contacto_id")
    private UserEntity usuarioContactoId;

    @Column(name = "tipo_participacion")
    private String tipoParticipacion;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at", nullable = true)
    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EventEntity getEventoId() {
        return eventoId;
    }

    public void setEventoId(EventEntity eventoId) {
        this.eventoId = eventoId;
    }

    public UserEntity getUsuarioContactoId() {
        return usuarioContactoId;
    }

    public void setUsuarioContactoId(UserEntity usuarioContactoId) {
        this.usuarioContactoId = usuarioContactoId;
    }

    public String getTipoParticipacion() {
        return tipoParticipacion;
    }

    public void setTipoParticipacion(String tipoParticipacion) {
        this.tipoParticipacion = tipoParticipacion;
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
