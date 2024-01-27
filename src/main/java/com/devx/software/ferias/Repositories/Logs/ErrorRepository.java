package com.devx.software.ferias.Repositories.Logs;

import com.devx.software.ferias.Entities.Logs.ErrorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ErrorRepository extends JpaRepository<ErrorEntity, Long> {
}
