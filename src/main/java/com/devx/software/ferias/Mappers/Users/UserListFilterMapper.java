package com.devx.software.ferias.Mappers.Users;

import com.devx.software.ferias.DTOs.Users.UserListFilterDTO;
import com.devx.software.ferias.Entities.Users.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserListFilterMapper {

    UserEntity dtoToEntity(UserListFilterDTO userListFilterDTO);

    UserListFilterDTO entityToDTO(UserEntity userEntity);

    List<UserEntity> listDtoToEntity(List<UserListFilterDTO> userListFilterDTOList);

    List<UserListFilterDTO> listEntityToDTO(List<UserEntity> userEntityList);
}
