package com.devx.software.ferias.Mappers.Profile;

import com.devx.software.ferias.DTOs.Profiles.FormResourcesProfileDTO;
import com.devx.software.ferias.DTOs.Profiles.ProfileDTO;
import com.devx.software.ferias.Entities.Profiles.ProfileEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    ProfileEntity dtoToEntity(ProfileDTO profileDTO);

    List<ProfileEntity> listDTOToEntity(List<ProfileDTO> profilesDTO);

    ProfileDTO entityToDTO(ProfileEntity profileEntity);

    List<ProfileDTO> listEntityToDTO(List<ProfileEntity> profilesEntity);

    FormResourcesProfileDTO entityToformResourcesProfileDTO(ProfileEntity profileEntity);

    ProfileEntity formResourcesProfileDtoToEntity(FormResourcesProfileDTO formResourcesProfileDTO);

    List<FormResourcesProfileDTO> listEntityToformResourcesProfileDTO(List<ProfileEntity> profilesEntity);

    List<ProfileEntity> listFormResourcesProfileDTOToEntity(List<FormResourcesProfileDTO> formResourcesProfileDTOS);

}
