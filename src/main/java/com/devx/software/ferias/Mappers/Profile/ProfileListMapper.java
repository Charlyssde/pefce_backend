package com.devx.software.ferias.Mappers.Profile;

import com.devx.software.ferias.DTOs.Profiles.ProfileListDTO;
import com.devx.software.ferias.Entities.Profiles.ProfileEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProfileListMapper {
    ProfileEntity dtoToEntity(ProfileListDTO profileListDTO);

    ProfileListDTO entityToDto(ProfileEntity profileEntity);

    List<ProfileEntity> listDtoToEntity(List<ProfileListDTO> profileListDTOS);

    List<ProfileListDTO> listEntityToDto(List<ProfileEntity> profileEntities);
}
