package com.devx.software.ferias.Services.Idiomas;

import com.devx.software.ferias.Entities.Idiomas.IdiomasEntity;
import com.devx.software.ferias.Repositories.Idiomas.IdiomasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IdiomasService {

    private final IdiomasRepository idiomasRepository;

    @Autowired
    public IdiomasService(IdiomasRepository idiomasRepository) {
        this.idiomasRepository = idiomasRepository;
    }

    public IdiomasEntity create(IdiomasEntity model) throws Exception {
        try {
            return idiomasRepository.save(model);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<IdiomasEntity> read() {
        return this.idiomasRepository.findAll();
    }

    public Page<IdiomasEntity> page(Pageable pageable, String nombre) {
        if (nombre == null) {
            return this.idiomasRepository.findAllByOrderByIdiomaAsc(pageable);
        } else {
            return this.idiomasRepository.findAllByIdiomaContainsIgnoreCaseOrderByIdiomaAsc(nombre, pageable);
        }
    }

    public IdiomasEntity findById(Long id){return idiomasRepository.findById(id).get();}

    public IdiomasEntity update(IdiomasEntity model) {
        return idiomasRepository.save(model);
    }

    public void delete(Long id) {
        idiomasRepository.deleteById(id);
    }
}
