package de.vilkas.scolee.db.service;

import de.vilkas.scolee.db.repository.EventDataRepository;
import de.vilkas.scolee.module.calendar.EventData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class EventDataServiceImpl implements EventDataService {

    private EventDataRepository repository;

    public EventDataServiceImpl(EventDataRepository repository) {
        this.repository = repository;
    }


    @Override
    public void save(final EventData eventData) {
        repository.save(eventData);
    }

    @Override
    public EventData findFirstById(final Long id) {
       return repository.findFirstById(id);
    }

    @Override
    public Collection<EventData> findAll() {
        final List<EventData> all = repository.findAll();
        return all == null ? new ArrayList<>() : all;
    }
}
