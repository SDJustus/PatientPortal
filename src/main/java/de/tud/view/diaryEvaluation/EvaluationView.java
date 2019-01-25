package de.tud.view.diaryEvaluation;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.*;

public abstract class EvaluationView {
    TreeGrid<DiaryEvaluationUIModel> grid = new TreeGrid<>();
    VerticalLayout tableContainer = new VerticalLayout();
    ComboBox<String> filterComboBox = new ComboBox<>();
    DateField fromDate = new DateField();
    DateField toDate = new DateField();
    HorizontalLayout filterBar = new HorizontalLayout();
    Button resetButton = new Button(VaadinIcons.ARROW_BACKWARD);
    int height = 260;
    int width = 300;


    public TreeGrid<DiaryEvaluationUIModel> getGrid() {
        return grid;
    }

    public VerticalLayout getTableContainer() {
        return tableContainer;
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

    public HorizontalLayout getFilterBar() {
        return filterBar;
    }

    public Button getResetButton() {
        return resetButton;
    }

}
