package de.tud.view.DiaryEvaluation;

import com.vaadin.server.Page;
import com.vaadin.ui.*;
import de.tud.model.welfare.Welfare;

import java.time.LocalDateTime;

public class WelfareEvaluationView  {
    Grid<WelfareTable> grid;
    VerticalLayout tableContainer = new VerticalLayout();
    ComboBox<String> filterComboBox = new ComboBox<>();
    int height = 250;
    int width = 300;


    public WelfareEvaluationView(){
        this.grid = new Grid<>();
    }

    public Component getViewComponent() {
        grid.setResponsive(true);

        //Spalte Datum
        grid.addColumn(WelfareTable::getDate).setId("Datum1");
        grid.getColumn("Datum1").setCaption("Datum2");
        grid.getColumn("Datum1").setResizable(false);
        grid.setSizeFull();

        //Spalte Welfare
        grid.addColumn(WelfareTable::getWelfare).setId("Ausprägung des Wohlbefindens");
        grid.getColumn("Ausprägung des Wohlbefindens").setCaption("Ausprägung des Wohlbefindens");
        grid.getColumn("Ausprägung des Wohlbefindens").setResizable(false);

        grid.setFrozenColumnCount(grid.getColumns().size());

        //Table size
        grid.setHeight("" + (Integer.valueOf(Page.getCurrent().getBrowserWindowHeight()) - Integer.valueOf(height)));
        grid.setWidth("" + (Integer.valueOf(Page.getCurrent().getBrowserWindowWidth()) - Integer.valueOf(width)));
        UI.getCurrent().getPage().addBrowserWindowResizeListener(e -> {
            grid.setHeight("" + (e.getHeight() - Integer.valueOf(height)));
            grid.setWidth("" + (e.getWidth() - Integer.valueOf(width)));

        });

        tableContainer.addComponents(grid);
        return tableContainer;
    }

    //helperclass
    public class WelfareTable {
        LocalDateTime dateTime;
        Welfare welfare;
        public WelfareTable(LocalDateTime dateTime, Welfare welfare ){
            this.dateTime  = dateTime;
            this.welfare = welfare;
        }
        public LocalDateTime getDate() {
            return dateTime;
        }

        public Welfare getWelfare() {
            return welfare;
        }
    }

    public Grid<WelfareEvaluationView.WelfareTable> getGrid() {
        return grid;
    }
    public ComboBox<String> getFilterComboBox() {
        return filterComboBox;
    }



}
