package com.devx.software.ferias.Mappers.Capacitacion;

import com.devx.software.ferias.DTOs.Capacitacion.ContactosDTO;
import com.devx.software.ferias.DTOs.Capacitacion.ContactoPageCsvDTO;
import com.devx.software.ferias.DTOs.Capacitacion.ContactosPageDTO;
import com.devx.software.ferias.Entities.Capacitacion.ContactosEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContactosMapper {
    ContactosEntity dtoToEntity(ContactosDTO dto);

    ContactosDTO entityToDTO(ContactosEntity entity);

    List<ContactosEntity> listDTOToEntity(List<ContactosDTO> dto);

    List<ContactosDTO> listEntityToDTO(List<ContactosEntity> entity);

    List<ContactosPageDTO> listEntityToPageDTO(List<ContactosEntity> entity);

    ContactosEntity csvDtoToEntity(ContactoPageCsvDTO contactoPageCsvDTO);
}
