package com.devx.software.ferias.Entities.Enterprises;

import com.devx.software.ferias.Entities.Autodiagnosticos.AutodiagnosticoEntity;
import com.devx.software.ferias.Entities.Shared.DomicilioEntity;
import com.devx.software.ferias.Entities.Users.UserEntity;
import com.devx.software.ferias.Entities.Catalogs.CatalogsEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "a_empresas")
public class EnterpriseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "empresa", nullable = false, columnDefinition = "varchar(250) unique")
    private String empresa;

    @Column(name = "rfc", nullable = false, columnDefinition = "varchar(14) unique")
    private String rfc;

    @OneToOne
    @JoinColumn(name = "regimen_fiscal_id", nullable = false)
    private CatalogsEntity regimenFiscal;

    @OneToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private CatalogsEntity categoria;

    @OneToOne
    @JoinColumn(name = "sector_id", nullable = false)
    private CatalogsEntity sector;

    @OneToOne
    @JoinColumn(name = "subsector_id", nullable = true)
    private CatalogsEntity subsector;

    @Column(name = "telefono", nullable = true, columnDefinition = "varchar(15)")
    private String telefono;

    @Column(name = "email", nullable = true, columnDefinition = "varchar(100)")
    private String email;

    @Column(name = "website", nullable = true, columnDefinition = "varchar(100)")
    private String website;

    @Column(name = "descripcion", nullable = true, columnDefinition = "text")
    private String descripcion;

    @Column(name = "representante_legal", nullable = true, columnDefinition = "varchar(250)")
    private String representanteLegal;

    @Column(name = "numero_colaboradores_masculinos", nullable = true)
    private Long numeroColaboradoresMasculinos;

    @Column(name = "numero_colaboradores_femeninos", nullable = true)
    private Long numeroColaboradoresFemeninos;

    @Column(name = "numero_colaboradores_capacidades_diferentes", nullable = true)
    private Long numeroColaboradoresCapacidadesDiferentes;

    @Column(name = "total_colaboradores", nullable = true)
    private Long totalColaboradores;

    @Column(name = "pabellon_aprobado", nullable = true, columnDefinition = "default true")
    private Boolean pabellonAprobado;

    @Column(name = "estatus", nullable = true, columnDefinition = "default true")
    private Boolean estatus;

    @Column(name = "created_at", nullable = false, columnDefinition = "default CURRENT_TIMESTAMP")
    private Date createdAt;

    @Column(name = "updated_at", nullable = true)
    private Date updatedAt;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.MERGE})
    @JoinTable(
            name = "a_empresa__a_domicilio",
            joinColumns = @JoinColumn(name = "empresa_id"),
            inverseJoinColumns = @JoinColumn(name = "domicilio_id")
    )
    private List<DomicilioEntity> domicilios = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JoinTable(
            name = "a_empresa__a_contacto",
            joinColumns = @JoinColumn(name = "empresa_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private List<UserEntity> contactos = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JoinTable(
            name = "a_empresa__a_empresa_solicitud_acceso",
            joinColumns = @JoinColumn(name = "empresa_id"),
            inverseJoinColumns = @JoinColumn(name = "empresa_solicitud_acceso_id")
    )
    private List<EnterpriseAccessRequestEntity> solicitudesAcceso = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JoinTable(
            name = "a_empresa__a_empresa_imagen_comercial",
            joinColumns = @JoinColumn(name = "empresa_id"),
            inverseJoinColumns = @JoinColumn(name = "empresa_imagen_comercial_id")
    )
    private List<EnterpriseTradeImageEntity> imagenEmpresarial = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "a_empresa__a_producto",
            joinColumns = @JoinColumn(name = "empresa_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private List<ProductEntity> productos = new ArrayList<>();

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "autodiagnostico", nullable = true)
    private AutodiagnosticoEntity autodiagnostico;
    
   @Column(name = "autorizado", nullable = true)
    private Boolean autorizado;

    public AutodiagnosticoEntity getAutodiagnostico() {
        return autodiagnostico;
    }

    public void setAutodiagnostico(AutodiagnosticoEntity autodiagnostico) {
        this.autodiagnostico = autodiagnostico;
    }

    public EnterpriseEntity() {
    }

    public void addDomicilio(DomicilioEntity domicilioEntity) {
        this.domicilios.add(domicilioEntity);
    }

    public void addContact(UserEntity userEntity) {
        this.contactos.add(userEntity);
    }

    public void addAccessRequest(EnterpriseAccessRequestEntity enterpriseAccessRequestEntity) {
        this.solicitudesAcceso.add(enterpriseAccessRequestEntity);
    }

    public void addImagenEmpresarial(EnterpriseTradeImageEntity enterpriseTradeImageEntity) {
        this.imagenEmpresarial.add(enterpriseTradeImageEntity);
    }

    public void addProducto(ProductEntity product) {
        this.productos.add(product);
    }


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

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public CatalogsEntity getRegimenFiscal() {
        return regimenFiscal;
    }

    public void setRegimenFiscal(CatalogsEntity regimenFiscal) {
        this.regimenFiscal = regimenFiscal;
    }

    public CatalogsEntity getCategoria() {
        return categoria;
    }

    public void setCategoria(CatalogsEntity categoria) {
        this.categoria = categoria;
    }

    public CatalogsEntity getSector() {
        return sector;
    }

    public void setSector(CatalogsEntity sector) {
        this.sector = sector;
    }

    public CatalogsEntity getSubsector() {
        return subsector;
    }

    public void setSubsector(CatalogsEntity subsector) {
        this.subsector = subsector;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRepresentanteLegal() {
        return representanteLegal;
    }

    public void setRepresentanteLegal(String representanteLegal) {
        this.representanteLegal = representanteLegal;
    }

    public Long getNumeroColaboradoresMasculinos() {
        return numeroColaboradoresMasculinos;
    }

    public void setNumeroColaboradoresMasculinos(Long numeroColaboradoresMasculinos) {
        this.numeroColaboradoresMasculinos = numeroColaboradoresMasculinos;
    }

    public Long getNumeroColaboradoresFemeninos() {
        return numeroColaboradoresFemeninos;
    }

    public void setNumeroColaboradoresFemeninos(Long numeroColaboradoresFemeninos) {
        this.numeroColaboradoresFemeninos = numeroColaboradoresFemeninos;
    }

    public Long getNumeroColaboradoresCapacidadesDiferentes() {
        return numeroColaboradoresCapacidadesDiferentes;
    }

    public void setNumeroColaboradoresCapacidadesDiferentes(Long numeroColaboradoresCapacidadesDiferentes) {
        this.numeroColaboradoresCapacidadesDiferentes = numeroColaboradoresCapacidadesDiferentes;
    }

    public Long getTotalColaboradores() {
        return totalColaboradores;
    }

    public void setTotalColaboradores(Long totalColaboradores) {
        this.totalColaboradores = totalColaboradores;
    }

    public Boolean getPabellonAprobado() {
        return pabellonAprobado;
    }

    public void setPabellonAprobado(Boolean pabellonAprobado) {
        this.pabellonAprobado = pabellonAprobado;
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

    public List<DomicilioEntity> getDomicilios() {
        return domicilios;
    }

    public void setDomicilios(List<DomicilioEntity> domicilios) {
        this.domicilios = domicilios;
    }

    public List<UserEntity> getContactos() {
        return contactos;
    }

    public void setContactos(List<UserEntity> contactos) {
        this.contactos = contactos;
    }

    public List<EnterpriseAccessRequestEntity> getSolicitudesAcceso() {
        return solicitudesAcceso;
    }

    public void setSolicitudesAcceso(List<EnterpriseAccessRequestEntity> solicitudesAcceso) {
        this.solicitudesAcceso = solicitudesAcceso;
    }

    public List<EnterpriseTradeImageEntity> getImagenEmpresarial() {
        return imagenEmpresarial;
    }

    public void setImagenEmpresarial(List<EnterpriseTradeImageEntity> imagenEmpresarial) {
        this.imagenEmpresarial = imagenEmpresarial;
    }

    public List<ProductEntity> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductEntity> productos) {
        this.productos = productos;
    }

    public Boolean getAutorizado() {
        return autorizado;
    }

    public void setAutorizado(Boolean autorizado) {
        this.autorizado = autorizado;
    }
    
    
}
