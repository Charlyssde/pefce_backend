package com.devx.software.ferias.Services.Minutas;

import com.devx.software.ferias.Entities.Minutas.MinutasEntity;
import com.devx.software.ferias.Repositories.Minutas.MinutasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MinutasService {

    private final MinutasRepository minutasRepository;

    @Autowired
    public MinutasService(MinutasRepository minutasRepository) {
        this.minutasRepository = minutasRepository;
    }

    public MinutasEntity create(MinutasEntity entity) throws Exception {
        try {
            return minutasRepository.save(entity);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<MinutasEntity> page() {
        return minutasRepository.findAllByOrderByIdDesc();
    }

    public MinutasEntity findById(Long id) {
        return minutasRepository.findMinutaById(id);
    }

    public MinutasEntity update(MinutasEntity entity) {
        return minutasRepository.save(entity);
    }

    public void deleteById(Long id) {
        minutasRepository.deleteById(id);
    }

    public Long findLastIdInserted() {
        return minutasRepository.findAllByOrderByIdDesc().get(0).getId();
    }
    
    public List<MinutasEntity> findAllMinutasWhereIn (List<Long> ids){
    return minutasRepository.findAllMinutasWhereIn(ids);
    }
}
