package de.vilkas.scolee.view;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.PushStateNavigation;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI
@PushStateNavigation
public class MainView extends UI {

    private Navigator navigator;

    @Autowired
    SpringViewProvider viewProvider;

    @Override
    protected void init(final VaadinRequest vaadinRequest) {
        MenuBar menu = createMenuBar();
        HorizontalLayout content = createMainLayout(menu);
        initNavigator(content);
    }

    /**
     * Initialises Navigator
     * @param content Container to load the Views from navigator
     */
    private void initNavigator(final HorizontalLayout content) {
        navigator = new Navigator(this, content);
        navigator.addProvider(viewProvider);
        navigator.addView("", viewProvider.getView(CalendarView.VIEW_NAME));
        setNavigator(navigator);
    }

    private HorizontalLayout createMainLayout(final MenuBar menu) {
        HorizontalLayout content = new HorizontalLayout();
        VerticalLayout mainLayout = new VerticalLayout(menu, content);
        mainLayout.setComponentAlignment(menu, Alignment.TOP_CENTER);
        setContent(mainLayout);
        return content;
    }

    private MenuBar createMenuBar() {
        MenuBar menu = new MenuBar();
        menu.setWidth(100.0f, Unit.PERCENTAGE);
        menu.addItem("Kalender", navitageToView(CalendarView.VIEW_NAME));
        menu.addItem("Adressen", navitageToView(Contacts.VIEW_NAME));
        return menu;
    }

    private MenuBar.Command navitageToView(final String viewName) {
        return e -> navigator.navigateTo(viewName);
    }
}
