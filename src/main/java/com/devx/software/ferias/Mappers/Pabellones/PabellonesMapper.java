package com.devx.software.ferias.Mappers.Pabellones;

import com.devx.software.ferias.Entities.Pabellones.PabellonesEntity;
import com.devx.software.ferias.DTOs.Pabellones.PabellonesDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PabellonesMapper {
    PabellonesEntity dtoToEntity(PabellonesDTO dto);

    PabellonesDTO entityToDTO(PabellonesEntity entity);

    List<PabellonesEntity> listDTOToEntity(List<PabellonesDTO> dto);

    List<PabellonesDTO> listEntityToDTO(List<PabellonesEntity> entity);
}
