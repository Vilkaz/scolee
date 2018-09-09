package de.vilkas.scolee.module.calendar;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;

public class EventInfo extends Window {

    public EventInfo(EventData eventData, Runnable editAction) {
        Label name = new Label(eventData.getName());
        Label details = new Label(eventData.getDetails(), ContentMode.PREFORMATTED);
        HorizontalLayout buttons = createButtons(editAction);
        final VerticalLayout mainLayout = new VerticalLayout(name, details, buttons);
        mainLayout.setComponentAlignment(buttons, Alignment.BOTTOM_RIGHT);
        setModal(true);
        setContent(mainLayout);
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
