package de.vilkas.scolee.module.calendar;

import org.vaadin.addon.calendar.item.BasicItem;

import java.time.ZonedDateTime;

/**
 * EventData Pojo
 */

public class EventItem extends BasicItem {

    private final EventData eventData;

    /**
     * constructor
     *
     * @param eventData A eventData
     */

    public EventItem(EventData eventData) {
        super(eventData.getDetails(), null, eventData.getStart(), eventData.getEnd());
        this.eventData = eventData;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EventItem)) {
            return false;
        }
        EventItem that = (EventItem) o;
        return getEventData().equals(that.getEventData());
    }

    public EventData getEventData() {
        return eventData;
    }

    @Override
    public String getStyleName() {
        return "state-" + eventData.getState().name().toLowerCase();
    }

    @Override
    public int hashCode() {
        return getEventData().hashCode();
    }

    @Override
    public boolean isAllDay() {
        //An Hack to hide the time ...
        return true;
    }

    @Override
    public boolean isMoveable() {
        return eventData.isEditable();
    }

    @Override
    public boolean isResizeable() {
        return eventData.isEditable();
    }

//    @Override
//    public boolean isClickable() {
//        return eventData.isEditable();
//    }

    @Override
    public void setEnd(ZonedDateTime end) {
        eventData.setEnd(end);
        super.setEnd(end);
    }

    @Override
    public void setStart(ZonedDateTime start) {
        eventData.setStart(start);
        super.setStart(start);
    }

    @Override
    public String getCaption() {
        return eventData.getName();
    }

    @Override
    public String getDateCaptionFormat() {
        return "";
    }

}