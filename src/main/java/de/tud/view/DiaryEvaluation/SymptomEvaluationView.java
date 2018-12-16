package de.tud.view.DiaryEvaluation;


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
    TreeGrid<SymptomEvaluationView.SymptomTable> grid;
    VerticalLayout tableContainer = new VerticalLayout();
    ComboBox<String> filterComboBox = new ComboBox<>();
    DateField fromDate = new DateField();
    DateField toDate = new DateField();
    HorizontalLayout filterBar = new HorizontalLayout();
    Button resetButton = new Button(VaadinIcons.ARROW_BACKWARD);
    int height = 170;
    int width = 300;

    public SymptomEvaluationView(){
        this.grid = new TreeGrid<>();
    }

    public VerticalLayout getViewComponent() {

        //datePicker
        fromDate.setPlaceholder("von...");
        toDate.setPlaceholder("bis...");

        //Symptom filterCombo Box
        filterComboBox.setPlaceholder("Symptome");
        filterComboBox.setWidth("250px");

        //Spalte Datum


        grid.addColumn(SymptomTable::getDate, new LocalDateRenderer("dd.MM.yyyy")).setId("Datum");
        grid.getColumn("Datum").setCaption("Datum");
        grid.getColumn("Datum").setResizable(false);

        grid.setHierarchyColumn("Datum");

        grid.addColumn(SymptomTable::getClock, new LocalDateTimeRenderer("HH:MM")).setId("Uhrzeit");
        grid.getColumn("Uhrzeit").setCaption("Uhrzeit");
        grid.getColumn("Uhrzeit").setResizable(false);
        grid.getColumn("Uhrzeit").setMaximumWidth(100);
        grid.getColumn("Uhrzeit").getSortOrder(SortDirection.DESCENDING);



        //Spalte Symptome
        grid.addColumn(SymptomTable::getSymptom).setId("Ausprägung der Symptome");
        grid.getColumn("Ausprägung der Symptome").setCaption("Ausprägung der Symptome");
        grid.sort("Datum", SortDirection.DESCENDING);


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

    public TreeGrid<SymptomEvaluationView.SymptomTable> getGrid() {
        return grid;
    }
    public ComboBox<String> getFilterComboBox() {
        return filterComboBox;
    }

    public DateField getFromDate() {
        return fromDate;
    }

    public DateField getToDate() {
        return toDate;
    }

    public Button getResetButton() {
        return resetButton;
    }

    //helperclass:
    public class SymptomTable {
        private Symptom symptom;
        private LocalDateTime date;
        private LocalDateTime clock;

        private ArrayList<SymptomTable> subEntries = new ArrayList<>();

        public SymptomTable(LocalDate localDate){
            this.date = localDate.atStartOfDay();
        }
        public SymptomTable(LocalDateTime d, Symptom s) {
            this.symptom = s;
            this.date = d;
            this.clock = d;
        }
        public void addSubentries(SymptomTable s){
            subEntries.add(s);
        }
        public ArrayList<SymptomTable> getSubEntries(){
            return subEntries;
        }
        public Symptom getSymptom() {
            return symptom;
        }

        public LocalDate getDate() {
            return date.toLocalDate();
        }
        public LocalDateTime getClock(){return clock;}
    }



}
