package com.devx.software.ferias.Services.Capacitacion;

//import com.devx.software.ferias.zx_entities.ContactoUsuarioEntity;
//import com.devx.software.ferias.zx_repositories.ContactoUsuarioRepository;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Date;

@Service
public class ContactoUsuarioService {

    //private final ContactoUsuarioRepository contactoUsuarioRepository;
    @PersistenceContext
    private EntityManager entityManager;
//
//    public ContactoUsuarioService(ContactoUsuarioRepository contactoUsuarioRepository) {
//        this.contactoUsuarioRepository = contactoUsuarioRepository;
//    }
//
//    @Transactional
//    public ContactoUsuarioEntity create(ContactoUsuarioEntity entity) throws Exception {
//        entity.setActivo(true);
//        entity.setCreatedAt(new Date());
//        try {
//            ContactoUsuarioEntity entitySave = contactoUsuarioRepository.save(entity);
//            entityManager.persist(entitySave);
//            return entitySave;
//        } catch (Exception e) {
//            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//            throw new Exception(e.getMessage());
//        }
//    }
//
//    public ContactoUsuarioEntity findByIdUsuario(Long id) {
//        return contactoUsuarioRepository.findByUsuario_Id(id);
//    }
//
//    public ContactoUsuarioEntity findByIdContacto(Long id) {
//        return contactoUsuarioRepository.findByContacto_Id(id);
//    }
}

