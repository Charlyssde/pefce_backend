package com.devx.software.ferias.Mappers.Users;

import com.devx.software.ferias.DTOs.Users.FormResourcesUserDTO;
import com.devx.software.ferias.Entities.Users.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FormResourcesUserMapper {
    FormResourcesUserDTO entityToDTO(UserEntity user);

    UserEntity dtoToEntity(FormResourcesUserDTO formResourcesUserDTO);

    List<FormResourcesUserDTO> entitiesToDTOs(List<UserEntity> userEntityList);

    List<UserEntity> dtosToEntities(List<FormResourcesUserDTO> formResourcesUserDTOList);
}
