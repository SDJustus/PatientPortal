package de.tud.view.DiaryEvaluation;

import com.vaadin.server.Page;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.ui.*;
import de.tud.model.symptom.Symptom;



public class SymptomEvaluationView {
    Grid<SymptomTable> grid;
    VerticalLayout tableContainer = new VerticalLayout();
    ComboBox<String> filterComboBox = new ComboBox<>();
    Panel panel = new Panel();
    DateTimeField fromDate = new DateTimeField();
    DateTimeField toDate = new DateTimeField();
    HorizontalLayout filterBar = new HorizontalLayout();


    int height = 250;
    int width = 300;


    public SymptomEvaluationView(){
        this.grid = new Grid<>();
    }

    public VerticalLayout getViewComponent() {
        //datePicker
        fromDate.setPlaceholder("von...");
        toDate.setPlaceholder("bis...");

        //Symptom filterCombo Box
        filterComboBox.setPlaceholder("Symptome");


        //Spalte Datum
        grid.addColumn(SymptomTable::getDate).setId("Datum");
        grid.getColumn("Datum").setCaption("Datum");
        grid.getColumn("Datum").setResizable(false);
        grid.sort("Datum", SortDirection.DESCENDING);

        //Spalte Symptome
        grid.addColumn(SymptomTable::getSymptom).setId("Ausprägung der Symptome");
        grid.getColumn("Ausprägung der Symptome").setCaption("Ausprägung der Symptome");
        grid.sort("Datum");

        grid.setFrozenColumnCount(grid.getColumns().size());

        //Table size
        grid.setHeight("" + (Integer.valueOf(Page.getCurrent().getBrowserWindowHeight()) - Integer.valueOf(height)));
        grid.setWidth("" + (Integer.valueOf(Page.getCurrent().getBrowserWindowWidth()) - Integer.valueOf(width)));

        UI.getCurrent().getPage().addBrowserWindowResizeListener(e -> {
            grid.setHeight("" + (e.getHeight() - Integer.valueOf(height)));
            grid.setWidth("" + (e.getWidth() - Integer.valueOf(width)));

        });

        filterBar.addComponents(fromDate, toDate, filterComboBox);
        filterBar.setResponsive(true);
        tableContainer.addComponents(filterBar, grid);
        tableContainer.setMargin(false);

        return tableContainer;
    }

    public Grid<SymptomTable> getGrid() {
        return grid;
    }
    public ComboBox<String> getFilterComboBox() {
        return filterComboBox;
    }

    //helperclass:
    public class SymptomTable {
        private Symptom symptom;
        private String date;

        public SymptomTable(String d, Symptom s) {
            this.symptom = s;
            this.date = d;
        }

        public Symptom getSymptom() {
            return symptom;
        }

        public String getDate() {
            return date;
        }
    }



}
