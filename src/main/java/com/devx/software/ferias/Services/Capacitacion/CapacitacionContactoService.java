package com.devx.software.ferias.Services.Capacitacion;

import com.devx.software.ferias.Entities.Capacitacion.CapacitacionContactoEntity;
import com.devx.software.ferias.Repositories.Capacitacion.CapacitacionContactoRepository;
import com.devx.software.ferias.Services.Capacitacion.ContactosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class CapacitacionContactoService {

    private final CapacitacionContactoRepository capacitacionContactoRepository;
    private final CapacitacionesService capacitacionesService;
    private final ContactosService contactosService;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public CapacitacionContactoService(CapacitacionContactoRepository capacitacionContactoRepository, CapacitacionesService capacitacionesService, ContactosService contactosService) {
        this.capacitacionContactoRepository = capacitacionContactoRepository;
        this.capacitacionesService = capacitacionesService;
        this.contactosService = contactosService;
    }

    @Transactional
    public CapacitacionContactoEntity create(CapacitacionContactoEntity entity) throws Exception {
        entity.setActivo(true);
        entity.setCreatedAt(new Date());
        entity.setContacto(entity.getContacto());
        entity.setCapacitacion(capacitacionesService.findById(entity.getCapacitacion().getId()));
        try {
            CapacitacionContactoEntity entitySave = capacitacionContactoRepository.save(entity);
            entityManager.persist(entitySave);
            return entitySave;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new Exception(e.getMessage());
        }
    }

    public List<CapacitacionContactoEntity> findContactosByIdCapacitacion(Long idCapacitacion) {
        return capacitacionContactoRepository.findAllByCapacitacion_Id(idCapacitacion);
    }
}
