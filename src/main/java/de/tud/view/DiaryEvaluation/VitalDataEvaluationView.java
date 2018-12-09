package de.tud.view.DiaryEvaluation;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Page;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.ui.*;
import com.vaadin.ui.renderers.NumberRenderer;
import com.vaadin.ui.renderers.Renderer;
import de.tud.model.VitalData;


public class VitalDataEvaluationView {
    Grid<VitalDataTable> grid;
    VerticalLayout tableContainer = new VerticalLayout();
    DateTimeField fromDate = new DateTimeField();
    DateTimeField toDate = new DateTimeField();
    HorizontalLayout filterBar = new HorizontalLayout();
    Button resetButton = new Button(VaadinIcons.ARROW_BACKWARD);
    int height = 170;
    int width = 300;


    public VitalDataEvaluationView(){
        this.grid = new Grid<>();
    }

    public VerticalLayout getViewComponent() {
        //datePicker
        fromDate.setPlaceholder("von...");
        toDate.setPlaceholder("bis...");


        //Spalte Datum

        grid.addColumn(VitalDataTable::getDate).setId("Datum");
        grid.getColumn("Datum").setCaption("Datum");
        grid.getColumn("Datum").setResizable(false);
        grid.sort("Datum", SortDirection.DESCENDING);

        //Blutdruck erster Wert
        grid.addColumn(VitalDataTable::getBloodPressureFirstValue).setId("RR_sys");
        grid.getColumn("RR_sys").setCaption("Blutdruck \n (systolisch)");
        grid.getColumn("RR_sys").setResizable(false);


        //Blutdruck zweiter Wert
        grid.addColumn(VitalDataTable::getBloodPressureSecondValue).setId("RR_dia");
        grid.getColumn("RR_dia").setCaption("Blutdruck \n (diastolisch)");

        //Heart Rate
        grid.addColumn(VitalDataTable::getHeartRate).setId("Puls");
        grid.getColumn("Puls").setCaption("Puls");
        grid.getColumn("Puls").setResizable(false);

        //BMI
        grid.addColumn(VitalDataTable::getBMI).setId("BMI");
        grid.getColumn("BMI").setCaption("BMI");
        grid.getColumn("BMI").setResizable(false);


        //Gewicht
        grid.addColumn(VitalDataTable::getWeight).setId("Gewicht");
        grid.getColumn("Gewicht").setCaption("Gewicht");
        grid.getColumn("Gewicht").setResizable(false);

        //Körpergröße
        grid.addColumn(VitalDataTable::getHeight).setId("size");
        grid.getColumn("size").setCaption("Größe");
        grid.getColumn("size").setResizable(false);


        grid.setFrozenColumnCount(grid.getColumns().size());

        //Table size
        grid.setHeight("" + (Integer.valueOf(Page.getCurrent().getBrowserWindowHeight()) - Integer.valueOf(height)));
        grid.setWidth("" + (Integer.valueOf(Page.getCurrent().getBrowserWindowWidth()) - Integer.valueOf(width)));

        UI.getCurrent().getPage().addBrowserWindowResizeListener(e -> {
            grid.setHeight("" + (e.getHeight() - Integer.valueOf(height)));
            grid.setWidth("" + (e.getWidth() - Integer.valueOf(width)));

        });

        filterBar.addComponents(fromDate, toDate, resetButton);
        filterBar.setResponsive(true);
        tableContainer.addComponents(filterBar, grid);
        tableContainer.setMargin(false);

        return tableContainer;
    }

    public Grid<VitalDataTable> getGrid() {
        return grid;
    }

    public DateTimeField getFromDate() {
        return fromDate;
    }

    public DateTimeField getToDate() {
        return toDate;
    }

    public Button getResetButton() {
        return resetButton;
    }

    public class VitalDataTable extends VitalData {
        String dateTime;

        public VitalDataTable(String dateTime, float height, float weight, int bloodPressureFirstValue,
                              int bloodPressureSecondValue, int heartRate) {
            super(height, weight, (short) bloodPressureFirstValue, (short) bloodPressureSecondValue, (short) heartRate);
            this.dateTime = dateTime;
        }

        public String getDate() {
            return dateTime;
        }
    }



}
