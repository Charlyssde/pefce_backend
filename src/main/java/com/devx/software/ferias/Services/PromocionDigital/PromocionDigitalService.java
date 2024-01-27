package com.devx.software.ferias.Services.PromocionDigital;

import com.devx.software.ferias.Entities.PromocionDigital.PromocionDigitalEntity;
import com.devx.software.ferias.Repositories.PromocionDigital.PromocionDigitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromocionDigitalService {

    private final PromocionDigitalRepository promocionDigitalRepository;

    @Autowired
    public PromocionDigitalService(PromocionDigitalRepository promocionDigitalRepository) {
        this.promocionDigitalRepository = promocionDigitalRepository;
    }

    public PromocionDigitalEntity create(PromocionDigitalEntity entity) throws Exception {
        try {
            return promocionDigitalRepository.save(entity);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<PromocionDigitalEntity> page() {
        return promocionDigitalRepository.findAll();
    }

    public PromocionDigitalEntity findById(Long id) {
        return promocionDigitalRepository.findPromocionDigitalById(id);
    }

    public PromocionDigitalEntity update(PromocionDigitalEntity entity) {
        return promocionDigitalRepository.save(entity);
    }

    public void deleteById(Long id) {
        promocionDigitalRepository.deleteById(id);
    }

}
