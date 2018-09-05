package de.vilkas.scolee.module.calendar;

import de.vilkas.scolee.db.service.EventDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vaadin.addon.calendar.item.BasicItemProvider;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public final class MeetingDataProvider extends BasicItemProvider<EventItem> {


    @Autowired
    private EventDataService eventDataService;

    @Autowired
    public MeetingDataProvider(EventDataService eventDataService) {
        this.eventDataService = eventDataService;
        refreshItems();
    }

    private void refreshItems() {
        removeAllEvents();
        final Collection<EventData> eventDatas = eventDataService.findAll();
        List<EventItem> eventItems = eventDatas.stream()
                .map(ed -> new EventItem(ed))
                .collect(Collectors.toList());
        setItems(eventItems);
    }


    void removeAllEvents() {
        this.itemList.clear();
        fireItemSetChanged();
    }

    public void remove(EventData eventData) {
        removeItem(new EventItem(eventData));
        eventDataService.delete(eventData);
    }

    public void save(EventData eventData) {
        eventDataService.save(eventData);
        refreshItems();
    }

    private EventItem getExistingItem(EventItem eventItem) {
        return itemList.stream()
                .filter(e -> e.equals(eventItem))
                .findAny()
                .orElse(null);
    }
}
