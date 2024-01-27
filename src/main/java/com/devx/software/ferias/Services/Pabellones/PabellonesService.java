package com.devx.software.ferias.Services.Pabellones;

import com.devx.software.ferias.Entities.Pabellones.PabellonesEntity;
import com.devx.software.ferias.Mappers.Pabellones.PabellonesMapper;
import com.devx.software.ferias.Mappers.Pabellones.PabellonesProductosMapper;
import com.devx.software.ferias.Mappers.Pabellones.PabellonesVideosMapper;
import com.devx.software.ferias.Repositories.Pabellones.PabellonesProductosRepository;
import com.devx.software.ferias.Repositories.Pabellones.PabellonesRepository;
import com.devx.software.ferias.Repositories.Pabellones.PabellonesVideosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class PabellonesService {
    private final PabellonesRepository pabellonesRepository;
//    private final DirectorioEmpresarialRepository directorioEmpresasRepository;
    private final PabellonesProductosRepository pabellonesP;
    private final PabellonesProductosMapper pabellonesMP;
    private final PabellonesVideosRepository pabellonesV;
    private final PabellonesVideosMapper pabellonesMV;
    private final PabellonesMapper pabellonesM;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public PabellonesService(
            PabellonesRepository pabellonesRepository,
            PabellonesProductosRepository pabellonesP,
            PabellonesProductosMapper pabellonesMP,
            PabellonesVideosRepository pabellonesV,
            PabellonesVideosMapper pabellonesMV,
//            DirectorioUsuarioRepository directorioUsuariosRepository,
//            DirectorioEmpresarialRepository directorioEmpresasRepository,
            PabellonesMapper pabellonesM) {
        this.pabellonesRepository = pabellonesRepository;
        this.pabellonesP = pabellonesP;
        this.pabellonesMP = pabellonesMP;
        this.pabellonesV = pabellonesV;
        this.pabellonesMV = pabellonesMV;
        this.pabellonesM = pabellonesM;
//        this.directorioEmpresasRepository = directorioEmpresasRepository;
    }

    // consultar todos los pabellones
    public List<PabellonesEntity> page() {
        List<PabellonesEntity> pabellones = pabellonesRepository.findAll();
        return pabellones;
    }

    public List<PabellonesEntity> pageActive() {
        List<PabellonesEntity> pabellones = pabellonesRepository.getPabellonesActive();
        return pabellones;
    }

    public List<PabellonesEntity> getPabellonesByIdEvento(Long idEvento) {
        List<PabellonesEntity> pabellones = pabellonesRepository.getPabellonesByIdEvento(idEvento);
        return pabellones;
    }

    // Leer pabellón
    public PabellonesEntity findPabellonByIdUsuario(Long idUsuario) {
        return pabellonesRepository.findFirstPabellonByIdUsuario(idUsuario);
    }

    // Pabellones individales
    public PabellonesEntity findPabellonById(Long id) {
        return pabellonesRepository.findPabellonById(id);
    }

    // Crear pabellón
    @Transactional
    public PabellonesEntity create(PabellonesEntity entity) throws Exception {
        entity.setCreatedAt(new Date());
        try {
            PabellonesEntity entitySave = pabellonesRepository.save(entity);
            entityManager.persist(entitySave);
            return entitySave;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new Exception(e.getMessage());
        }
    }


    // Update pabellon
    public PabellonesEntity update(PabellonesEntity entity) {
        return pabellonesRepository.save(entity);
    }

    public PabellonesEntity findByIdDirectorio(Long id) {
        return pabellonesRepository.findByIdDirectorioEmpresarial(id);
    }
}
