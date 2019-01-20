package de.tud.view.DiaryEvaluation;

import com.vaadin.navigator.View;
import com.vaadin.server.Page;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.ui.*;
import com.vaadin.ui.renderers.LocalDateRenderer;
import com.vaadin.ui.renderers.LocalDateTimeRenderer;
import de.tud.model.welfare.Welfare;

public class WelfareEvaluationView extends EvaluationView  {


    public WelfareEvaluationView(){

    }

    public Component getViewComponent() {
        //datePicker
        fromDate.setPlaceholder("von...");
        toDate.setPlaceholder("bis...");

        //Symptom filterCombo Box
        filterComboBox.setPlaceholder("Wohlbefinden");
        filterComboBox.setWidth("250px");

        //Spalte Datum
        grid.addColumn(DiaryEvaluationUIModel::getDate, new LocalDateRenderer("dd.MM.yyyy")).setId("Datum");
        grid.getColumn("Datum").setCaption("Datum");
        grid.getColumn("Datum").setResizable(false);
        grid.setHierarchyColumn("Datum");
        grid.setSizeFull();

        grid.addColumn(DiaryEvaluationUIModel::getClock, new LocalDateTimeRenderer("HH:mm")).setId("Uhrzeit");
        grid.getColumn("Uhrzeit").setCaption("Uhrzeit");
        grid.getColumn("Uhrzeit").setResizable(false);
        grid.getColumn("Uhrzeit").setMaximumWidth(100);


        //Spalte Welfare
        grid.addColumn(DiaryEvaluationUIModel::getWelfare).setId("Ausprägung des Wohlbefindens");
        grid.getColumn("Ausprägung des Wohlbefindens").setCaption("Ausprägung des Wohlbefindens");
        grid.getColumn("Ausprägung des Wohlbefindens").setResizable(false);
        grid.getColumn("Ausprägung des Wohlbefindens").setCaption("Ausprägung des Wohlbefindens").
        setStyleGenerator(cellRef -> {
            if(cellRef.getWelfare() == null){
                return null;
            }
            switch (cellRef.getWelfare().getStrength()){
                case SEVERE: return "severe";
                case WEAK: return "weak";
                case MIDDLE: return "middle";
            }
            return null;
        });

        grid.setFrozenColumnCount(grid.getColumns().size());
        grid.sort("Datum", SortDirection.DESCENDING);

        grid.setFrozenColumnCount(grid.getColumns().size());

        //Table size
        grid.setHeight("" + (Integer.valueOf(Page.getCurrent().getBrowserWindowHeight()) - Integer.valueOf(height)));
        grid.setWidth("" + (Integer.valueOf(Page.getCurrent().getBrowserWindowWidth()) - Integer.valueOf(width)));
        UI.getCurrent().getPage().addBrowserWindowResizeListener(e -> {
            grid.setHeight("" + (e.getHeight() - Integer.valueOf(height)));
            grid.setWidth("" + (e.getWidth() - Integer.valueOf(width)));

        });

        grid.setSelectionMode(Grid.SelectionMode.NONE);

        filterBar.addComponents(fromDate,toDate,filterComboBox, resetButton);
        tableContainer.addComponents(filterBar,grid);
        tableContainer.setMargin(false);

        return tableContainer;
    }





}
