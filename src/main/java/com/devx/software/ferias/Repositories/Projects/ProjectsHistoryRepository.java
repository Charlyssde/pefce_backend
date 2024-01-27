package com.devx.software.ferias.Repositories.Projects;

import com.devx.software.ferias.Entities.Projects.ProjectHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectsHistoryRepository extends JpaRepository<ProjectHistoryEntity, Long> {

}
