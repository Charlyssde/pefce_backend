package com.devx.software.ferias.Mappers.Catalogs;

import com.devx.software.ferias.DTOs.Catalogs.EstadoMunicipioDTO;
import com.devx.software.ferias.Entities.Catalogs.EstadosMunicipiosEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EstadoMunicipioMapper {

    EstadosMunicipiosEntity dtoToEntity(EstadoMunicipioDTO estadoMunicipioDTO);

    EstadoMunicipioDTO entityToDTO(EstadosMunicipiosEntity estadosMunicipiosEntity);

    List<EstadosMunicipiosEntity> listDTOToEntity(List<EstadoMunicipioDTO> estadoMunicipioDTOList);

    List<EstadoMunicipioDTO> listEntityToDTO(List<EstadosMunicipiosEntity> estadosMunicipiosEntityList);
}
