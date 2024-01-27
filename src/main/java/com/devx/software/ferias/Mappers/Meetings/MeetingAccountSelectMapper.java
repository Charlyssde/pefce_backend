package com.devx.software.ferias.Mappers.Meetings;

import com.devx.software.ferias.DTOs.Meetings.MeetingAccountSelectDTO;
import com.devx.software.ferias.Entities.Meetings.MeetingAccountEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MeetingAccountSelectMapper {
    MeetingAccountEntity dtoToEntity(MeetingAccountSelectDTO meetingAccountSelectDTO);

    MeetingAccountSelectDTO entityToDto(MeetingAccountEntity meetingAccountEntity);

    List<MeetingAccountEntity> listDtoToEntity(List<MeetingAccountSelectDTO> meetingAccountSelectDTO);

    List<MeetingAccountSelectDTO> listEntityToDto(List<MeetingAccountEntity> meetingAccountEntity);
}
