package com.devx.software.ferias.Entities.Enterprises;

import com.devx.software.ferias.Entities.Files.FileEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "a_productos")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "descripcion", nullable = true, columnDefinition = "text")
    private String descripcion;

    @Column(name = "empaque_envasado", nullable = true, columnDefinition = "text")
    private String empaqueEnvasado;

    @Column(name = "embalaje", nullable = true, columnDefinition = "text")
    private String embalaje;

    @Column(name = "estibado", nullable = true)
    private String estibado;

    @Column(name = "condiciones_almacenamiento", nullable = true, columnDefinition = "text")
    private String condicionesAlmacenamiento;

    @Column(name = "transporte", nullable = true, columnDefinition = "text")
    private String transporte;

    @Column(name = "vida_anaquel", nullable = true, columnDefinition = "text")
    private String vidaAnaquel;

    @Column(name = "lugarOrigen", nullable = true, columnDefinition = "text")
    private String lugarOrigen;

    @Column(name = "estatus", nullable = false, columnDefinition = "boolean default true")
    private Boolean estatus;

    @Column(name = "created_at", nullable = false, columnDefinition = "timestamp default current_timestamp")
    private Date createdAt;

    @Column(name = "updated_at", nullable = true, columnDefinition = "timestamp")
    private Date updatedAt;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "a_producto__a_ficha_tecnica",
            joinColumns = @JoinColumn(name = "producto_id"),
            inverseJoinColumns = @JoinColumn(name = "ficha_tecnica_id")
    )
    private List<FileEntity> fichaTecnica = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "a_producto__a_imagen",
            joinColumns = @JoinColumn(name = "producto_id"),
            inverseJoinColumns = @JoinColumn(name = "imagen_id")
    )
    private List<FileEntity> imagenes = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "a_producto__a_video",
            joinColumns = @JoinColumn(name = "producto_id"),
            inverseJoinColumns = @JoinColumn(name = "video_id")
    )
    private List<FileEntity> videos = new ArrayList<>();

    public void addFichaTecnica(FileEntity fileEntity) {
        this.fichaTecnica.add(fileEntity);
    }

    public void addImagen(FileEntity fileEntity) {
        this.imagenes.add(fileEntity);
    }

    public void addVideo(FileEntity fileEntity) {
        this.videos.add(fileEntity);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEmpaqueEnvasado() {
        return empaqueEnvasado;
    }

    public void setEmpaqueEnvasado(String empaqueEnvasado) {
        this.empaqueEnvasado = empaqueEnvasado;
    }

    public String getEmbalaje() {
        return embalaje;
    }

    public void setEmbalaje(String embalaje) {
        this.embalaje = embalaje;
    }

    public String getEstibado() {
        return estibado;
    }

    public void setEstibado(String estibado) {
        this.estibado = estibado;
    }

    public String getCondicionesAlmacenamiento() {
        return condicionesAlmacenamiento;
    }

    public void setCondicionesAlmacenamiento(String condicionesAlmacenamiento) {
        this.condicionesAlmacenamiento = condicionesAlmacenamiento;
    }

    public String getTransporte() {
        return transporte;
    }

    public void setTransporte(String transporte) {
        this.transporte = transporte;
    }

    public String getVidaAnaquel() {
        return vidaAnaquel;
    }

    public void setVidaAnaquel(String vidaAnaquel) {
        this.vidaAnaquel = vidaAnaquel;
    }

    public String getLugarOrigen() {
        return lugarOrigen;
    }

    public void setLugarOrigen(String lugarOrigen) {
        this.lugarOrigen = lugarOrigen;
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

    public List<FileEntity> getFichaTecnica() {
        return fichaTecnica;
    }

    public void setFichaTecnica(List<FileEntity> fichaTecnica) {
        this.fichaTecnica = fichaTecnica;
    }

    public List<FileEntity> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<FileEntity> imagenes) {
        this.imagenes = imagenes;
    }

    public List<FileEntity> getVideos() {
        return videos;
    }

    public void setVideos(List<FileEntity> videos) {
        this.videos = videos;
    }
}
