package com.devx.software.ferias.Mappers.Users;

import com.devx.software.ferias.DTOs.Users.UserInstitutionSelectDTO;
import com.devx.software.ferias.Entities.Users.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserInstitutionSelectMapper {
    UserEntity dtoToEntity(UserInstitutionSelectDTO userInstitutionSelectDTO);

    UserInstitutionSelectDTO entityToDTO(UserEntity userEntity);

    List<UserEntity> listDtoToEntity(List<UserInstitutionSelectDTO> userInstitutionSelectDTOS);

    List<UserInstitutionSelectDTO> listEntityToDTO(List<UserEntity> userEntities);
}
