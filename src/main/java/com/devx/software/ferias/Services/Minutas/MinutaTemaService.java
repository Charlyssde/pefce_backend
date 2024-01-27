package com.devx.software.ferias.Services.Minutas;

import com.devx.software.ferias.Entities.Minutas.MinutaTemaEntity;
import com.devx.software.ferias.Repositories.Minutas.MinutaTemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MinutaTemaService {

    private final MinutaTemaRepository minutaTemaRepository;

    @Autowired
    public MinutaTemaService(MinutaTemaRepository minutaTemaRepository) {
        this.minutaTemaRepository = minutaTemaRepository;
    }

    public MinutaTemaEntity create(MinutaTemaEntity entity) throws Exception {
        try {
            return minutaTemaRepository.save(entity);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<MinutaTemaEntity> page() {
        return minutaTemaRepository.findAll();
    }

    public MinutaTemaEntity findById(Long id) {
        return minutaTemaRepository.findMinutaTemaById(id);
    }

    public MinutaTemaEntity update(MinutaTemaEntity entity) {
        return minutaTemaRepository.save(entity);
    }

    public void deleteById(Long id) {
        minutaTemaRepository.deleteById(id);
    }
}
