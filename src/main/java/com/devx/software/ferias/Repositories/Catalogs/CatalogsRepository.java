package com.devx.software.ferias.Repositories.Catalogs;

import com.devx.software.ferias.Entities.Catalogs.CatalogsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatalogsRepository extends JpaRepository<CatalogsEntity, Long> {

    CatalogsEntity findCatalogByTipoCatalogoAndNombre(String tipo, String nombre);


    CatalogsEntity findCatalogoByTipoCatalogoAndNombre(String tipo, String nombre);


    CatalogsEntity findCatalogoById(Long id);

    List<CatalogsEntity> findAllByTipoCatalogoAndNombre(String tipoCatalogo, String nombre);

    List<CatalogsEntity> findAllByTipoCatalogoAndActivoTrue(String tipoCatalogo);

    List<CatalogsEntity> findAllByTipoCatalogoAndActivoTrueAndIdCatalogoPadre(String tipoCatalogo, Long padre);

    Page<CatalogsEntity> findAllByTipoCatalogoAndActivoTrueOrderByNombreAsc(String tipoCatalogo, Pageable pageable);

    Page<CatalogsEntity> findAllByTipoCatalogoAndActivoTrueAndNombreContainsIgnoreCaseOrderByNombreAsc(String tipoCatalogo, String nombre, Pageable pageable);

    Page<CatalogsEntity> findAllByTipoCatalogoAndActivoTrueAndIdCatalogoPadreOrderByNombreAsc(String tipoCatalogo, Long padreId, Pageable pageable);

    Page<CatalogsEntity> findAllByTipoCatalogoAndActivoTrueAndIdCatalogoPadreAndNombreContainsIgnoreCaseOrderByNombreAsc(String tipoCatalogo, Long padreId, String nombre, Pageable pageable);
}

