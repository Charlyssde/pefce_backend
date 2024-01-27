package com.devx.software.ferias.Services.Pabellones;

import com.devx.software.ferias.Entities.Pabellones.PabellonesProductosEntity;
import com.devx.software.ferias.Repositories.Pabellones.PabellonesProductosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Date;

@Service
public class PabellonesProductosService {
    private final PabellonesProductosRepository pabellonesProductosRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public PabellonesProductosService(
            PabellonesProductosRepository pabellonesProductosRepository
    ) {
        this.pabellonesProductosRepository = pabellonesProductosRepository;
    }

    //    Crear pabellón
    @Transactional
    public PabellonesProductosEntity create(PabellonesProductosEntity entity) throws Exception {
        entity.setCreatedAt(new Date());
        try {
            PabellonesProductosEntity entitySave = pabellonesProductosRepository.save(entity);
            entityManager.persist(entitySave);
            return entitySave;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new Exception(e.getMessage());
        }
    }

    public PabellonesProductosEntity findById(Long idProducto) {
        return pabellonesProductosRepository.findById(idProducto).get();
    }

    public PabellonesProductosEntity update(PabellonesProductosEntity entity) {
        return pabellonesProductosRepository.save(entity);
    }

    public void delete(PabellonesProductosEntity entity) {
        pabellonesProductosRepository.delete(entity);
    }
}
