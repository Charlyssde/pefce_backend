package com.devx.software.ferias.Services.Minutas;

import com.devx.software.ferias.Entities.Minutas.MinutaArchivoEntity;
import com.devx.software.ferias.Repositories.Minutas.MinutaArchivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MinutaArchivoService {

    private final MinutaArchivoRepository minutaArchivoRepository;

    @Autowired
    public MinutaArchivoService(MinutaArchivoRepository minutaArchivoRepository) {
        this.minutaArchivoRepository = minutaArchivoRepository;
    }

    public MinutaArchivoEntity create(MinutaArchivoEntity entity) throws Exception {
        try {
            return minutaArchivoRepository.save(entity);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<MinutaArchivoEntity> page() {
        return minutaArchivoRepository.findAll();
    }

    public MinutaArchivoEntity findById(Long id) {
        return minutaArchivoRepository.findMinutaArchivoById(id);
    }

    public MinutaArchivoEntity update(MinutaArchivoEntity entity) {
        return minutaArchivoRepository.save(entity);
    }

    public void deleteById(Long id) {
        minutaArchivoRepository.deleteById(id);
    }
}
