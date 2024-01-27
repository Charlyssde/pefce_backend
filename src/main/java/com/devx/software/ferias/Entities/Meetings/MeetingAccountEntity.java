package com.devx.software.ferias.Entities.Meetings;

import com.devx.software.ferias.Entities.Catalogs.CatalogsEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "a_meeting_cuentas")
public class MeetingAccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "areas_id", nullable = true)
    private CatalogsEntity areasId;
    @Column(nullable = true, name = "name")
    private String name;
    @Column(nullable = false, name = "zoom_user_id")
    private String zoomUserId;
    @Column(nullable = false, name = "zoom_user_pwd")
    private String zoomUserPwd;
    @Column(nullable = false, name = "zoom_account_id")
    private String zoomAccountId;
    @Column(nullable = false, name = "zoom_client_id")
    private String zoomClientId;
    @Column(nullable = false, name = "zoom_client_secret")
    private String zoomClientSecret;
    @Column(nullable = false, name = "zoom_secret_token_features")
    private String zoomSecretTokenFeatures;
    @Column(nullable = false, name = "zoom_secret_verification_features")
    private String zoomSecretVerificationFeatures;
    @Column(nullable = true)
    private Boolean activo;
    @Column(nullable = false)
    private Date createdAt;
    @Column(nullable = true)
    private Date updatedAt;
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "meetingCuentaId"
    )
    private Set<MeetingEntity> meetings = new HashSet<>();

    public void addMeeting(MeetingEntity meeting) {
        if (meeting != null) {
            if (meetings == null) {
                meetings = new HashSet<>();
            }
            meetings.add(meeting);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CatalogsEntity getAreasId() {
        return areasId;
    }

    public void setAreasId(CatalogsEntity areasId) {
        this.areasId = areasId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZoomUserId() {
        return zoomUserId;
    }

    public void setZoomUserId(String zoomUserId) {
        this.zoomUserId = zoomUserId;
    }

    public String getZoomUserPwd() {
        return zoomUserPwd;
    }

    public void setZoomUserPwd(String zoomUserPwd) {
        this.zoomUserPwd = zoomUserPwd;
    }

    public String getZoomAccountId() {
        return zoomAccountId;
    }

    public void setZoomAccountId(String zoomAccountId) {
        this.zoomAccountId = zoomAccountId;
    }

    public String getZoomClientId() {
        return zoomClientId;
    }

    public void setZoomClientId(String zoomClientId) {
        this.zoomClientId = zoomClientId;
    }

    public String getZoomClientSecret() {
        return zoomClientSecret;
    }

    public void setZoomClientSecret(String zoomClientSecret) {
        this.zoomClientSecret = zoomClientSecret;
    }

    public String getZoomSecretTokenFeatures() {
        return zoomSecretTokenFeatures;
    }

    public void setZoomSecretTokenFeatures(String zoomSecretTokenFeatures) {
        this.zoomSecretTokenFeatures = zoomSecretTokenFeatures;
    }

    public String getZoomSecretVerificationFeatures() {
        return zoomSecretVerificationFeatures;
    }

    public void setZoomSecretVerificationFeatures(String zoomSecretVerificationFeatures) {
        this.zoomSecretVerificationFeatures = zoomSecretVerificationFeatures;
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
}
