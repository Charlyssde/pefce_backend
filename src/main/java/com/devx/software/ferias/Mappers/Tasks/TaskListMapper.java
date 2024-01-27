package com.devx.software.ferias.Mappers.Tasks;

import com.devx.software.ferias.DTOs.Tasks.TaskListDTO;
import com.devx.software.ferias.Entities.Tasks.TaskEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskListMapper {
    TaskEntity dtoToEntity(TaskListDTO taskListDTO);

    TaskListDTO entityToDTO(TaskEntity taskEntity);

    List<TaskEntity> listDtoToEntity(List<TaskListDTO> taskListDTOList);

    List<TaskListDTO> listEntityToDto(List<TaskEntity> taskEntityList);
}
