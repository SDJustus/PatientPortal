package de.tud.view.DiaryEvaluation;

import com.vaadin.server.Page;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.ui.*;
import de.tud.model.welfare.Welfare;

public class WelfareEvaluationView  {
    Grid<WelfareTable> grid;
    VerticalLayout tableContainer = new VerticalLayout();
    ComboBox<String> filterComboBox = new ComboBox<>();
    DateTimeField fromDate = new DateTimeField();
    DateTimeField toDate = new DateTimeField();
    HorizontalLayout filterBar = new HorizontalLayout();
    int height = 170;
    int width = 300;


    public WelfareEvaluationView(){
        this.grid = new Grid<>();
    }

    public Component getViewComponent() {
        //datePicker
        fromDate.setPlaceholder("von...");
        toDate.setPlaceholder("bis...");

        //Symptom filterCombo Box
        filterComboBox.setPlaceholder("Wohlbefinden");
        filterComboBox.setWidth("250px");

        //Spalte Datum
        grid.addColumn(WelfareTable::getDate).setId("Datum");
        grid.getColumn("Datum").setCaption("Datum");
        grid.getColumn("Datum").setResizable(false);
        grid.setSizeFull();

        //Spalte Welfare
        grid.addColumn(WelfareTable::getWelfare).setId("Ausprägung des Wohlbefindens");
        grid.getColumn("Ausprägung des Wohlbefindens").setCaption("Ausprägung des Wohlbefindens");
        grid.getColumn("Ausprägung des Wohlbefindens").setResizable(false);

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

        filterBar.addComponents(fromDate,toDate,filterComboBox);
        tableContainer.addComponents(filterBar,grid);
        tableContainer.setMargin(false);

        return tableContainer;
    }

    //helperclass
    public class WelfareTable {
        String dateTime;
        Welfare welfare;
        public WelfareTable(String dateTime, Welfare welfare ){
            this.dateTime  = dateTime;
            this.welfare = welfare;
        }
        public String getDate() {
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
