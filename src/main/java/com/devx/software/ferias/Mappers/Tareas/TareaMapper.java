package com.devx.software.ferias.Mappers.Tareas;

import com.devx.software.ferias.DTOs.Tasks.TaskDTO;
import com.devx.software.ferias.Entities.Tasks.TaskEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TareaMapper {
    TaskEntity dtoToEntity(TaskDTO dto);

    TaskDTO entityToDTO(TaskEntity entity);

    List<TaskEntity> listDtoToEntity(List<TaskDTO> listDto);

    List<TaskDTO> listEntityToDTO(List<TaskEntity> listEntity);

}
