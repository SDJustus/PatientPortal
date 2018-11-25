package de.tud.controller;


import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.TreeData;
import com.vaadin.data.provider.TreeDataProvider;
import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.event.selection.SelectionListener;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.*;
import com.vaadin.ui.*;
import com.vaadin.navigator.Navigator;
import com.vaadin.ui.themes.ValoTheme;
import de.tud.view.*;

import javax.servlet.annotation.WebServlet;


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
        title.addStyleName(ValoTheme.MENU_TITLE);

        //Profilbild
        Image profilbild = new Image("", new ClassResource("/profilbild.png"));
        profilbild.setHeight("120px");
        profilbild.setWidth("120px");
        profilbild.setId("profilbild");


        //Button zum Patiententagebuch
        Button view1 = new Button("Patiententagebuch",
                e -> getNavigator().navigateTo("Patiententagebuch"));
        view1.setIcon(VaadinIcons.TASKS);
        view1.addStyleNames( ValoTheme.BUTTON_LINK, ValoTheme.MENU_ITEM);

        //Button z.B. zum Medikationsplan
        Button view2 = new Button("Auswertung", e -> getNavigator().navigateTo("Auswertung"));
        view2.addStyleNames(ValoTheme.BUTTON_LINK, ValoTheme.MENU_ITEM);
        view2.setIcon(VaadinIcons.CHART, ValoTheme.MENU_PART_LARGE_ICONS);

        //Menu tree
        Tree<String> menuTree = new Tree<>();

        TreeData<String> menuTreeData = new TreeData<>();

        //add tree items with hierachy
        menuTreeData.addItem(null,"Patiententagebuch");
        menuTreeData.addItem("Patiententagebuch","Eintrag hinzufügen");
        menuTreeData.addItem("Patiententagebuch","Vitaldaten einfügen");
        menuTreeData.addItem("Patiententagebuch", "Auswertung");

        //add data to tree
        TreeDataProvider inMemoryDataProvider = new TreeDataProvider<>(menuTreeData);
        menuTree.setDataProvider(inMemoryDataProvider);



        //Integration der MenuItems
        CssLayout menu = new CssLayout(title, profilbild,view1, view2,menuTree);
        //CssLayout menu = new CssLayout(title,view1, view2);
        menu.addStyleName(ValoTheme.MENU_ROOT);

        //view Container = alles was rechts vom Menu ist, wo Inhalte angezeigt werden
        CssLayout viewContainer = new CssLayout();


        HorizontalLayout mainLayout = new HorizontalLayout(menu, viewContainer);
        mainLayout.setSizeFull();

        setContent(mainLayout);
        mainLayout.setExpandRatio(viewContainer, 1.0f);

        //Navigation im Menü, um auf Klick Views anzeigen zu lassen
        Navigator navigator = new Navigator(this, viewContainer);
        navigator.addView("", DiaryView.class);
        navigator.addView("Patiententagebuch", DiaryView.class);
        navigator.addView("Auswertung", DiaryEvaluationView.class);


        //add functions to tree items -> listen for selection change then navigate

        menuTree.addSelectionListener(new SelectionListener<String>() {
            @Override
            public void selectionChange(SelectionEvent<String> selectionEvent) {

                System.out.println(menuTree.getSelectedItems().toString());
                switch (menuTree.getSelectedItems().toString())
                {

                    case "[Vitaldaten einfügen]":
                    {
                        navigator.navigateTo("");
                        System.out.println(menuTree.getSelectedItems().toString());
                    }
                    case "[Eintrag hinzufügen]":
                    {
                        navigator.navigateTo("Patiententagebuch");
                        System.out.println(menuTree.getSelectedItems().toString());
                    }
                    case "[Auswertung]":
                    {
                        navigator.navigateTo("Auswertung");
                    }

                }
            }
        });

    }
    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = PatientPortalMenu.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
