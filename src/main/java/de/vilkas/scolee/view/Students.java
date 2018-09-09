package de.vilkas.scolee.view;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import de.vilkas.scolee.util.CustomStyles;

@SpringView(name = Students.VIEW_NAME)
public class Students extends VerticalLayout implements View {

    public static final String VIEW_NAME = "students";

    public Students() {
        final Accordion accordion = new Accordion();
        accordion.setHeight(100.0f, Unit.PERCENTAGE);

        for (int i = 1; i < 8; i++) {
            final Label name = new Label("Schüller nr " + i);
            final VaadinIcons picture = VaadinIcons.PICTURE;
            Button pictureBtn = new Button("KindNAme", picture);
            pictureBtn.addStyleName(CustomStyles.ICON_ONLY_BTN.getStyleName());
            HorizontalLayout layout = new HorizontalLayout(pictureBtn, name);
            layout.setWidth(100.0f, Unit.PERCENTAGE);

            layout.setMargin(true);

            accordion.addTab(layout, "Kind " + i);
        }
        accordion.setWidth(90, Unit.PERCENTAGE);
        addComponents(new Label("Adressen"), accordion);
    }
}
