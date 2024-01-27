package com.devx.software.ferias.Repositories.Meetings;

import com.devx.software.ferias.Entities.Meetings.MeetingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetingsRepository extends JpaRepository<MeetingEntity, Long> {

    List<MeetingEntity> findAllByMeetingCuentaId_Id(Long meetingCuentaId);
}
