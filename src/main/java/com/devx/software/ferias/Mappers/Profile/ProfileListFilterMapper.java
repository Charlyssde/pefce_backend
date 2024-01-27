package com.devx.software.ferias.Mappers.Profile;

import com.devx.software.ferias.DTOs.Profiles.ProfileListFilterDTO;
import com.devx.software.ferias.Entities.Profiles.ProfileEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProfileListFilterMapper {
    ProfileEntity dtoToEntity(ProfileListFilterDTO profileListFilterDTO);

    ProfileListFilterDTO entityToDTO(ProfileEntity profileEntity);

    List<ProfileEntity> listDtoToEntity(List<ProfileListFilterDTO> profileListFilterDTOList);

    List<ProfileListFilterDTO> listEntityToDTO(List<ProfileEntity> profileEntityList);
}
