package com.devx.software.ferias.Services.Pabellones;

import com.devx.software.ferias.Entities.Pabellones.PabellonesVideosEntity;
import com.devx.software.ferias.Repositories.Pabellones.PabellonesVideosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Date;

@Service
public class PabellonesVideosService {
    private final PabellonesVideosRepository pabellonesVideosRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public PabellonesVideosService(
            PabellonesVideosRepository pabellonesVideosRepository
    ) {
        this.pabellonesVideosRepository = pabellonesVideosRepository;
    }

    //    Crear pabellón
    @Transactional
    public PabellonesVideosEntity create(PabellonesVideosEntity entity) throws Exception {
        entity.setCreatedAt(new Date());
        try {
            PabellonesVideosEntity entitySave = pabellonesVideosRepository.save(entity);
            entityManager.persist(entitySave);
            return entitySave;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new Exception(e.getMessage());
        }
    }

    public PabellonesVideosEntity findById(Long idProducto) {
        return pabellonesVideosRepository.findById(idProducto).get();
    }

    public PabellonesVideosEntity update(PabellonesVideosEntity entity) {
        return pabellonesVideosRepository.save(entity);
    }

    public void delete(PabellonesVideosEntity entity) {
        pabellonesVideosRepository.delete(entity);
    }
}
