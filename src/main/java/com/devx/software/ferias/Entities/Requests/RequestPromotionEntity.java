package com.devx.software.ferias.Entities.Requests;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "a_solicitudes_promocion")
public class RequestPromotionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ubicacion")
    private String ubicacion;

    @Column(name = "coordenadas")
    private String coordenadas = "{\"lat\":0,\"lng\":0}";

    @Column(name = "superficie")
    private Float superficie;

    @Column(name = "unidad_superficie")
    private String unidadSuperficie;

    @Column(name = "tipologia")
    private String tipologia;

    @Column(name = "valorM2")
    private Float valorM2;

    @Column(name = "vocacion", nullable = true)
    private String vocacion;

    @Column(name = "permisos")
    private String permisos;

    @Column(name = "tipo_propiedad")
    private String tipoPropiedad = "{\"escritura publica\":false,\"titulo de propiedad\":false,\"vocacion\":false,\"Cesion de derechos\":false,\"certificado parcelario\":false,\"Puerto\":false,\"Otro\":false}";

    @Column(name = "datos_documento")
    private String datosDocumento;

    @Column(name = "tiene_gravamen")
    private Boolean tieneGravamen;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at", nullable = true)
    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    public Float getSuperficie() {
        return superficie;
    }

    public void setSuperficie(Float superficie) {
        this.superficie = superficie;
    }

    public String getUnidadSuperficie() {
        return unidadSuperficie;
    }

    public void setUnidadSuperficie(String unidadSuperficie) {
        this.unidadSuperficie = unidadSuperficie;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public Float getValorM2() {
        return valorM2;
    }

    public void setValorM2(Float valorM2) {
        this.valorM2 = valorM2;
    }

    public String getVocacion() {
        return vocacion;
    }

    public void setVocacion(String vocacion) {
        this.vocacion = vocacion;
    }

    public String getPermisos() {
        return permisos;
    }

    public void setPermisos(String permisos) {
        this.permisos = permisos;
    }

    public String getTipoPropiedad() {
        return tipoPropiedad;
    }

    public void setTipoPropiedad(String tipoPropiedad) {
        this.tipoPropiedad = tipoPropiedad;
    }

    public String getDatosDocumento() {
        return datosDocumento;
    }

    public void setDatosDocumento(String datosDocumento) {
        this.datosDocumento = datosDocumento;
    }

    public Boolean getTieneGravamen() {
        return tieneGravamen;
    }

    public void setTieneGravamen(Boolean tieneGravamen) {
        this.tieneGravamen = tieneGravamen;
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
