package com.devx.software.ferias.Mappers.Files;

import com.devx.software.ferias.DTOs.Files.FileDTO;
import com.devx.software.ferias.Entities.Files.FileEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FilesMapper {
    FileEntity dtoToEntity(FileDTO fileDTO);

    FileDTO entityToDto(FileEntity fileEntity);

    List<FileEntity> listDtoToEntity(List<FileDTO> fileDTOList);

    List<FileDTO> listEntityToDto(List<FileEntity> fileEntityList);
}
