package com.devx.software.ferias.DTOs.Auth;

public class RefreshTokenByUserIdRequest {
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
