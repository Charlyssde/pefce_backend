package com.devx.software.ferias.Mappers.Tasks;

import com.devx.software.ferias.DTOs.Tasks.FormResourceTaskDTO;
import com.devx.software.ferias.Entities.Tasks.TaskEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FormResourceTaskMapper {
    FormResourceTaskDTO entityToDTO(TaskEntity taskEntity);

    TaskEntity dtoToEntity(FormResourceTaskDTO formResourceTaskDTO);

    List<FormResourceTaskDTO> listEntityToDto(List<TaskEntity> taskEntityList);

    List<TaskEntity> listDtoToEntity(List<FormResourceTaskDTO> formResourceTaskDTOList);
}
