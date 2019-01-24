package de.tud.view.diaryEvaluation;

import com.vaadin.server.Page;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.ui.*;
import com.vaadin.ui.renderers.LocalDateRenderer;


public class VitalDataEvaluationView extends EvaluationView {


    public VitalDataEvaluationView(){

    }

    public VerticalLayout getViewComponent() {
        //datePicker
        fromDate.setPlaceholder("von...");
        toDate.setPlaceholder("bis...");


        //Spalte Datum

        grid.addColumn(DiaryEvaluationUIModel::getDate, new LocalDateRenderer("dd.MM.yyyy")).setId("Datum");
        grid.getColumn("Datum").setCaption("Datum");
        grid.getColumn("Datum").setResizable(false);
        grid.sort("Datum", SortDirection.DESCENDING);

        //Blutdruck erster Wert
        grid.addColumn(DiaryEvaluationUIModel::getBloodPressureFirstValue).setId("RR_sys");
        grid.getColumn("RR_sys").setCaption("Blutdruck (syst.) mmHg)");
        grid.getColumn("RR_sys").setResizable(false);


        //Blutdruck zweiter Wert
        grid.addColumn(DiaryEvaluationUIModel::getBloodPressureSecondValue).setId("RR_dia");
        grid.getColumn("RR_dia").setCaption("Blutdruck (diast.) mmHg)");

        //Heart Rate
        grid.addColumn(DiaryEvaluationUIModel::getHeartRate).setId("Puls");
        grid.getColumn("Puls").setCaption("Puls (BPM)");
        grid.getColumn("Puls").setResizable(false);

        //BMI
        grid.addColumn(DiaryEvaluationUIModel::getBMI).setId("BMI");
        grid.getColumn("BMI").setCaption("BMI");
        grid.getColumn("BMI").setResizable(false);


        //Gewicht
        grid.addColumn(DiaryEvaluationUIModel::getWeight).setId("Gewicht");
        grid.getColumn("Gewicht").setCaption("Gewicht in kg");
        grid.getColumn("Gewicht").setResizable(false);



        //Körpergröße
        grid.addColumn(DiaryEvaluationUIModel::getHeight).setId("size");
        grid.getColumn("size").setCaption("Größe in cm");
        grid.getColumn("size").setResizable(false);


        grid.setFrozenColumnCount(grid.getColumns().size());
        grid.setSelectionMode(Grid.SelectionMode.NONE);

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




}
