package com.devx.software.ferias.DTOs.Users;

import com.devx.software.ferias.Entities.Profiles.ProfileEntity;

import java.util.List;

public class UserRequestDTO {

    private FormResourcesUserDTO user;

    private Boolean updatePassword;

    private String newPassword;

    private String repeatPassword;

    private List<ProfileEntity> profiles;

    public FormResourcesUserDTO getUser() {
        return user;
    }

    public void setUser(FormResourcesUserDTO user) {
        this.user = user;
    }

    public Boolean getUpdatePassword() {
        return updatePassword;
    }

    public void setUpdatePassword(Boolean updatePassword) {
        this.updatePassword = updatePassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public List<ProfileEntity> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<ProfileEntity> profiles) {
        this.profiles = profiles;
    }
}
