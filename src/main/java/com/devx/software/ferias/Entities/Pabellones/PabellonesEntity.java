package com.devx.software.ferias.Entities.Pabellones;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "a_pabellones")
public class PabellonesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "slogan")
    private String slogan;

    @Column
    private String banner1;

    @Column
    private String banner2;

    @Column
    private String logotipo;

    @Column
    private Boolean estatus;

    @Column
    private Date createdAt;

    @Column
    private Date updatedAt;

    @OneToMany(
            mappedBy = "pabellones",
            cascade = CascadeType.PERSIST
    )
    private List<PabellonesVideosEntity> videos;

    @OneToMany(
            mappedBy = "pabellones",
            cascade = CascadeType.PERSIST
    )
    private List<PabellonesProductosEntity> productos;

    public void agregarProducto(PabellonesProductosEntity producto) {
        producto.setPabellones(this);
        this.productos.add(producto);
    }

    public void agregarVideo(PabellonesVideosEntity video) {
        this.videos.add(video);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
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

    public String getLogotipo() {
        return logotipo;
    }

    public void setLogotipo(String logotipo) {
        this.logotipo = logotipo;
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

    public List<PabellonesVideosEntity> getVideos() {
        return videos;
    }

    public void setVideos(List<PabellonesVideosEntity> videos) {
        this.videos = videos;
    }

    public List<PabellonesProductosEntity> getProductos() {
        return productos;
    }

    public void setProductos(List<PabellonesProductosEntity> productos) {
        this.productos = productos;
    }
}
