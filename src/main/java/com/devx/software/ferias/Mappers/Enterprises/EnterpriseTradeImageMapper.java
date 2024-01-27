package com.devx.software.ferias.Mappers.Enterprises;

import com.devx.software.ferias.DTOs.Enterprises.EnterpriseTradeImageDTO;
import com.devx.software.ferias.Entities.Enterprises.EnterpriseTradeImageEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EnterpriseTradeImageMapper {
    EnterpriseTradeImageEntity dtoToEntity(EnterpriseTradeImageDTO enterpriseTradeImageDTO);

    EnterpriseTradeImageDTO entityToDto(EnterpriseTradeImageEntity enterpriseTradeImageEntity);

    List<EnterpriseTradeImageEntity> listDtoToEntity(List<EnterpriseTradeImageDTO> enterpriseTradeImageDTOList);

    List<EnterpriseTradeImageDTO> listEntityToDto(List<EnterpriseTradeImageEntity> enterpriseTradeImageEntityList);
}
