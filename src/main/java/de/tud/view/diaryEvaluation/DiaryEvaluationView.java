package de.tud.view.diaryEvaluation;

import com.vaadin.navigator.View;
import com.vaadin.ui.*;
import de.tud.controller.DiaryEvaluationViewController;


public class DiaryEvaluationView extends Composite implements View {
    private VerticalLayout verticalLayout = new VerticalLayout();
    private Button symptomTableButton = new Button("Symptome");
    private Button vitalDataTableButton = new Button("Vitaldaten");
    private Button welfareTableButton = new Button("Wohlbefinden");
    private HorizontalLayout menuBar = new HorizontalLayout();

    private DiaryEvaluationViewController diaryEvaluationViewController;


    public DiaryEvaluationView() {
        UI.getCurrent().getPage().getStyles().add("#yellow{ background-color: #fdff40 !important;}"+
                "#green{background-color: #60ec1a !important;}"+
                "#blue{background-color: #10bbee !important; }");

        symptomTableButton.setId("yellow");
        vitalDataTableButton.setId("green");
        welfareTableButton.setId("blue");
        symptomTableButton.setEnabled(false);

        menuBar.addComponents(symptomTableButton, vitalDataTableButton, welfareTableButton);
        verticalLayout.addComponents(menuBar);
        verticalLayout.setComponentAlignment(menuBar, Alignment.MIDDLE_LEFT);

        //Verbindung zu DiaryEvaluationViewController
        this.diaryEvaluationViewController = new DiaryEvaluationViewController(this);

        addClickListenerForSymptomButton();
        addClickListenerForVitalDataButton();
        addClickListenerForWelfareButton();

        setCompositionRoot(verticalLayout);
    }

    private void addClickListenerForSymptomButton(){
        diaryEvaluationViewController.addClickListenerForSymptomButton();
    }
    private void addClickListenerForVitalDataButton(){
        diaryEvaluationViewController.addClickListenerForVitalDataButton();
    }
    private void addClickListenerForWelfareButton(){
        diaryEvaluationViewController.addClickListenerForWelfareButton();
    }

    public VerticalLayout getVerticalLayout() {
        return verticalLayout;
    }

    public Button getSymptomTableButton() {
        return symptomTableButton;
    }

    public Button getVitalDataTableButton() {
        return vitalDataTableButton;
    }

    public Button getWelfareTableButton() {
        return welfareTableButton;
    }

}
