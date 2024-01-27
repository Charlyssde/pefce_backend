package com.devx.software.ferias.Mappers.Enterprises;

import com.devx.software.ferias.DTOs.Enterprises.EnterpriseListUserFilterDTO;
import com.devx.software.ferias.Entities.Enterprises.EnterpriseEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EnterpriseListUserFilterMapper {
    EnterpriseEntity dtoToEntity(EnterpriseListUserFilterDTO enterpriseListUserFilterDTO);

    EnterpriseListUserFilterDTO entityToDTO(EnterpriseEntity enterpriseEntity);

    List<EnterpriseEntity> listDtoToEntity(List<EnterpriseListUserFilterDTO> enterpriseListUserFilterDTOList);

    List<EnterpriseListUserFilterDTO> listEntityToDTO(List<EnterpriseEntity> enterpriseEntityList);
}
