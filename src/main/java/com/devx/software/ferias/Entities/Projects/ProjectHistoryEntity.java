package com.devx.software.ferias.Entities.Projects;

import com.devx.software.ferias.Entities.Meetings.MeetingEntity;
import com.devx.software.ferias.Entities.Users.UserEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "a_proyectos_historico")
public class ProjectHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UserEntity usuarioId;

    @Column(name = "accion", nullable = false)
    private String accion;

    @Column(name = "tipo_id", nullable = true)
    private Long tipoId;

    @Column(nullable = false, columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    private Date createdAt;

    @Column(nullable = true)
    private Date updatedAt;

    @Column(name = "is_file", nullable = false)
    private boolean isFile;

    @ManyToMany
    @JoinTable(
            name = "a_proyecto_historico__a_meeting",
            joinColumns = @JoinColumn(name = "proyecto_historico_id"),
            inverseJoinColumns = @JoinColumn(name = "meeting_id")
    )
    private List<MeetingEntity> meetings = new ArrayList<>();

    public void addMeetings(MeetingEntity meeting){
        this.meetings.add(meeting);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(UserEntity usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public Long getTipoId() {
        return tipoId;
    }

    public void setTipoId(Long tipoId) {
        this.tipoId = tipoId;
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

    public List<MeetingEntity> getMeetings() {
        return meetings;
    }

    public void setMeetings(List<MeetingEntity> meeting) {
        this.meetings = meeting;
    }

    public boolean isFile() {
        return isFile;
    }

    public void setFile(boolean isFile) {
        this.isFile = isFile;
    }
    
}
