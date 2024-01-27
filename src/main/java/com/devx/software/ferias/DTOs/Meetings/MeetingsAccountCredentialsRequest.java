package com.devx.software.ferias.DTOs.Meetings;

public class MeetingsAccountCredentialsRequest {

    private String zoomAccountId;
    private String zoomClientId;
    private String zoomClientSecret;

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
}
