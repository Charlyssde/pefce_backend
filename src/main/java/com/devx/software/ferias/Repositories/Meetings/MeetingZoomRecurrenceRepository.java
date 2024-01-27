package com.devx.software.ferias.Repositories.Meetings;

import com.devx.software.ferias.Entities.Meetings.MeetingZoomRecurrenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingZoomRecurrenceRepository extends JpaRepository<MeetingZoomRecurrenceEntity, Long> {
}
