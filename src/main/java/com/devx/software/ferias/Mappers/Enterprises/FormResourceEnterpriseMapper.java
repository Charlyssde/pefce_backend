package com.devx.software.ferias.Mappers.Enterprises;

import com.devx.software.ferias.DTOs.Enterprises.FormResourceEnterpriseDTO;
import com.devx.software.ferias.Entities.Enterprises.EnterpriseEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FormResourceEnterpriseMapper {
    FormResourceEnterpriseDTO entityToDTO(EnterpriseEntity enterpriseEntity);

    EnterpriseEntity dtoToEntity(FormResourceEnterpriseDTO formResourceEnterpriseDTO);

    List<FormResourceEnterpriseDTO> listEntityToDto(List<EnterpriseEntity> enterpriseEntityList);

    List<EnterpriseEntity> listDtoToEntity(List<FormResourceEnterpriseDTO> formResourceEnterpriseDTOList);
}
