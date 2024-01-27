package com.devx.software.ferias.Mappers.Capacitacion;

import com.devx.software.ferias.DTOs.Capacitacion.PreguntasDTO;
import com.devx.software.ferias.Entities.Capacitacion.PreguntasEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PreguntasMapper {
    PreguntasEntity dtoToEntity(PreguntasDTO dto);

    PreguntasDTO entityToDto(PreguntasEntity entity);

    List<PreguntasEntity> listDtoToEntity(List<PreguntasDTO> dto);

    List<PreguntasDTO> listEntityToDto(List<PreguntasEntity> entity);
}
