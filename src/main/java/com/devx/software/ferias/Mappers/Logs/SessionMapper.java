package com.devx.software.ferias.Mappers.Logs;

import com.devx.software.ferias.DTOs.Logs.SessionDTO;
import com.devx.software.ferias.Entities.Logs.SessionEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SessionMapper {
    SessionEntity dtoToEntity(SessionDTO sessionDTO);

    SessionDTO entityToDto(SessionEntity sessionEntity);

    List<SessionEntity> listDtoToEntity(List<SessionDTO> sessionDTOList);

    List<SessionDTO> listEntityToDto(List<SessionEntity> sessionEntityList);
}
