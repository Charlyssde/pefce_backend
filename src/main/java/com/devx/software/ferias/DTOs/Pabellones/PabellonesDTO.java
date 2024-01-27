package com.devx.software.ferias.DTOs.Pabellones;
import java.util.Date;
import java.util.List;

public class PabellonesDTO {
    private Long id;

    private String empresa;

    private String eslogan;

    private String banner1;

    private String banner2;

    private String logotipo;

    private Boolean estatus;

    private List<PabellonesProductosDTO> pabellonesProductos;

    private List<PabellonesVideosDTO> pabellonesVideos;

    private Date createdAt;

    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
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

    public List<PabellonesProductosDTO> getPabellonesProductos() {
        return pabellonesProductos;
    }

    public void setPabellonesProductos(List<PabellonesProductosDTO> pabellonesProductos) {
        this.pabellonesProductos = pabellonesProductos;
    }

    public List<PabellonesVideosDTO> getPabellonesVideos() {
        return pabellonesVideos;
    }

    public void setPabellonesVideos(List<PabellonesVideosDTO> pabellonesVideos) {
        this.pabellonesVideos = pabellonesVideos;
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
