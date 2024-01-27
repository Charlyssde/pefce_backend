package com.devx.software.ferias.Mappers.Projects;

import com.devx.software.ferias.DTOs.Projects.ProjectBasicFormRequestDTO;
import com.devx.software.ferias.Entities.Projects.ProjectEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectBasicFormRequestMapper {
    ProjectEntity dtoToEntity(ProjectBasicFormRequestDTO projectBasicFormRequestDTO);

    ProjectBasicFormRequestDTO entityToDTO(ProjectEntity projectEntity);

    List<ProjectEntity> listDtoToEntity(List<ProjectBasicFormRequestDTO> projectBasicFormRequestDTOS);

    List<ProjectBasicFormRequestDTO> listEntityToDTO(List<ProjectEntity> projectEntities);
}
