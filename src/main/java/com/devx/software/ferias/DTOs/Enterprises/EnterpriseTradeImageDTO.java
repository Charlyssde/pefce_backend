package com.devx.software.ferias.DTOs.Enterprises;

import com.devx.software.ferias.DTOs.Files.FileDTO;

import java.util.Date;

public class EnterpriseTradeImageDTO {

    private Long id;

    private String eslogan;

    private String banner1;

    private String banner2;

    private FileDTO logotipoId;

    private FileDTO videoId;

    private Boolean estatus;

    private Date createdAt;

    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEslogan() {
        return eslogan;
    }

    public void setEslogan(String eslogan) {
        this.eslogan = eslogan;
    }

    public String getBanner1() {
        return banner1;
    }

    public void setBanner1(String banner1) {
        this.banner1 = banner1;
    }

    public String getBanner2() {
        return banner2;
    }

    public void setBanner2(String banner2) {
        this.banner2 = banner2;
    }

    public FileDTO getLogotipoId() {
        return logotipoId;
    }

    public void setLogotipoId(FileDTO logotipoId) {
        this.logotipoId = logotipoId;
    }

    public FileDTO getVideoId() {
        return videoId;
    }

    public void setVideoId(FileDTO videoId) {
        this.videoId = videoId;
    }

    public Boolean getEstatus() {
        return estatus;
    }

    public void setEstatus(Boolean estatus) {
        this.estatus = estatus;
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
