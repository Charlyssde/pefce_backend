package com.devx.software.ferias.Mappers.Catalogs;

import com.devx.software.ferias.DTOs.Catalogs.CatalogsDTO;
import com.devx.software.ferias.Entities.Catalogs.CatalogsEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CatalogsMapper {
    CatalogsEntity dtoToEntity(CatalogsDTO catalogsDTO);

    CatalogsDTO entityToDTO(CatalogsEntity CatalogsEntity);

    List<CatalogsEntity> dtosToEntities(List<CatalogsDTO> catalogosDTOS);

    List<CatalogsDTO> entitiesToDTOS(List<CatalogsEntity> catalogosEntities);
}
