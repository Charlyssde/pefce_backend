package com.devx.software.ferias.Mappers.Capacitacion;

import com.devx.software.ferias.DTOs.Capacitacion.UsuarioCapacitacionDTO;
import com.devx.software.ferias.Entities.Capacitacion.UsuarioCapacitacionEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioCapacitacionMapper {
    UsuarioCapacitacionEntity dtoToEntity(UsuarioCapacitacionDTO dto);

    UsuarioCapacitacionDTO entityToDto(UsuarioCapacitacionEntity entity);

    List<UsuarioCapacitacionEntity> listDtoToEntity(List<UsuarioCapacitacionDTO> dto);

    List<UsuarioCapacitacionDTO> listEntityToDto(List<UsuarioCapacitacionEntity> entity);
}
