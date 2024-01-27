package com.devx.software.ferias.Repositories.Catalogs;

import com.devx.software.ferias.Entities.Catalogs.EstadosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadosRepositoy extends JpaRepository<EstadosEntity, Long> {
}
