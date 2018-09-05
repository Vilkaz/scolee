package de.vilkas.scolee.module.calendar;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.addon.calendar.Calendar;
import org.vaadin.addon.calendar.handler.BasicDateClickHandler;
import org.vaadin.addon.calendar.ui.CalendarComponentEvents;

import java.time.Month;
import java.time.ZonedDateTime;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;


/**
 * Calendar Class from Vaadin Directory
 */
public class EventCalendar extends CustomComponent {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final Random R = new Random(0);
    public VerticalLayout panel;

    private MeetingDataProvider meetingDataProvider;

    private Calendar<EventItem> calendar;

    public EventCalendar(MeetingDataProvider meetingDataProvider) {
        this.meetingDataProvider = meetingDataProvider;
        setId("class-Calendar");
        setSizeFull();

        initCalendar();

        VerticalLayout layout = new VerticalLayout();
        layout.setMargin(false);
        layout.setSpacing(false);
        layout.setSizeFull();

        panel = new VerticalLayout(calendar);
        panel.setHeight(100, Unit.PERCENTAGE);
        layout.addComponent(panel);

        setCompositionRoot(layout);

    }

    public void switchToMonth(Month month) {
        calendar.withMonth(month);
    }

    public Calendar<EventItem> getCalendar() {
        return calendar;
    }

    private void onCalendarRangeSelect(CalendarComponentEvents.RangeSelectEvent event) {

        final EventData eventData = EventDataBuilder.anEventData()
                .withStart(event.getStart())
                .withEnd(event.getEnd())
                .build();
        final Window window = new EventPopupWindow(meetingDataProvider, eventData);
        this.getUI().getUI().addWindow(window);
    }

    private void onCalendarClick(CalendarComponentEvents.ItemClickEvent itemClickEvent) {

        EventItem item = (EventItem) itemClickEvent.getCalendarItem();
        final EventData eventData = item.getEventData();

        final Window window = new EventPopupWindow(meetingDataProvider, eventData);
        this.getUI().getUI().addWindow(window);
//        Notification.show(eventData.getName(), eventData.getDetails(), Type.HUMANIZED_MESSAGE);
    }

    private void initCalendar() {

        calendar = new Calendar<>(meetingDataProvider);

        calendar.addStyleName("calendar");
        calendar.setWidth(100.0f, Unit.PERCENTAGE);
        calendar.setHeight(100.0f, Unit.PERCENTAGE);
        calendar.setResponsive(true);

        calendar.setItemCaptionAsHtml(true);
        calendar.setContentMode(ContentMode.HTML);
        calendar.setLocale(Locale.GERMANY);
        calendar.withMonth(ZonedDateTime.now().getMonth());


        addCalendarEventListeners();

        setupBlockedTimeSlots();
    }

    private void setupBlockedTimeSlots() {

        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(java.util.Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the hour of day !
        cal.clear(java.util.Calendar.MINUTE);
        cal.clear(java.util.Calendar.SECOND);
        cal.clear(java.util.Calendar.MILLISECOND);

        GregorianCalendar bcal = new GregorianCalendar(Locale.GERMANY);
        bcal.clear();

        long start = bcal.getTimeInMillis();

        bcal.add(java.util.Calendar.HOUR, 7);
        bcal.add(java.util.Calendar.MINUTE, 30);
        long end = bcal.getTimeInMillis();

        calendar.addTimeBlock(start, end, "my-blocky-style");

        cal.add(java.util.Calendar.DAY_OF_WEEK, 1);

        bcal.clear();
        bcal.add(java.util.Calendar.HOUR, 14);
        bcal.add(java.util.Calendar.MINUTE, 30);
        start = bcal.getTimeInMillis();

        bcal.add(java.util.Calendar.MINUTE, 60);
        end = bcal.getTimeInMillis();

        calendar.addTimeBlock(start, end);

    }

    private void addCalendarEventListeners() {
        calendar.setHandler(new BasicDateClickHandler(true));
        calendar.setHandler(this::onCalendarClick);
        calendar.setHandler(this::onCalendarRangeSelect);
    }

}

