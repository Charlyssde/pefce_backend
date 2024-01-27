package com.devx.software.ferias.Repositories.Enterprises;

import com.devx.software.ferias.Entities.Enterprises.EnterpriseAccessRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnterpriseAccessRequestRepository extends JpaRepository<EnterpriseAccessRequestEntity, Long> {
}
