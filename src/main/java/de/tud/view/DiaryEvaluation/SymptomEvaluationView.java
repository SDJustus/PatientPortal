package de.tud.view.DiaryEvaluation;


import com.vaadin.data.provider.GridSortOrder;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.server.Page;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.ui.*;
import com.vaadin.ui.Button;
import com.vaadin.ui.renderers.LocalDateRenderer;
import com.vaadin.ui.renderers.LocalDateTimeRenderer;
import com.vaadin.ui.renderers.TextRenderer;
import de.tud.model.symptom.Symptom;

import javax.swing.text.DateFormatter;
import java.awt.*;
import java.text.DateFormat;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class SymptomEvaluationView extends EvaluationView{


    public SymptomEvaluationView(){

    }

    public VerticalLayout getViewComponent() {

        //datePicker
        fromDate.setPlaceholder("von...");
        toDate.setPlaceholder("bis...");

        //Symptom filterCombo Box
        filterComboBox.setPlaceholder("Symptome");
        filterComboBox.setWidth("250px");

        //Spalte Datum


        grid.addColumn(DiaryEvaluationUIModel::getDate, new LocalDateRenderer("dd.MM.yyyy")).setId("Datum");
        grid.getColumn("Datum").setCaption("Datum");
        grid.getColumn("Datum").setResizable(false);

        grid.setHierarchyColumn("Datum");

        grid.addColumn(DiaryEvaluationUIModel::getClock, new LocalDateTimeRenderer("HH:mm")).setId("Uhrzeit");
        grid.getColumn("Uhrzeit").setCaption("Uhrzeit");
        grid.getColumn("Uhrzeit").setResizable(false);
        grid.getColumn("Uhrzeit").setMaximumWidth(100);


        //Spalte Symptome
        grid.addColumn(DiaryEvaluationUIModel::getSymptom).setId("Ausprägung der Symptome");
        grid.getColumn("Ausprägung der Symptome").setCaption("Ausprägung der Symptome");
        //grid.sort("Datum", SortDirection.DESCENDING);

        grid.setSortOrder(GridSortOrder.desc(grid.getColumn("Datum"))
                .thenDesc(grid.getColumn("Uhrzeit")));

        grid.setSelectionMode(Grid.SelectionMode.NONE);

        grid.setFrozenColumnCount(grid.getColumns().size());


        //Table size
        grid.setHeight("" + (Integer.valueOf(Page.getCurrent().getBrowserWindowHeight()) - Integer.valueOf(height)));
        grid.setWidth("" + (Integer.valueOf(Page.getCurrent().getBrowserWindowWidth()) - Integer.valueOf(width)));

        UI.getCurrent().getPage().addBrowserWindowResizeListener(e -> {
            grid.setHeight("" + (e.getHeight() - Integer.valueOf(height)));
            grid.setWidth("" + (e.getWidth() - Integer.valueOf(width)));

        });

        filterBar.addComponents(fromDate, toDate, filterComboBox, resetButton);
        filterBar.setResponsive(true);
        tableContainer.addComponents(filterBar, grid);
        tableContainer.setMargin(false);

        return tableContainer;
    }




}
