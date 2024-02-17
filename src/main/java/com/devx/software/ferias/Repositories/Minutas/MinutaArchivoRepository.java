package com.devx.software.ferias.Repositories.Minutas;

import com.devx.software.ferias.Entities.Minutas.MinutaArchivoEntity;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MinutaArchivoRepository extends JpaRepository<MinutaArchivoEntity, Long> {
   MinutaArchivoEntity findMinutaArchivoById(Long id);
     
    @Query( value = "Select * from a_minuta__a_archivo ma where ma.id_minuta = ?1" , nativeQuery = true)
    List  <MinutaArchivoEntity> findMinutaArchivoByIdlist(Long id);
}
