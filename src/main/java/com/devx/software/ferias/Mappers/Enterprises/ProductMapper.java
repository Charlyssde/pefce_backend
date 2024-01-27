package com.devx.software.ferias.Mappers.Enterprises;

import com.devx.software.ferias.DTOs.Enterprises.ProductDTO;
import com.devx.software.ferias.Entities.Enterprises.ProductEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductEntity dtoToEntity(ProductDTO productDTO);

    ProductDTO entityToDto(ProductEntity productEntity);

    List<ProductEntity> listDtoToEntity(List<ProductDTO> productDTOList);

    List<ProductDTO> listEntotyToDto(List<ProductEntity> productEntityList);

}
