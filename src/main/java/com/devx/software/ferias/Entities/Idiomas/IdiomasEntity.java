package com.devx.software.ferias.Entities.Idiomas;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "a_idiomas")
public class IdiomasEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String idioma;

    @Column
    private String tag;

    @Column(name="translation_object",nullable = true,columnDefinition = "jsonb")
    private String translationObject;

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

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTranslationObject() {
        return translationObject;
    }

    public void setTranslationObject(String translationObject) {
        this.translationObject = translationObject;
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
