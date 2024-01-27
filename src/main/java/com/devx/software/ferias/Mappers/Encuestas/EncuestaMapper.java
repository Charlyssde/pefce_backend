package com.devx.software.ferias.Mappers.Encuestas;

import com.devx.software.ferias.DTOs.Encuestas.EncuestaDTO;
import com.devx.software.ferias.Entities.Encuestas.EncuestaEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EncuestaMapper {

    EncuestaEntity dtoToEntity(EncuestaDTO encuestaDTO);

    EncuestaDTO entityToDto(EncuestaEntity encuestaEntity);

    List<EncuestaEntity> listDtoToEntity(List<EncuestaDTO> encuestaDTOList);

    List<EncuestaDTO> listEntotyToDto(List<EncuestaEntity> encuestaEntityList);

}
