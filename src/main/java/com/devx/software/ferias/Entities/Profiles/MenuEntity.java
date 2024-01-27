package com.devx.software.ferias.Entities.Profiles;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "a_menus")
public class MenuEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = true)
    @JoinColumn(name = "menu_id")
    @JsonBackReference
    private MenuEntity padre;

    @OneToMany(mappedBy = "padre")
    @JsonManagedReference
    private Set<MenuEntity> submenus;

    @Column
    private String nombre;

    @Column
    private String icono;

    @Column
    private String componente;

    @Column
    private String url;

    @Column
    private String etiqueta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MenuEntity getPadre() {
        return padre;
    }

    public void setPadre(MenuEntity padre) {
        this.padre = padre;
    }

    public Set<MenuEntity> getSubmenus() {
        return submenus;
    }

    public void setSubmenus(Set<MenuEntity> submenus) {
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
