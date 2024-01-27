package com.devx.software.ferias.DTOs.Profiles;

import java.util.List;

public class PermissionDTO {
    private Long id;

    private String nombreEtiqueta;

    private String nombreRol;

    private String icon;

    private List<ChildPermissionDTO> subModulos;

    private String urlModulo;

    private Boolean canCreate;

    private Boolean canDelete;

    private Boolean canReport;

    private Boolean canShow;

    private Boolean canUpdate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreEtiqueta() {
        return nombreEtiqueta;
    }

    public void setNombreEtiqueta(String nombreEtiqueta) {
        this.nombreEtiqueta = nombreEtiqueta;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<ChildPermissionDTO> getSubModulos() {
        return subModulos;
    }

    public void setSubModulos(List<ChildPermissionDTO> subModulos) {
        this.subModulos = subModulos;
    }

    public String getUrlModulo() {
        return urlModulo;
    }

    public void setUrlModulo(String urlModulo) {
        this.urlModulo = urlModulo;
    }

    public Boolean getCanCreate() {
        return canCreate;
    }

    public void setCanCreate(Boolean canCreate) {
        this.canCreate = canCreate;
    }

    public Boolean getCanDelete() {
        return canDelete;
    }

    public void setCanDelete(Boolean canDelete) {
        this.canDelete = canDelete;
    }

    public Boolean getCanReport() {
        return canReport;
    }

    public void setCanReport(Boolean canReport) {
        this.canReport = canReport;
    }

    public Boolean getCanShow() {
        return canShow;
    }

    public void setCanShow(Boolean canShow) {
        this.canShow = canShow;
    }

    public Boolean getCanUpdate() {
        return canUpdate;
    }

    public void setCanUpdate(Boolean canUpdate) {
        this.canUpdate = canUpdate;
    }
}
