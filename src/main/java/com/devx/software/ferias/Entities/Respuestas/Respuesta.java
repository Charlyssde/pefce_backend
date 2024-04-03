package com.devx.software.ferias.Entities.Respuestas;

import com.devx.software.ferias.Entities.Encuestas.Encuestas;
import com.devx.software.ferias.Entities.Enterprises.EnterpriseEntity;
import com.devx.software.ferias.Entities.Preguntas.Preguntas;
import com.devx.software.ferias.Entities.Users.UserEntity;
import com.devx.software.ferias.Enums.TipoRespuesta;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "respuestas")
public class Respuesta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "respuesta")
    @Enumerated(value = EnumType.ORDINAL)
    private TipoRespuesta respuesta;

    @ManyToOne
    @JoinColumn(name = "id_pregunta")
    private Preguntas pregunta;

    @ManyToOne
    @JoinColumn(name = "id_empresa")
    private EnterpriseEntity empresa;

    @ManyToOne
    @JoinColumn(name = "id_encuesta")
    private Encuestas encuesta;

    @ManyToOne
    @JoinColumn(name = "creado_por")
    private UserEntity creadoPor;

    public Respuesta() {
    }

    public Respuesta(Long id, TipoRespuesta respuesta, Preguntas pregunta, EnterpriseEntity empresa, Encuestas encuesta, UserEntity creadoPor) {
        this.id = id;
        this.respuesta = respuesta;
        this.pregunta = pregunta;
        this.empresa = empresa;
        this.encuesta = encuesta;
        this.creadoPor = creadoPor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoRespuesta getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(TipoRespuesta respuesta) {
        this.respuesta = respuesta;
    }

    public Preguntas getPregunta() {
        return pregunta;
    }

    public void setPregunta(Preguntas pregunta) {
        this.pregunta = pregunta;
    }

    public EnterpriseEntity getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EnterpriseEntity empresa) {
        this.empresa = empresa;
    }

    public Encuestas getEncuesta() {
        return encuesta;
    }

    public void setEncuesta(Encuestas encuesta) {
        this.encuesta = encuesta;
    }

    public UserEntity getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(UserEntity creadoPor) {
        this.creadoPor = creadoPor;
    }

    @Override
    public String toString() {
        return "Respuesta{" +
                "id=" + id +
                ", respuesta=" + respuesta +
                ", pregunta=" + pregunta +
                ", empresa=" + empresa +
                ", encuesta=" + encuesta +
                ", creadoPor=" + creadoPor +
                '}';
    }
}
