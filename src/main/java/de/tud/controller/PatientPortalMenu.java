package de.tud.controller;


import com.github.appreciated.material.MaterialTheme;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.server.*;
import com.vaadin.ui.*;
import com.vaadin.navigator.Navigator;
import com.vaadin.ui.themes.ValoTheme;
import de.tud.model.Diary;
import de.tud.model.manager.DiaryManager;
import de.tud.view.*;

import javax.servlet.annotation.WebServlet;

@Theme("mytheme")
public class PatientPortalMenu extends UI {
    @Override
    public void init(VaadinRequest request) {
        //CSS Befehle
        UI.getCurrent().getPage().getStyles().add(".v-button{text-align: left !important;}" +
                ".v-label{font-size: large !important; }"+
                "#smileybild:hover{transform: scale(1.2);}"+
                "#smileybild:{transition: transform .2s;}"+
                "#profilbild{display: block; margin: 0 auto;}");


        //Titel des Menüs
        Label title = new Label("Patientenportal");
        title.addStyleName(MaterialTheme.MENU_TITLE);

        //Profilbild
        Image profilbild = new Image("", new ClassResource("/profilbild.png"));
        profilbild.setHeight("120px");
        profilbild.setWidth("120px");
        profilbild.setId("profilbild");


        //Button zum Patiententagebuch
        Button view1 = new Button("Patiententagebuch",
                e -> getNavigator().navigateTo("Patiententagebuch"));
        view1.setIcon(VaadinIcons.TASKS);
        view1.addStyleNames(MaterialTheme.BUTTON_LINK, MaterialTheme.MENU_ITEM);

        //Button z.B. zum Medikationsplan
        Button view2 = new Button("Medikationsplan", e -> getNavigator().navigateTo("view2"));
        view2.addStyleNames(MaterialTheme.BUTTON_LINK, MaterialTheme.MENU_ITEM);
        view2.setIcon(VaadinIcons.AMBULANCE, ValoTheme.MENU_PART_LARGE_ICONS);
        view2.setIcon(VaadinIcons.DOCTOR, ValoTheme.MENU_PART_LARGE_ICONS);

        //Button Vitaldaten

        Button view3 = new Button("Vitaldaten",
                e -> getNavigator().navigateTo("VitalData"));
        view1.setIcon(VaadinIcons.TASKS);
        view1.addStyleNames( MaterialTheme.BUTTON_LINK, MaterialTheme.MENU_ITEM);


        //Integration der MenuItems
        CssLayout menu = new CssLayout(title, profilbild,view1, view2, view3);
        //CssLayout menu = new CssLayout(title,view1, view2);
        menu.addStyleName(MaterialTheme.MENU_ROOT);

        //view Container = alles was rechts vom Menu ist, wo Inhalte angezeigt werden
        CssLayout viewContainer = new CssLayout();


        HorizontalLayout mainLayout = new HorizontalLayout(menu, viewContainer);
        mainLayout.setSizeFull();

        setContent(mainLayout);
        mainLayout.setExpandRatio(viewContainer, 1.0f);

        //Navigation im Menü, um auf Klick Views anzeigen zu lassen
        Navigator navigator = new Navigator(this, viewContainer);
        navigator.addView("", DefaultView.class);
        navigator.addView("Patiententagebuch", TagebuchView.class);
        navigator.addView("VitalData", VitalDataView.class);


    }
    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = PatientPortalMenu.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
