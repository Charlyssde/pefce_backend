package com.devx.software.ferias.Entities.Events;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.devx.software.ferias.Entities.Catalogs.MunicipiosEntity;
import com.devx.software.ferias.Entities.Enterprises.EnterpriseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "a_eventos_a_enterprises")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventEnterpriseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean activo;

    private Date createdAt;

    private Date updatedAt;

    private int males;

    private int females;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_municipio")
    private MunicipiosEntity municipio;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_evento")
    private EventEntity evento;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_enterprise")
    private EnterpriseEntity enterprise;

}
