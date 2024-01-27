package com.devx.software.ferias.Mappers.Proyectos;


import com.devx.software.ferias.DTOs.Projects.ProjectDTO;
import com.devx.software.ferias.Entities.Projects.ProjectEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProyectosMapper {
    ProjectEntity dtoToEntity(ProjectDTO dto);

    ProjectDTO entityToDTO(ProjectEntity entity);

    List<ProjectEntity> listDTOToEntity(List<ProjectDTO> dto);

    List<ProjectDTO> listEntityToDTO(List<ProjectEntity> entity);
}
