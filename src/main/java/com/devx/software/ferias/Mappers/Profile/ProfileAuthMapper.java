package com.devx.software.ferias.Mappers.Profile;

import com.devx.software.ferias.DTOs.Profiles.ProfileAuthDTO;
import com.devx.software.ferias.Entities.Profiles.ProfileEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileAuthMapper {
    ProfileAuthDTO entityToDTO(ProfileEntity profileEntity);
}
