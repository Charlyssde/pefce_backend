package com.devx.software.ferias.Mappers.Enterprises;

import com.devx.software.ferias.DTOs.Enterprises.EnterpriseListDTO;
import com.devx.software.ferias.Entities.Enterprises.EnterpriseEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EnterpriseListMapper {
    EnterpriseEntity dtoToEntity(EnterpriseListDTO enterpriseListDTO);

    EnterpriseListDTO entityToDTO(EnterpriseEntity enterpriseEntity);

    List<EnterpriseEntity> listDtoToEntity(List<EnterpriseListDTO> enterpriseListDTOList);

    List<EnterpriseListDTO> listEntityToDto(List<EnterpriseEntity> enterpriseEntityList);
}
