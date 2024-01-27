package com.devx.software.ferias.DTOs.Auth;

public class LogoutRequest {
    private Long sessionId;

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }
}
