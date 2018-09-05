package de.vilkas.scolee.db.repository;

import de.vilkas.scolee.module.calendar.EventData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventDataRepository extends JpaRepository<EventData, Long> {

    EventData findFirstById(Long id);

}
