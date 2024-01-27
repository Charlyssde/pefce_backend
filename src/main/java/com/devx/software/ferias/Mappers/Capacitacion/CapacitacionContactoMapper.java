package com.devx.software.ferias.Mappers.Capacitacion;

import com.devx.software.ferias.DTOs.Capacitacion.CapacitacionContactoDTO;
import com.devx.software.ferias.DTOs.Capacitacion.CapacitacionContactoPageDTO;
import com.devx.software.ferias.Entities.Capacitacion.CapacitacionContactoEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CapacitacionContactoMapper {
    CapacitacionContactoEntity dtoToEntity(CapacitacionContactoDTO capacitacionContactoDTO);

    CapacitacionContactoDTO entityToDTO(CapacitacionContactoEntity capacitacionContactoEntity);

    List<CapacitacionContactoDTO> listEntityToListDTO(List<CapacitacionContactoEntity> capacitacionContactoEntities);

    List<CapacitacionContactoPageDTO> listEntityToPageDTO(List<CapacitacionContactoEntity> capacitacionContactoEntities);
}
