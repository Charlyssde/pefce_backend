package com.devx.software.ferias.Mappers.Requests;

import com.devx.software.ferias.DTOs.Requests.RequestListDTO;
import com.devx.software.ferias.Entities.Requests.RequestEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RequestListMapper {
    RequestEntity dtoToEntity(RequestListDTO requestListDTO);

    RequestListDTO entityToDTO(RequestEntity requestEntity);

    List<RequestEntity> listDtoToEntity(List<RequestListDTO> requestListDTOList);

    List<RequestListDTO> listEntityToDto(List<RequestEntity> requestEntityList);
}
