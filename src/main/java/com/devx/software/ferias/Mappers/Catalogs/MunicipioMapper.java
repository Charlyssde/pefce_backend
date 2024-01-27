package com.devx.software.ferias.Mappers.Catalogs;

import com.devx.software.ferias.DTOs.Catalogs.MunicipioDTO;
import com.devx.software.ferias.Entities.Catalogs.MunicipiosEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MunicipioMapper {

    MunicipiosEntity dtoToEntity(MunicipioDTO municipioDTO);

    MunicipioDTO entityToDTO(MunicipiosEntity municipiosEntity);

    List<MunicipiosEntity> listDTOToEntity(List<MunicipioDTO> municipioDTOList);

    List<MunicipioDTO> listEntityToDTO(List<MunicipiosEntity> municipiosEntityList);
}
