package com.devx.software.ferias.Mappers.Projects;

import com.devx.software.ferias.DTOs.Projects.ProjectDTO;
import com.devx.software.ferias.Entities.Projects.ProjectEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    ProjectEntity dtoToEntity(ProjectDTO projectEntity);

    ProjectDTO entityToDTO(ProjectEntity projectEntity);

    List<ProjectEntity> listDtoToEntity(List<ProjectDTO> projectDTOList);

    List<ProjectDTO> listEntityToDTO(List<ProjectEntity> projectEntityList);
}
