package com.devx.software.ferias.Entities.Sistema;

import com.devx.software.ferias.Entities.Encuestas.EncuestaEntity;
import com.devx.software.ferias.Entities.Users.UserEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "a_sistema")
@IdClass(SistemaEntity.class)
public class SistemaEntity implements Serializable {

    @OneToOne
    @Id
    @JoinColumn(name = "subinresponsable", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private UserEntity responableSubin;

    @OneToOne
    @Id
    @JoinColumn(name = "dceresponsable", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private UserEntity responableDce;

    @OneToOne
    @Id
    @JoinColumn(name = "dainresponsable", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private UserEntity responableDain;

    @OneToOne
    @Id
    @JoinColumn(name = "cpditresponsable", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private UserEntity responableCpdit;

    public UserEntity getResponableSubin() {
        return responableSubin;
    }

    public void setResponableSubin(UserEntity responableSubin) {
        this.responableSubin = responableSubin;
    }

    public UserEntity getResponableDce() {
        return responableDce;
    }

    public void setResponableDce(UserEntity responableDce) {
        this.responableDce = responableDce;
    }

    public UserEntity getResponableDain() {
        return responableDain;
    }

    public void setResponableDain(UserEntity responableDain) {
        this.responableDain = responableDain;
    }

    public UserEntity getResponableCpdit() {
        return responableCpdit;
    }

    public void setResponableCpdit(UserEntity responableCpdit) {
        this.responableCpdit = responableCpdit;
    }
}
