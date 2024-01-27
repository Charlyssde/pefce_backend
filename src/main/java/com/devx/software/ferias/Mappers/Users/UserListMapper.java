package com.devx.software.ferias.Mappers.Users;

import com.devx.software.ferias.DTOs.Users.UserListDTO;
import com.devx.software.ferias.Entities.Users.UserEntity;
import com.devx.software.ferias.Entities.Users.UserProfileEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserListMapper {
    UserEntity dtoToEntity(UserListDTO userListDTO);

    UserListDTO entityToDto(UserEntity userEntity);

    List<UserEntity> listDtoToEntity(List<UserListDTO> userListDTOS);

    List<UserListDTO> listEntityToDto(List<UserEntity> userEntities);

}
