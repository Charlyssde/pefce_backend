package com.devx.software.ferias.DTOs.Meetings;

import com.devx.software.ferias.DTOs.Catalogs.CatalogsDTO;

public class MeetingAccountSelectDTO {
    private Long id;
    private CatalogsDTO areasId;
    private String name;
    private String zoomUserId;
    private String zoomUserPwd;
    private String zoomAccountId;
    private String zoomClientId;
    private String zoomClientSecret;
    private String zoomSecretTokenFeatures;
    private String zoomSecretVerificationFeatures;
    private Boolean activo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CatalogsDTO getAreasId() {
        return areasId;
    }

    public void setAreasId(CatalogsDTO areasId) {
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
}
