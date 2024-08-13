package com.devx.software.ferias.Entities.DSP;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.devx.software.ferias.Entities.Files.FileEntity;

@Entity
@Table(name = "a_dsp")
public class DSP {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date solicitudSefiplan;

    @Column(nullable = false, name = "numero_dsp")
    private String numeroDSP;

    @Column(nullable = false)
    private Date autorizacion;

    @Column(nullable = false)
    private Date recepcion;

    @Column(nullable = false)
    private float importe;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private String concepto;

    @Column(nullable = false, name = "codigo_presupuestal")
    private String codigoPresupuestal;

    @Column(nullable = false)
    private Date vigencia;

    @Column(nullable = false)
    private Date solicitud;

    @Column(nullable = false, name = "solicitud_prorroga")
    private Date solicitudProrroga;

    @Column(nullable = false, name = "oficio_prorroga")
    private String oficioProrroga;

    @Column(nullable = false, name = "autorizacion_prorroga")
    private Date autorizacionProrroga;

    @Column(nullable = false, name = "recepcion_prorroga")
    private Date recepcionProrroga;

    @OneToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinColumn(name = "file")
    private FileEntity file;

    public DSP(Long id, Date solicitudSefiplan, String numeroDSP, Date autorizacion, Date recepcion, float importe,
            String descripcion, String concepto, String codigoPresupuestal, Date vigencia, Date solicitud,
            Date solicitudProrroga, String oficioProrroga, Date autorizacionProrroga, Date recepcionProrroga,
            FileEntity file) {
        this.id = id;
        this.solicitudSefiplan = solicitudSefiplan;
        this.numeroDSP = numeroDSP;
        this.autorizacion = autorizacion;
        this.recepcion = recepcion;
        this.importe = importe;
        this.descripcion = descripcion;
        this.concepto = concepto;
        this.codigoPresupuestal = codigoPresupuestal;
        this.vigencia = vigencia;
        this.solicitud = solicitud;
        this.solicitudProrroga = solicitudProrroga;
        this.oficioProrroga = oficioProrroga;
        this.autorizacionProrroga = autorizacionProrroga;
        this.recepcionProrroga = recepcionProrroga;
        this.file = file;
    }

    public DSP() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getSolicitudSefiplan() {
        return solicitudSefiplan;
    }

    public void setSolicitudSefiplan(Date solicitudSefiplan) {
        this.solicitudSefiplan = solicitudSefiplan;
    }

    public String getNumeroDSP() {
        return numeroDSP;
    }

    public void setNumeroDSP(String numeroDSP) {
        this.numeroDSP = numeroDSP;
    }

    public Date getAutorizacion() {
        return autorizacion;
    }

    public void setAutorizacion(Date autorizacion) {
        this.autorizacion = autorizacion;
    }

    public Date getRecepcion() {
        return recepcion;
    }

    public void setRecepcion(Date recepcion) {
        this.recepcion = recepcion;
    }

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getCodigoPresupuestal() {
        return codigoPresupuestal;
    }

    public void setCodigoPresupuestal(String codigoPresupuestal) {
        this.codigoPresupuestal = codigoPresupuestal;
    }

    public Date getVigencia() {
        return vigencia;
    }

    public void setVigencia(Date vigencia) {
        this.vigencia = vigencia;
    }

    public Date getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Date solicitud) {
        this.solicitud = solicitud;
    }

    public Date getSolicitudProrroga() {
        return solicitudProrroga;
    }

    public void setSolicitudProrroga(Date solicitudProrroga) {
        this.solicitudProrroga = solicitudProrroga;
    }

    public String getOficioProrroga() {
        return oficioProrroga;
    }

    public void setOficioProrroga(String oficioProrroga) {
        this.oficioProrroga = oficioProrroga;
    }

    public Date getAutorizacionProrroga() {
        return autorizacionProrroga;
    }

    public void setAutorizacionProrroga(Date autorizacionProrroga) {
        this.autorizacionProrroga = autorizacionProrroga;
    }

    public Date getRecepcionProrroga() {
        return recepcionProrroga;
    }

    public void setRecepcionProrroga(Date recepcionProrroga) {
        this.recepcionProrroga = recepcionProrroga;
    }

    public FileEntity getFile() {
        return file;
    }

    public void setFile(FileEntity file) {
        this.file = file;
    }



}