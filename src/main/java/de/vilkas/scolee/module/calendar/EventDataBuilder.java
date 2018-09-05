package de.vilkas.scolee.module.calendar;

import java.time.ZonedDateTime;

public final class EventDataBuilder {
    private ZonedDateTime start;
    private ZonedDateTime end;
    private String name;
    private String details;
    private EventData.State state = EventData.State.empty;
    private boolean longTime;

    private EventDataBuilder() {
    }

    public static EventDataBuilder anEventData() {
        return new EventDataBuilder();
    }

    public EventDataBuilder withStart(ZonedDateTime start) {
        this.start = start;
        return this;
    }

    public EventDataBuilder withEnd(ZonedDateTime end) {
        this.end = end;
        return this;
    }

    public EventDataBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public EventDataBuilder withDetails(String details) {
        this.details = details;
        return this;
    }

    public EventDataBuilder withState(EventData.State state) {
        this.state = state;
        return this;
    }

    public EventDataBuilder withLongTime(boolean longTime) {
        this.longTime = longTime;
        return this;
    }

    public EventData build() {
        EventData eventData = new EventData(longTime);
        eventData.setStart(start);
        eventData.setEnd(end);
        eventData.setName(name);
        eventData.setDetails(details);
        eventData.setState(state);
        return eventData;
    }
}
