package com.devx.software.ferias.DTOs.Profiles;

import java.util.Set;

public class MenuDTO {
    private Long id;

    private MenuDTO padre;

    private Set<MenuDTO> submenus;

    private String nombre;

    private String icono;

    private String componente;

    private String url;

    private String etiqueta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MenuDTO getPadre() {
        return padre;
    }

    public void setPadre(MenuDTO padre) {
        this.padre = padre;
    }

    public Set<MenuDTO> getSubmenus() {
        return submenus;
    }

    public void setSubmenus(Set<MenuDTO> submenus) {
        this.submenus = submenus;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public String getComponente() {
        return componente;
    }

    public void setComponente(String componente) {
        this.componente = componente;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }
}
