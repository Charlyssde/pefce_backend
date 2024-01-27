package com.devx.software.ferias.Services.Minutas;

import com.devx.software.ferias.Entities.Minutas.ContactoMinutaEntity;
import com.devx.software.ferias.Repositories.Minutas.ContactoMinutaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactoMinutaService {

    private final ContactoMinutaRepository contactoMinutaRepository;

    @Autowired
    public ContactoMinutaService(ContactoMinutaRepository contactoMinutaRepository) {
        this.contactoMinutaRepository = contactoMinutaRepository;
    }

    public ContactoMinutaEntity create(ContactoMinutaEntity entity) throws Exception {
        try {
            return contactoMinutaRepository.save(entity);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<ContactoMinutaEntity> page() {
        return contactoMinutaRepository.findAll();
    }

    public ContactoMinutaEntity findById(Long id) {
        return contactoMinutaRepository.findContactoMinutaById(id);
    }

    public ContactoMinutaEntity update(ContactoMinutaEntity entity) {
        return contactoMinutaRepository.save(entity);
    }

    public void deleteById(Long id) {
        contactoMinutaRepository.deleteById(id);
    }
}
