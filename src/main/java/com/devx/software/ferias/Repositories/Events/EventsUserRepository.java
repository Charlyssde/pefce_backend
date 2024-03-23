package com.devx.software.ferias.Repositories.Events;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.devx.software.ferias.Entities.Events.EventUsersEntity;

@Repository
public interface EventsUserRepository extends JpaRepository<EventUsersEntity, Long> {
    
}
