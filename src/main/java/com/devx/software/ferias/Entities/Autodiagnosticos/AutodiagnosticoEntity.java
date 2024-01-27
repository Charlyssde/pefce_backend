package com.devx.software.ferias.Entities.Autodiagnosticos;

import com.devx.software.ferias.Entities.Enterprises.EnterpriseEntity;
import com.devx.software.ferias.Entities.Files.FileEntity;
import com.devx.software.ferias.Entities.Users.UserEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "a_autodiagnosticos")
public class AutodiagnosticoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombre_comercial;

    @Column
    private String sexo_representante;

    @Column
    private String redes;

    @Column
    private String domicilio_coincide;

    @Column
    private String domicilio_fisico;

    @Column
    private String municipio_fisico;

    @Column
    private String puesto_contesto;

    @Column
    private Long operacion;

    @Column
    private String esquemas;

    @Column
    private Long numero_tiendas;

    @Column
    private String representacion;

    @Column
    private String impi;

    @Column
    private String marca_internacional;

    @Column
    private String certificacion;

    @Column
    private String vucem;

    @Column
    private String volumen;

    @Column
    private String exportacion_previa;

    @Column
    private String actualmente_exporta;

    @Column
    private String pais_exporta;

    @Column
    private String area_ventas;

    @Column
    private String en_linea;

    @Column
    private String programas;

    @Column
    private Boolean estatus;

    @Column
    private Date created_at;

    @Column
    private Date updated_at;
/*
    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JoinTable(
            name = "a_autodiagnosticos__a_empresas",
            joinColumns = @JoinColumn(name = "autodiagnostico_id"),
            inverseJoinColumns = @JoinColumn(name = "empresa_id")
    )
    private EnterpriseEntity empresa;
*/
    /*
    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JoinTable(
            name = "a_autodiagnosticos__a_contesto",
            joinColumns = @JoinColumn(name = "autodiagnostico_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private UserEntity contesto;
    */
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "a_autodiagnosticos__a_cif",
            joinColumns = @JoinColumn(name = "autodiagnostico_id"),
            inverseJoinColumns = @JoinColumn(name = "cif_id")
    )
    private List<FileEntity> cif = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "a_autodiagnosticos__a_opinion",
            joinColumns = @JoinColumn(name = "autodiagnostico_id"),
            inverseJoinColumns = @JoinColumn(name = "opinion_id")
    )
    private List<FileEntity> opinion = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
/*
    public EnterpriseEntity getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EnterpriseEntity empresa) {
        this.empresa = empresa;
    }
*/
    public String getNombre_comercial() {
        return nombre_comercial;
    }

    public void setNombre_comercial(String nombre_comercial) {
        this.nombre_comercial = nombre_comercial;
    }

    public String getSexo_representante() {
        return sexo_representante;
    }

    public void setSexo_representante(String sexo_representante) {
        this.sexo_representante = sexo_representante;
    }

    public String getRedes() {
        return redes;
    }

    public void setRedes(String redes) {
        this.redes = redes;
    }
/*
    public UserEntity getContesto() {
        return contesto;
    }

    public void setContesto(UserEntity contesto) {
        this.contesto = contesto;
    }
*/
    public String getDomicilio_coincide() {
        return domicilio_coincide;
    }

    public void setDomicilio_coincide(String domicilio_coincide) {
        this.domicilio_coincide = domicilio_coincide;
    }

    public String getDomicilio_fisico() {
        return domicilio_fisico;
    }

    public void setDomicilio_fisico(String domicilio_fisico) {
        this.domicilio_fisico = domicilio_fisico;
    }

    public String getMunicipio_fisico() {
        return municipio_fisico;
    }

    public void setMunicipio_fisico(String municipio_fisico) {
        this.municipio_fisico = municipio_fisico;
    }

    public String getPuesto_contesto() {
        return puesto_contesto;
    }

    public void setPuesto_contesto(String puesto_contesto) {
        this.puesto_contesto = puesto_contesto;
    }

    public Long getOperacion() {
        return operacion;
    }

    public void setOperacion(Long operacion) {
        this.operacion = operacion;
    }

    public String getEsquemas() {
        return esquemas;
    }

    public void setEsquemas(String esquemas) {
        this.esquemas = esquemas;
    }

    public Long getNumero_tiendas() {
        return numero_tiendas;
    }

    public void setNumero_tiendas(Long numero_tiendas) {
        this.numero_tiendas = numero_tiendas;
    }

    public String getRepresentacion() {
        return representacion;
    }

    public void setRepresentacion(String representacion) {
        this.representacion = representacion;
    }

    public String getImpi() {
        return impi;
    }

    public void setImpi(String impi) {
        this.impi = impi;
    }

    public String getMarca_internacional() {
        return marca_internacional;
    }

    public void setMarca_internacional(String marca_internacional) {
        this.marca_internacional = marca_internacional;
    }

    public String getCertificacion() {
        return certificacion;
    }

    public void setCertificacion(String certificacion) {
        this.certificacion = certificacion;
    }

    public String getVucem() {
        return vucem;
    }

    public void setVucem(String vucem) {
        this.vucem = vucem;
    }

    public String getVolumen() {
        return volumen;
    }

    public void setVolumen(String volumen) {
        this.volumen = volumen;
    }

    public String getExportacion_previa() {
        return exportacion_previa;
    }

    public void setExportacion_previa(String exportacion_previa) {
        this.exportacion_previa = exportacion_previa;
    }

    public String getActualmente_exporta() {
        return actualmente_exporta;
    }

    public void setActualmente_exporta(String actualmente_exporta) {
        this.actualmente_exporta = actualmente_exporta;
    }

    public String getPais_exporta() {
        return pais_exporta;
    }

    public void setPais_exporta(String pais_exporta) {
        this.pais_exporta = pais_exporta;
    }

    public String getArea_ventas() {
        return area_ventas;
    }

    public void setArea_ventas(String area_ventas) {
        this.area_ventas = area_ventas;
    }

    public String getEn_linea() {
        return en_linea;
    }

    public void setEn_linea(String en_linea) {
        this.en_linea = en_linea;
    }

    public String getProgramas() {
        return programas;
    }

    public void setProgramas(String programas) {
        this.programas = programas;
    }

    public Boolean getEstatus() {
        return estatus;
    }

    public void setEstatus(Boolean estatus) {
        this.estatus = estatus;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public List<FileEntity> getCif() {
        return cif;
    }

    public void setCif(List<FileEntity> cif) {
        this.cif = cif;
    }

    public void addCif(FileEntity fileEntity) {
        this.cif.add(fileEntity);
    }

    public List<FileEntity> getOpinion() {
        return opinion;
    }

    public void setOpinion(List<FileEntity> opinion) {
        this.opinion = opinion;
    }

    public void addOpinion(FileEntity fileEntity) {
        this.opinion.add(fileEntity);
    }

}
