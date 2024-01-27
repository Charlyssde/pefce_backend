package com.devx.software.ferias.Mappers.Capacitacion;

import com.devx.software.ferias.DTOs.Capacitacion.CapacitacionesDTO;
import com.devx.software.ferias.Entities.Capacitacion.CapacitacionesEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CapacitacionesMapper {
    CapacitacionesEntity dtoToEntity(CapacitacionesDTO dto);

    CapacitacionesDTO entityToDTO(CapacitacionesEntity entity);

    List<CapacitacionesEntity> listDTOToEntity(List<CapacitacionesDTO> dto);

    List<CapacitacionesDTO> listEntityToDTO(List<CapacitacionesEntity> entity);
}
