package com.devx.software.ferias.Entities.Capacitacion;

import com.devx.software.ferias.Entities.Capacitacion.TemasEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "a_capacitacion__a_preguntas")
public class PreguntasEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_tema")
    @JsonBackReference
    private TemasEntity tema;

    @Column
    private String pregunta;

    @Column
    private String detalle;

    @Column
    private String recurso;

    @Column(name = "respuesta_a")
    private String respuestaA;

    @Column(name = "respuesta_b")
    private String respuestaB;

    @Column(name = "respuesta_c")
    private String respuestaC;

    @Column(name = "respuesta_d")
    private String respuestaD;

    @Column
    private String respuestaCorrecta;

    @Column
    private Date createdAt;

    @Column
    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TemasEntity getTema() {
        return tema;
    }

    public void setTema(TemasEntity tema) {
        this.tema = tema;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getRecurso() {
        return recurso;
    }

    public void setRecurso(String recurso) {
        this.recurso = recurso;
    }

    public String getRespuestaA() {
        return respuestaA;
    }

    public void setRespuestaA(String respuestaA) {
        this.respuestaA = respuestaA;
    }

    public String getRespuestaB() {
        return respuestaB;
    }

    public void setRespuestaB(String respuestaB) {
        this.respuestaB = respuestaB;
    }

    public String getRespuestaC() {
        return respuestaC;
    }

    public void setRespuestaC(String respuestaC) {
        this.respuestaC = respuestaC;
    }

    public String getRespuestaD() {
        return respuestaD;
    }

    public void setRespuestaD(String respuestaD) {
        this.respuestaD = respuestaD;
    }

    public String getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public void setRespuestaCorrecta(String respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
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
