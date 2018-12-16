package de.tud.view.DiaryEvaluation;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.*;

public class EvaluationView {
    TreeGrid<SymptomEvaluationView.SymptomTable> grid;
    VerticalLayout tableContainer = new VerticalLayout();
    ComboBox<String> filterComboBox = new ComboBox<>();
    DateField fromDate = new DateField();
    DateField toDate = new DateField();
    HorizontalLayout filterBar = new HorizontalLayout();
    Button resetButton = new Button(VaadinIcons.ARROW_BACKWARD);
    int height = 170;
    int width = 300;

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

}
