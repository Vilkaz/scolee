package de.vilkas.scolee.view;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.VerticalLayout;
import de.vilkas.scolee.module.calendar.EventCalendar;
import de.vilkas.scolee.module.calendar.MeetingDataProvider;
import org.springframework.beans.factory.annotation.Autowired;

@SpringView(name = CalendarView.VIEW_NAME)
public class CalendarView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "calendar";

    @Autowired
    public CalendarView(MeetingDataProvider meetingDataProvider) {
        final EventCalendar calendar = new EventCalendar(meetingDataProvider);
        calendar.setSizeFull();
        addComponent(calendar);
    }
}
