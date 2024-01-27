package com.devx.software.ferias.Services.Minutas;

import com.devx.software.ferias.Entities.Minutas.MinutaTareaEntity;
import com.devx.software.ferias.Repositories.Minutas.MinutaTareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MinutaTareaService {

    private final MinutaTareaRepository minutaTareaRepository;

    @Autowired
    public MinutaTareaService(MinutaTareaRepository minutaTareaRepository) {
        this.minutaTareaRepository = minutaTareaRepository;
    }

    public MinutaTareaEntity create(MinutaTareaEntity entity) throws Exception {
        try {
            return minutaTareaRepository.save(entity);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<MinutaTareaEntity> page() {
        return minutaTareaRepository.findAll();
    }

    public MinutaTareaEntity findById(Long id) {
        return minutaTareaRepository.findMinutaTareaById(id);
    }

    public MinutaTareaEntity update(MinutaTareaEntity entity) {
        return minutaTareaRepository.save(entity);
    }

    public void deleteById(Long id) {
        minutaTareaRepository.deleteById(id);
    }
}
