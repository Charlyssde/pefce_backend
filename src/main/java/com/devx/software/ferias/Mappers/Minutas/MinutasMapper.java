package com.devx.software.ferias.Mappers.Minutas;


import com.devx.software.ferias.DTOs.Minutas.MinutasDTO;
import com.devx.software.ferias.Entities.Minutas.MinutasEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MinutasMapper {
    MinutasEntity dtoToEntity(MinutasDTO dto);

    MinutasDTO entityToDTO(MinutasEntity entity);

    List<MinutasEntity> listDTOToEntity(List<MinutasDTO> dto);

    List<MinutasDTO> listEntityToDTO(List<MinutasEntity> entity);
}
