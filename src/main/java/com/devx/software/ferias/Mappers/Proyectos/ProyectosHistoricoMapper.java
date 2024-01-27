package com.devx.software.ferias.Mappers.Proyectos;

import com.devx.software.ferias.DTOs.Projects.ProjectHistoryDTO;
import com.devx.software.ferias.Entities.Projects.ProjectHistoryEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProyectosHistoricoMapper {
    ProjectHistoryEntity dtoToEntity(ProjectHistoryDTO dto);

    ProjectHistoryDTO entityToDTO(ProjectHistoryEntity entity);

    List<ProjectHistoryEntity> dtoToEntity(List<ProjectHistoryDTO> listDto);

    List<ProjectHistoryDTO> entityToDTO(List<ProjectHistoryEntity> listEntity);
}
