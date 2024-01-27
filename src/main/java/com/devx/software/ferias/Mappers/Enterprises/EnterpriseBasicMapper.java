package com.devx.software.ferias.Mappers.Enterprises;

import com.devx.software.ferias.DTOs.Enterprises.EnterpriseBasicDTO;
import com.devx.software.ferias.DTOs.Enterprises.EnterpriseListDTO;
import com.devx.software.ferias.Entities.Enterprises.EnterpriseEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EnterpriseBasicMapper {
    EnterpriseEntity dtoToEntity(EnterpriseBasicDTO enterpriseListDTO);

    EnterpriseBasicDTO entityToDTO(EnterpriseEntity enterpriseEntity);

    List<EnterpriseEntity> listDtoToEntity(List<EnterpriseBasicDTO> enterpriseListDTOList);

    List<EnterpriseBasicDTO> listEntityToDto(List<EnterpriseEntity> enterpriseEntityList);
}
