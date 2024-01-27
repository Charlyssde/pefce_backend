package com.devx.software.ferias.Repositories.Idiomas;

import com.devx.software.ferias.Entities.Catalogs.CatalogsEntity;
import com.devx.software.ferias.Entities.Idiomas.IdiomasEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdiomasRepository extends JpaRepository<IdiomasEntity, Long> {

    Page<IdiomasEntity> findAllByOrderByIdiomaAsc(Pageable pageable);

    Page<IdiomasEntity> findAllByIdiomaContainsIgnoreCaseOrderByIdiomaAsc(String nombre, Pageable pageable);
    IdiomasEntity findIdiomaById(Long id);
}
