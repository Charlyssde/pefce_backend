package com.devx.software.ferias.Repositories.Minutas;

import com.devx.software.ferias.Entities.Minutas.MinutasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MinutasRepository extends JpaRepository<MinutasEntity, Long> {
    MinutasEntity findMinutaById(Long id);

//    @Query(value="SELECT m FROM MinutasEntity m ORDER BY m.id DESC LIMIT 1", nativeQuery = true)
//    List<MinutasEntity> findAll();

    List<MinutasEntity> findAllByOrderByIdDesc();
}
