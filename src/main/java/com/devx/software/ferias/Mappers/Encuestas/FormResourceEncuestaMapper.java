package com.devx.software.ferias.Mappers.Encuestas;

import com.devx.software.ferias.DTOs.Encuestas.FormResourceEncuestaDTO;
import com.devx.software.ferias.Entities.Encuestas.EncuestaEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FormResourceEncuestaMapper {
    FormResourceEncuestaDTO entityToDTO(EncuestaEntity encuestaEntity);

    EncuestaEntity dtoToEntity(FormResourceEncuestaDTO formResourceEncuestaDTO);

    List<FormResourceEncuestaDTO> listEntityToDto(List<EncuestaEntity> encuestaEntityList);

    List<EncuestaEntity> listDtoToEntity(List<FormResourceEncuestaDTO> formResourceEncuestaDTOList);
}
