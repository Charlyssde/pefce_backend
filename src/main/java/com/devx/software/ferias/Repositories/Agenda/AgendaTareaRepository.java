package com.devx.software.ferias.Repositories.Agenda;

import com.devx.software.ferias.Entities.Agenda.AgendaTareaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendaTareaRepository extends JpaRepository<AgendaTareaEntity, Long> {
    AgendaTareaEntity findByTareaId(Long id);
}
