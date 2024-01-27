package com.devx.software.ferias.Entities.Meetings;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "a_meetings")
public class MeetingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "meeting_cuenta_id")
    private MeetingAccountEntity meetingCuentaId;
    @Column(name = "api_response")
    private String apiResponse;
    @Column(name = "activo")
    private Boolean activo;
    @Column(nullable = false)
    private Date createdAt;
    @Column(nullable = true)
    private Date updatedAt;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "meetingId", fetch = FetchType.LAZY)
    @JsonManagedReference
    private MeetingZoomEntity meetingZoom;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MeetingAccountEntity getMeetingCuentaId() {
        return meetingCuentaId;
    }

    public void setMeetingCuentaId(MeetingAccountEntity meetingCuentaId) {
        this.meetingCuentaId = meetingCuentaId;
    }

    public String getApiResponse() {
        return apiResponse;
    }

    public void setApiResponse(String apiResponse) {
        this.apiResponse = apiResponse;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
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

    public MeetingZoomEntity getMeetingZoom() {
        return meetingZoom;
    }

    public void setMeetingZoom(MeetingZoomEntity meetingZoom) {
        this.meetingZoom = meetingZoom;
    }
}
