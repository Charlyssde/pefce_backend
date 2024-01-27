package com.devx.software.ferias.Mappers.Encuestas;

import com.devx.software.ferias.DTOs.Encuestas.EncuestaListDTO;
import com.devx.software.ferias.Entities.Encuestas.EncuestaEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EncuestaListMapper {
    EncuestaEntity dtoToEntity(EncuestaListDTO encuestaListDTO);

    EncuestaListDTO entityToDTO(EncuestaEntity encuestaEntity);

    List<EncuestaEntity> listDtoToEntity(List<EncuestaListDTO> encuestaListDTOList);

    List<EncuestaListDTO> listEntityToDto(List<EncuestaEntity> encuestaEntityList);
}
