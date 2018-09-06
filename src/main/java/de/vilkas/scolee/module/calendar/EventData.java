package de.vilkas.scolee.module.calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * @author guettler
 * @since 29.06.17
 */
@Entity
public class EventData {

    enum State {
        empty,
        planned,
        confirmed
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private ZonedDateTime start;

    private ZonedDateTime end;

    private String name;

    private String details;

    private State state = State.empty;

    private boolean longTime;

    public EventData() {
    }

    public EventData(boolean longTime) {
        this.longTime = longTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public ZonedDateTime getStart() {
        return start;
    }

    public void setStart(ZonedDateTime start) {
        this.start = start;
    }

    public ZonedDateTime getEnd() {
        return end;
    }

    public void setEnd(ZonedDateTime end) {
        this.end = end;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public boolean isEditable() {
        return state != State.confirmed;
    }

    public boolean isLongTimeEvent() {
        return longTime;
    }

    public void setLongTime(final boolean longTime) {
        this.longTime = longTime;
    }

    public LocalDate getStartAsLocalDate() {
        return start == null ? null : start.toLocalDate();
    }

    public void setStartAsLocalDate(LocalDate date) {
        start = ZonedDateTime.of(date, LocalTime.NOON, ZoneId.systemDefault());
    }

    public LocalDate getEndAsLocalDate() {
        return end == null ? null : end.toLocalDate();
    }

    public void setEndAsLocalDate(LocalDate date) {
        end = ZonedDateTime.of(date, LocalTime.MIDNIGHT, ZoneId.systemDefault());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventData eventData = (EventData) o;
        return Objects.equals(id, eventData.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
