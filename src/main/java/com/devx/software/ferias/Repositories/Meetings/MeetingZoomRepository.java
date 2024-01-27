package com.devx.software.ferias.Repositories.Meetings;

import com.devx.software.ferias.Entities.Meetings.MeetingZoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingZoomRepository extends JpaRepository<MeetingZoomEntity, Long> {
}
