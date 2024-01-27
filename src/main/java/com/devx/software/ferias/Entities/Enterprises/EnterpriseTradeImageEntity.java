package com.devx.software.ferias.Entities.Enterprises;

import com.devx.software.ferias.Entities.Files.FileEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "a_empresa_imagen_comercial")
public class EnterpriseTradeImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "eslogan", nullable = true)
    private String eslogan;

    @Column(name = "banner1", nullable = true)
    private String banner1;

    @Column(name = "banner2", nullable = true)
    private String banner2;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "logotipo_id", referencedColumnName = "id")
    private FileEntity logotipoId;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "video_id", referencedColumnName = "id")
    private FileEntity videoId;

    @Column(name = "estatus", nullable = false, columnDefinition = "boolean default true")
    private Boolean estatus;

    @Column(name = "created_at", nullable = false, columnDefinition = "timestamp default current_timestamp")
    private Date createdAt;

    @Column(name = "updated_at", nullable = true)
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

    public FileEntity getLogotipoId() {
        return logotipoId;
    }

    public void setLogotipoId(FileEntity logotipoId) {
        this.logotipoId = logotipoId;
    }

    public FileEntity getVideoId() {
        return videoId;
    }

    public void setVideoId(FileEntity videoId) {
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
