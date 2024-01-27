package com.devx.software.ferias.Mappers.Projects;

import com.devx.software.ferias.DTOs.Projects.ProjectHistoryDTO;
import com.devx.software.ferias.Entities.Projects.ProjectHistoryEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectHistoryMapper {
    ProjectHistoryEntity dtoToEntity(ProjectHistoryDTO projectHistoryDTO);

    ProjectHistoryDTO entityToDTO(ProjectHistoryEntity projectHistoryEntity);

    List<ProjectHistoryEntity> listDtoToEntity(List<ProjectHistoryEntity> projectHistoryEntityList);

    List<ProjectHistoryDTO> listEntityToDTO(List<ProjectHistoryEntity> projectHistoryEntityList);
}
