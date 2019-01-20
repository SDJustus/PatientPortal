package de.tud.view.DiaryEvaluation;

import com.vaadin.navigator.View;
import com.vaadin.ui.*;
import de.tud.controller.DiaryEvaluationViewController;


public class DiaryEvaluationView extends Composite implements View {
    private VerticalLayout verticalLayout = new VerticalLayout();

    private Button symptomTableButton = new Button("Symptome");
    private Button vitalDataTableButton = new Button("Vitaldaten");
    private Button welfareTableButton = new Button("Wohlbefinden");
    private HorizontalLayout menuBar = new HorizontalLayout();

    private HorizontalLayout chartBar = new HorizontalLayout();
    private Button symptomChartButton = new Button("Symptomdiagramm");
    private Button vitaldataChartButton = new Button("Vitaldatendiagramm");
    private Button welfareChartButton = new Button("Wohlbefindendiagramm");



    private DiaryEvaluationViewController diaryEvaluationViewController;


    public DiaryEvaluationView() {
        UI.getCurrent().getPage().getStyles().add("#yellow{ background-color: #fdff40 !important;}"+
                "#green{background-color: #60ec1a !important;}"+
                "#blue{background-color: #10bbee !important; }");

        symptomTableButton.setId("yellow");
        vitalDataTableButton.setId("green");
        welfareTableButton.setId("blue");
        symptomTableButton.setEnabled(false);

        symptomChartButton.setId("yellow");
        vitaldataChartButton.setId("green");
        vitaldataChartButton.setEnabled(false);
        welfareChartButton.setId("blue");

        menuBar.addComponents(symptomTableButton, vitalDataTableButton, welfareTableButton);
        chartBar.addComponents(symptomChartButton, vitaldataChartButton, welfareChartButton);
        verticalLayout.addComponents(menuBar, chartBar );
        verticalLayout.setComponentAlignment(menuBar, Alignment.MIDDLE_LEFT);
        verticalLayout.setComponentAlignment(chartBar, Alignment.MIDDLE_LEFT);

        //Verbindung zu DiaryEvaluationViewController
        this.diaryEvaluationViewController = new DiaryEvaluationViewController(this);

        diaryEvaluationViewController.addClickListenerForSymptomChartButton();
        diaryEvaluationViewController.addClickListenerForWelfareChartButton();
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



    public HorizontalLayout getChartBar() {
        return chartBar;
    }

    public void setChartBar(HorizontalLayout chartBar) {
        this.chartBar = chartBar;
    }

    public Button getSymptomChartButton() {
        return symptomChartButton;
    }

    public void setSymptomChartButton(Button symptomChartButton) {
        this.symptomChartButton = symptomChartButton;
    }

    public Button getVitaldataChartButton() {
        return vitaldataChartButton;
    }

    public void setVitaldataChartButton(Button vitaldataChartButton) {
        this.vitaldataChartButton = vitaldataChartButton;
    }

    public Button getWelfareChartButton() {
        return welfareChartButton;
    }

    public void setWelfareChartButton(Button welfareChartButton) {
        this.welfareChartButton = welfareChartButton;
    }


}
