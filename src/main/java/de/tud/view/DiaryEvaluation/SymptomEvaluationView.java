package de.tud.view.DiaryEvaluation;

import com.vaadin.server.Page;
import com.vaadin.ui.*;
import de.tud.model.symptom.Symptom;

import java.time.LocalDateTime;

public class SymptomEvaluationView {
    Grid<SymptomTable> grid;
    VerticalLayout tableContainer = new VerticalLayout();
    ComboBox<String> filterComboBox = new ComboBox<>();
    Panel panel = new Panel();


    int height = 300;
    int width = 350;


    public SymptomEvaluationView(){
        this.grid = new Grid<>();
    }

    public VerticalLayout getViewComponent() {
        grid.setResponsive(true);
        grid.setSizeUndefined();

        //Spalte Datum
        grid.addColumn(SymptomTable::getDate).setId("Datum");
        grid.getColumn("Datum").setCaption("Datum");
        grid.getColumn("Datum").setResizable(false);
        grid.getColumn("Datum").setWidthUndefined();


        //Spalte Symptome
        grid.addColumn(SymptomTable::getSymptom).setId("Ausprägung der Symptome");
        grid.getColumn("Ausprägung der Symptome").setCaption("Ausprägung der Symptome");
        grid.getColumn("Ausprägung der Symptome").setResizable(false);
        grid.getColumn("Ausprägung der Symptome").setWidthUndefined();

        //Table size
        grid.setHeight("" + (Integer.valueOf(Page.getCurrent().getBrowserWindowHeight()) - Integer.valueOf(height)));
        grid.setWidth("" + (Integer.valueOf(Page.getCurrent().getBrowserWindowWidth()) - Integer.valueOf(width)));

        UI.getCurrent().getPage().addBrowserWindowResizeListener(e -> {
            grid.setHeight("" + (e.getHeight() - Integer.valueOf(height)));
            grid.setWidth("" + (e.getWidth() - Integer.valueOf(width)));

        });


        panel.setContent(grid);
        panel.setSizeFull();

        tableContainer.addComponents(filterComboBox, panel);

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
        private LocalDateTime date;

        public SymptomTable(LocalDateTime d, Symptom s) {
            this.symptom = s;
            this.date = d;
        }

        public Symptom getSymptom() {
            return symptom;
        }

        public LocalDateTime getDate() {
            return date;
        }
    }


}
