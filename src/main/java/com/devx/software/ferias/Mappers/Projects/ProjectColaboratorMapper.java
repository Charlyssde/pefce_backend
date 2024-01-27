package com.devx.software.ferias.Mappers.Projects;

import com.devx.software.ferias.DTOs.Projects.ProjectColaboratorDTO;
import com.devx.software.ferias.Entities.Projects.ProjectColaboratorEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectColaboratorMapper {
    ProjectColaboratorEntity dtoToEntity(ProjectColaboratorDTO projectColaboratorDTO);

    ProjectColaboratorDTO entityToDTO(ProjectColaboratorEntity projectColaboratorEntity);

    List<ProjectColaboratorEntity> listDtoToEntity(List<ProjectColaboratorDTO> projectColaboratorDTOList);

    List<ProjectColaboratorDTO> listEntityToDTO(List<ProjectColaboratorEntity> projectColaboratorEntityList);
}
