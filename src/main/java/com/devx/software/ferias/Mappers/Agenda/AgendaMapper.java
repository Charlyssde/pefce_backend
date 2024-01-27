package com.devx.software.ferias.Mappers.Agenda;

import com.devx.software.ferias.DTOs.Agenda.AgendaDTO;
import com.devx.software.ferias.Entities.Agenda.AgendaEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AgendaMapper {
    AgendaEntity dtoToEntity(AgendaDTO agendaDTO);

    AgendaDTO entityToDTO(AgendaEntity agendaEntity);

    List<AgendaEntity> listDtoToEntity(List<AgendaDTO> agendaDTOList);

    List<AgendaDTO> listEntityToDTO(List<AgendaEntity> agendaEntityList);
}
