package com.devx.software.ferias.Repositories.PromocionDigital;

import com.devx.software.ferias.Entities.PromocionDigital.PromocionDigitalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromocionDigitalRepository extends JpaRepository<PromocionDigitalEntity, Long> {
    PromocionDigitalEntity findPromocionDigitalById(Long id);
}
