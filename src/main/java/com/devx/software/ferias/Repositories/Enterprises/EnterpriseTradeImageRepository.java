package com.devx.software.ferias.Repositories.Enterprises;

import com.devx.software.ferias.Entities.Enterprises.EnterpriseTradeImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnterpriseTradeImageRepository extends JpaRepository<EnterpriseTradeImageEntity, Long> {
}
