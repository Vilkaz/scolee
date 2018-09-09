package de.vilkas.scolee.module.calendar;

import org.vaadin.addon.calendar.Calendar;

public class ScoleeCalendar extends Calendar<EventItem> {
    public ScoleeCalendar(final MeetingDataProvider meetingDataProvider) {
        super(meetingDataProvider);
    }

    /**
     * To Disable the DayView on Click
     */
    @Override
    protected void setDefaultHandlers() {
//        setHandler(new BasicBackwardHandler());
//        setHandler(new BasicForwardHandler());
//        setHandler(new BasicWeekClickHandler());
//        setHandler(new BasicDateClickHandler());
//        setHandler(new BasicItemMoveHandler());
//        setHandler(new BasicItemResizeHandler());
    }


}
