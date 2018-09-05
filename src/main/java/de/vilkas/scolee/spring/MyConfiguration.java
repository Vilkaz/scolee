package de.vilkas.scolee.spring;

import de.vilkas.scolee.db.repository.EventDataRepository;
import de.vilkas.scolee.db.service.EventDataService;
import de.vilkas.scolee.db.service.EventDataServiceImpl;
import de.vilkas.scolee.module.calendar.MeetingDataProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EventDataRepository eventDataRepository;

    @Bean
    public EventDataService eventDataService() {
        return new EventDataServiceImpl(eventDataRepository);
    }

    @Bean
    public MeetingDataProvider meetingDataProvider() {
        return new MeetingDataProvider(eventDataService());
    }

}
