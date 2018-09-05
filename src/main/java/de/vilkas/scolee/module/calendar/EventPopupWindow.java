package de.vilkas.scolee.module.calendar;

import com.vaadin.data.Binder;
import com.vaadin.ui.*;

public class EventPopupWindow extends Window {


    private final MeetingDataProvider eventProvider;
    private EventData eventData;
    private Binder<EventData> binder;
    private DateField start;
    private DateField end;
    private HorizontalLayout dates;
    private TextField name;
    private TextArea details;
    private Button cancelBtn;
    private Button saveBtn;
    private HorizontalLayout buttons;
    private VerticalLayout mainLayout;

    public EventPopupWindow(final MeetingDataProvider eventProvider, final EventData eventData) {
        this.eventProvider = eventProvider;
        this.eventData = eventData;
        binder = new Binder<>();

        initUI();
        formatUI();

        setContent(mainLayout);
        binder.setBean(this.eventData);
    }

    private void formatUI() {
        setModal(true);
        mainLayout.setComponentAlignment(buttons, Alignment.BOTTOM_RIGHT);
        mainLayout.setMargin(true);
        dates.setWidth(100, Unit.PERCENTAGE);
        name.setWidth(100, Unit.PERCENTAGE);
        details.setWidth(100, Unit.PERCENTAGE);
    }

    private void initUI() {
        initDates();
        initTopic();
        initDetails();
        initButtons();
        mainLayout = new VerticalLayout(dates, name, details, buttons);
    }

    private void initButtons() {
        cancelBtn = new Button("Abbrechen", e -> close());
        saveBtn = new Button("Speichern", e -> handleSave());
        buttons = new HorizontalLayout(cancelBtn, saveBtn);
    }

    private void initDetails() {
        details = new TextArea("Beschreibung");
        binder.forField(details).bind(EventData::getDetails, EventData::setDetails);
    }

    private void initTopic() {
        name = new TextField("Thema");
        binder.forField(name).bind(EventData::getName, EventData::setName);
    }

    private void initDates() {
        initStart();
        initEnd();
        dates = new HorizontalLayout(start, end);
    }

    private void initEnd() {
        end = new DateField("Bis:", eventData.getEnd().toLocalDate());
        binder.forField(end).bind(EventData::getEndAsLocalDate, EventData::setEndAsLocalDate);
    }

    private void initStart() {
        start = new DateField("Von:", eventData.getStart().toLocalDate());
        binder.forField(start).bind(EventData::getStartAsLocalDate, EventData::setStartAsLocalDate);
    }

    private void handleSave() {
        eventProvider.saveEventItem(new EventItem(eventData));
        close();
    }
}
