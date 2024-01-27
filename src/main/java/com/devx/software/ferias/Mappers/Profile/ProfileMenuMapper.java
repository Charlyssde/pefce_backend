package com.devx.software.ferias.Mappers.Profile;

import com.devx.software.ferias.DTOs.Profiles.ProfileMenuDTO;
import com.devx.software.ferias.DTOs.Profiles.ProfileMenuNoChildrenDTO;
import com.devx.software.ferias.Entities.Profiles.ProfileMenuEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProfileMenuMapper {

    List<ProfileMenuDTO> listProfileMenuDtoToEntities(List<ProfileMenuEntity> profileMenuEntities);

    List<ProfileMenuNoChildrenDTO> listProfileMenuNoChildrenDtoToEntities(List<ProfileMenuEntity> profileMenuEntities);

}
