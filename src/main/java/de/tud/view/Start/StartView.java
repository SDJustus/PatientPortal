package de.tud.view.Start;


import com.vaadin.data.provider.Query;
import com.vaadin.event.FieldEvents;
import com.vaadin.navigator.View;
import com.vaadin.server.Page;
import com.vaadin.ui.*;
import com.vaadin.ui.renderers.LocalDateTimeRenderer;
import de.tud.controller.StartViewController;
import de.tud.model.Homework;



public class StartView  extends com.vaadin.ui.Composite implements View {
    private VerticalLayout verticalLayoutMain;
    private Label  todayLabel;
    private Label fulfilledLabel;
    private Label nextDaysLabel;
    private Grid<Homework> todayGrid;
    private Grid<Homework> fulfilledGrid;
    int width = 600;


    private StartViewController startViewController;


    public StartView(){
        UI.getCurrent().getPage().getStyles().add("#header-label{font-weight: bold; font-size:40px;}" +
                ".v-grid-cell.green{color: green;}"+
                ".v-grid-cell.red{color: red;}");

        verticalLayoutMain = new VerticalLayout();
        todayLabel  = new Label("Aufgaben Heute:");
        todayLabel.setId("header-label");
        nextDaysLabel = new Label("Kommende Aufgaben:");
        nextDaysLabel.setId("header-label");

        todayGrid = new Grid<>();

        todayGrid.addColumn(Homework::getName).setCaption("Name").setResizable(false);
        todayGrid.addColumn(Homework::getDescription).setCaption("Beschreibung").setResizable(false);
        todayGrid.addComponentColumn(s -> {CheckBox checkBox = new CheckBox();
        checkBox.setValue(s.isStatus());
            checkBox.addFocusListener(new FieldEvents.FocusListener() {
                @Override
                public void focus(FieldEvents.FocusEvent focusEvent) {
                    startViewController.checkBoxListener(s.getId(),checkBox);
                }
            });

        return checkBox; })
                .setCaption("").setResizable(false).setMinimumWidth(100).setMaximumWidth(100);


        todayGrid.setFrozenColumnCount(todayGrid.getColumns().size());
        todayGrid.setSelectionMode(Grid.SelectionMode.NONE).setUserSelectionAllowed(false);

        todayGrid.setWidth("" + (Integer.valueOf(Page.getCurrent().getBrowserWindowWidth()) - Integer.valueOf(width)));

        todayGrid.setHeightByRows(todayGrid.getDataProvider().size(new Query<>())+1);


        UI.getCurrent().getPage().addBrowserWindowResizeListener(e -> {

            todayGrid.setWidth("" + (e.getWidth() - Integer.valueOf(width)));

        });

        fulfilledLabel = new Label("Heute erfüllte Aufgaben:");
        fulfilledLabel.setId("header-label");

        fulfilledGrid = new Grid<>();
        //fulfilledGrid.setEnabled(false);
        fulfilledGrid.setSelectionMode(Grid.SelectionMode.NONE).setUserSelectionAllowed(false);

        fulfilledGrid.addColumn(Homework::getName).setCaption("Name").setResizable(false);
        fulfilledGrid.addColumn(Homework::getDescription).setCaption("Beschreibung").setResizable(false);
        fulfilledGrid.addComponentColumn(s -> {CheckBox checkBox = new CheckBox();
            checkBox.setValue(true);
            checkBox.addFocusListener(new FieldEvents.FocusListener() {
                @Override
                public void focus(FieldEvents.FocusEvent focusEvent) {
                    startViewController.checkBoxListener(s.getId(),checkBox);
                }
            });
            return  checkBox; })
                .setCaption("").setResizable(false).setMinimumWidth(100).setMaximumWidth(100);

        fulfilledGrid.setFrozenColumnCount(fulfilledGrid.getColumns().size());


        fulfilledGrid.setHeightByRows(fulfilledGrid.getDataProvider().size(new Query<>())+1);
        fulfilledGrid.setWidth("" + (Integer.valueOf(Page.getCurrent().getBrowserWindowWidth()) - Integer.valueOf(width)));

        UI.getCurrent().getPage().addBrowserWindowResizeListener(e -> {
            fulfilledGrid.setWidth("" + (e.getWidth() - Integer.valueOf(width)));

        });



        verticalLayoutMain.addComponents(todayLabel, todayGrid, fulfilledLabel, fulfilledGrid);
        verticalLayoutMain.setSpacing(true);
        verticalLayoutMain.setMargin(true);


        this.startViewController = new StartViewController(this);

        setCompositionRoot(verticalLayoutMain);
    }


    public Grid<Homework> getFulfilledGrid(){return fulfilledGrid;}
    public Grid<Homework> getTodayGrid() {
        return todayGrid;
    }

}
