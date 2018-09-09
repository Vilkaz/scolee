package de.vilkas.scolee.module.calendar;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;

import java.time.format.DateTimeFormatter;

public class EventInfo extends Window {

    private DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public EventInfo(EventData eventData, Runnable editAction) {
        Label name = new Label(eventData.getName());
        Label details = new Label(eventData.getDetails(), ContentMode.PREFORMATTED);
        Label date = createDate(eventData);
        HorizontalLayout buttons = createButtons(editAction);
        final VerticalLayout mainLayout = new VerticalLayout(name, date, details, buttons);
        mainLayout.setComponentAlignment(buttons, Alignment.BOTTOM_RIGHT);
        setModal(true);
        setContent(mainLayout);
    }

    private Label createDate(final EventData eventData) {
        Label date;
        if (startsAndEndsAtSameDay(eventData)) {
            date = new Label("am: " + eventData.getStart().format(formatter));
        } else {
            date = new Label("von: " + eventData.getStart().format(formatter) +
                    " bis: " + eventData.getEnd().format(formatter));
        }
        return date;
    }

    private boolean startsAndEndsAtSameDay(final EventData eventData) {
        return eventData.getStart().toLocalDate().equals(eventData.getEnd().toLocalDate());
    }

    private HorizontalLayout createButtons(Runnable editAction) {
        final Button close = new Button("Schliessen", VaadinIcons.CLOSE);
        close.addClickListener(e -> close());
        final Button edit = new Button("Bearbeiten, ", VaadinIcons.EDIT);
        edit.addClickListener(e -> handleEdit(editAction));
        final HorizontalLayout buttons = new HorizontalLayout(edit, close);
        buttons.setComponentAlignment(close, Alignment.BOTTOM_RIGHT);
        return buttons;
    }

    private void handleEdit(Runnable editAction) {
        close();
        editAction.run();
    }
}
