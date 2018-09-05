package de.vilkas.scolee.db.service;

import de.vilkas.scolee.module.calendar.EventData;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface EventDataService {

    void save(EventData eventData);

    void delete(EventData eventData);

    EventData findFirstById(Long id);

    Collection<EventData> findAll();

}
