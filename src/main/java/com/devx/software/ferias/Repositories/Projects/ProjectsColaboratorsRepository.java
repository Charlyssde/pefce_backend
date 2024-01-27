package com.devx.software.ferias.Repositories.Projects;

import com.devx.software.ferias.Entities.Projects.ProjectColaboratorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectsColaboratorsRepository extends JpaRepository<ProjectColaboratorEntity, Long> {

}
