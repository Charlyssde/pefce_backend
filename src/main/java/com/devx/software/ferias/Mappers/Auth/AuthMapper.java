package com.devx.software.ferias.Mappers.Auth;

import com.devx.software.ferias.DTOs.Auth.LoginResponse;
import com.devx.software.ferias.Entities.Users.UserEntity;
import com.devx.software.ferias.DTOs.Users.UserDTO;
import com.devx.software.ferias.Entities.Users.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthMapper {

    UserEntity dtoToEntity(UserDTO userModel);

    LoginResponse entityToDTO(UserEntity userEntity);


}
