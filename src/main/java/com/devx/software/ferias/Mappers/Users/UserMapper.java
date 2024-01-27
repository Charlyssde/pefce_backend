package com.devx.software.ferias.Mappers.Users;

import com.devx.software.ferias.DTOs.Users.UserDTO;
import com.devx.software.ferias.Entities.Users.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity dtoToEntity(UserDTO userDTO);

    UserDTO entityToDTO(UserEntity userEntity);

    List<UserEntity> listDtoToEntity(List<UserDTO> userDTOList);

    List<UserDTO> listEntityTiDTO(List<UserEntity> userEntityList);
}
