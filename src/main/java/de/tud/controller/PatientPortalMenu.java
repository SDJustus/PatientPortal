package de.tud.controller;


import com.github.appreciated.material.MaterialTheme;
import com.vaadin.annotations.Theme;
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
import de.tud.view.VitalData.VitalDataUIDesignerUISetup;
import de.tud.view.VitalData.VitalDataView;
import de.tud.view.Welfare.WelfareView;

import javax.servlet.annotation.WebServlet;
import java.util.Set;

@Theme("mytheme")
public class PatientPortalMenu extends UI {

    Tree<String> menuTree;
    TreeData<String> menuTreeData;
    Navigator navigator;

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
        view1.addStyleNames( MaterialTheme.BUTTON_LINK, MaterialTheme.MENU_ITEM);
        view1.addStyleName(MaterialTheme.MENU_ITEM);

        //Button z.B. zum Medikationsplan
        Button view2 = new Button("Auswertung", e -> getNavigator().navigateTo("Auswertung"));
        view2.addStyleNames(MaterialTheme.BUTTON_LINK, MaterialTheme.MENU_ITEM);
        view2.setIcon(VaadinIcons.CHART, MaterialTheme.MENU_PART_LARGE_ICONS);


        //Menu tree
         menuTree = new Tree<>();
         menuTreeData = new TreeData<>();

        //add tree items with hierachy
        menuTreeData.addItem(null,"Patiententagebuch");
        menuTreeData.addItem("Patiententagebuch","Symptomeintrag");
        menuTreeData.addItem("Patiententagebuch","Vitaldateneintrag");
        menuTreeData.addItem("Patiententagebuch", "Auswertung");
        menuTreeData.addItem("Patiententagebuch", "Wohlbefinden");


        //add data to tree
        TreeDataProvider inMemoryDataProvider = new TreeDataProvider<>(menuTreeData);
        menuTree.setDataProvider(inMemoryDataProvider);
        menuTree.setSelectionMode(Grid.SelectionMode.SINGLE);



        //design settings
        view1.addStyleName(MaterialTheme.BUTTON_FLAT + MaterialTheme.BUTTON_BORDER);
        view2.addStyleName(MaterialTheme.BUTTON_FLAT +MaterialTheme.BUTTON_BORDER);




        //add functions to tree items -> listen for selection change then navigate
        menuTree.addItemClickListener(event -> {
            {


                String selectedItem = event.getItem();



                System.out.println(selectedItem);
                switch (selectedItem) {

                    case "Vitaldateneintrag": {
                        menuTree.select(event.getItem());
                        getNavigator().navigateTo("Vitaldateneintrag");

                        break;
                    }
                    case "Symptomeintrag": {
                        menuTree.select(event.getItem());
                        this.getNavigator().navigateTo("Patiententagebuch");
                        System.out.println(selectedItem);
                        break;
                    }
                    case "Auswertung": {
                        menuTree.select(event.getItem());
                        getNavigator().navigateTo("Auswertung");
                        System.out.println(selectedItem);
                        break;
                    }
                    case "Wohlbefinden": {
                        menuTree.select(event.getItem());
                        getNavigator().navigateTo("Wohlbefinden");
                        System.out.println(selectedItem);

                        break;
                    }


                }



            }
        });

        //Integration der MenuItems
        CssLayout menu = new CssLayout(title, profilbild,view1, view2,menuTree);

        //Styles hinzufügen
        menu.addStyleName(MaterialTheme.MENU_ROOT);
        menu.addStyleName(MaterialTheme.LAYOUT_COMPONENT_GROUP_MATERIAL);
        menu.addStyleName("menubackground");
        menuTree.setPrimaryStyleName("v-tree8");
        menuTree.addStyleName("colourTree");
        menuTree.addStyleName("v-tree8");


        //  menuTree.addStyleName(MaterialTheme.BUTTON_FLAT);
        //menu.addStyleName(MaterialTheme.TEXTFIELD_BORDERLESS);
        //CssLayout menu = new CssLayout(title, profilbild, menuTree);




        //view Container = alles was rechts vom Menu ist, wo Inhalte angezeigt werden
        CssLayout viewContainer = new CssLayout();


        HorizontalLayout mainLayout = new HorizontalLayout(menu, viewContainer);
        mainLayout.setSizeFull();

        setContent(mainLayout);
        mainLayout.setExpandRatio(viewContainer, 1.0f);

        //Navigation im Menü, um auf Klick Views anzeigen zu lassen

       navigator = new Navigator(this, viewContainer);

        navigator.addView("", StartView.class);
        navigator.addView("Patiententagebuch", DiaryView.class);
        navigator.addView("Auswertung", DiaryEvaluationView.class);
        navigator.addView("Vitaldateneintrag", VitalDataView.class);
        navigator.addView("Wohlbefinden", WelfareView.class);

        




    }
    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = PatientPortalMenu.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
    @Override
    protected void refresh(VaadinRequest request) {
        super.refresh(request);

    }

}