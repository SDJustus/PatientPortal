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

    private HorizontalLayout chartBar = new HorizontalLayout();
    private Button symptomChartButton = new Button("Symptomdiagramm");
    private Button vitaldataChartButton = new Button("Vitaldatendiagramm");
    private Button welfareChartButton = new Button("Wohlbefindendiagramm");


    private DiaryEvaluationViewController diaryEvaluationViewController;
    /**
     * Set up the main diary evaluation view window with buttons to navigate to specific evaluations.
     */

    public DiaryEvaluationView() {

        symptomTableButton.addStyleName("yellow");
        vitalDataTableButton.addStyleName("green");
        welfareTableButton.addStyleName("blue");
        symptomTableButton.setEnabled(false);

        symptomChartButton.addStyleName("yellow");
        vitaldataChartButton.addStyleName("green");
        vitaldataChartButton.setEnabled(false);
        welfareChartButton.addStyleName("blue");

        menuBar.addComponents(symptomTableButton, vitalDataTableButton, welfareTableButton);
        chartBar.addComponents(symptomChartButton, vitaldataChartButton, welfareChartButton);
        verticalLayout.addComponents(menuBar, chartBar );
        verticalLayout.setComponentAlignment(menuBar, Alignment.MIDDLE_LEFT);
        verticalLayout.setComponentAlignment(chartBar, Alignment.MIDDLE_LEFT);


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

    public Button getSymptomChartButton() {
        return symptomChartButton;
    }




    public Button getWelfareChartButton() {
        return welfareChartButton;
    }



}
