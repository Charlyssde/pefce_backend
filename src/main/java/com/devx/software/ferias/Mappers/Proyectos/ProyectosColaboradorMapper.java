package com.devx.software.ferias.Mappers.Proyectos;

import com.devx.software.ferias.DTOs.Projects.ProjectColaboratorDTO;
import com.devx.software.ferias.Entities.Projects.ProjectColaboratorEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProyectosColaboradorMapper {
    ProjectColaboratorEntity dtoToEntity(ProjectColaboratorDTO proyectosColaboradoresDTO);

    ProjectColaboratorDTO entityToDTO(ProjectColaboratorEntity proyectosColaboradoresEntity);

    List<ProjectColaboratorDTO> listEntityToDtoList(List<ProjectColaboratorEntity> proyectosColaboradoresList);

    List<ProjectColaboratorEntity> listDtoToEntity(List<ProjectColaboratorEntity> proyectosColaboradoresList);
}
