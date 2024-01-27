package com.devx.software.ferias.Mappers.Projects;

import com.devx.software.ferias.DTOs.Projects.ProjectListDTO;
import com.devx.software.ferias.Entities.Projects.ProjectEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectListMapper {
    ProjectEntity dtoToEntity(ProjectListDTO projectEntity);

    ProjectListDTO entityToDTO(ProjectEntity projectEntity);

    List<ProjectEntity> listDtoToEntity(List<ProjectListDTO> projectDTOList);

    List<ProjectListDTO> listEntityToDTO(List<ProjectEntity> projectEntityList);
}
