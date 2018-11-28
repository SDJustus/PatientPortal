package de.tud.controller;

import com.vaadin.data.HasValue;
import com.vaadin.event.MouseEvents;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.themes.ValoTheme;
import de.tud.model.symptom.Symptom;
import de.tud.model.symptom.SymptomFactory;
import de.tud.view.SymptomSelectionView;

public class SymptomSelectionViewController {

    private SymptomSelectionView symptomSelectionView;
    private DiaryViewController diaryViewController;
    private String selectedSymptom;
    private Symptom symptomArt;
    private Symptom.Strength choice;
    private int selectionCounter = 0; //um dafür zu sorgen, dass nur einmal der Button "+weiteres Symptom" erscheint

    public SymptomSelectionViewController(SymptomSelectionView symptomSelectionView, DiaryViewController diaryViewController){
        this.diaryViewController = diaryViewController;
        System.out.println(diaryViewController.getSymptomList());
        this.symptomSelectionView = symptomSelectionView;
    }

    public boolean checkComboBox(){
        if(selectedSymptom == null){
            Notification.show("Bitte erst ein Symptom auswählen!");
            return false;
        }
        return true;
    }


    //Button darf nur einmal gedrückt werden
    //TODO Button can be pressed only one time
    public void addClickListenerToAddNextSymptom(){
        symptomSelectionView.getAddNextSymptom().addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                SymptomSelectionView newSymptomSelectionView= new SymptomSelectionView(diaryViewController);
                selectionCounter += 1;

                diaryViewController.getSymptomList().remove(selectedSymptom);

                if(diaryViewController.getSymptomList().size() == 2){
                    diaryViewController.setSaveButtonEnabled(false);
                    symptomSelectionView.getAddNextSymptom().setVisible(false);
                    diaryViewController.addNewSymptomSelectionView(newSymptomSelectionView);
                    return;
                }
                diaryViewController.addNewSymptomSelectionView(newSymptomSelectionView);
                diaryViewController.setSaveButtonEnabled(false);
                symptomSelectionView.getAddNextSymptom().setVisible(false);
            }
        });
    }


    public void addClickListenerForSmileys(){
        symptomSelectionView.getGoodSmiley().addClickListener(new MouseEvents.ClickListener() {
            @Override
            public void click(MouseEvents.ClickEvent clickEvent) {
                if(checkComboBox()== true) {
                    symptomSelectionView.getMiddleLabel().setValue("");
                    symptomSelectionView.getBadLabel().setValue("");
                    symptomSelectionView.getGoodLabel().setValue("keine");
                    choice = Symptom.Strength.WEAK;
                    symptomArt = SymptomFactory.createSymptomByClass(selectedSymptom, choice);
                    diaryViewController.setSaveButtonEnabled(true);
                    checkAddNextSymptomRestrictions();
                }
            }
        });
        symptomSelectionView.getMiddleSmiley().addClickListener(new MouseEvents.ClickListener() {
            @Override
            public void click(MouseEvents.ClickEvent clickEvent) {
                if(checkComboBox()== true) {
                    symptomSelectionView.getGoodLabel().setValue("");
                    symptomSelectionView.getBadLabel().setValue("");
                    symptomSelectionView.getMiddleLabel().setValue("mäßig");
                    choice = Symptom.Strength.MIDDLE;
                    symptomArt = SymptomFactory.createSymptomByClass(selectedSymptom, choice);
                    diaryViewController.setSaveButtonEnabled(true);
                    checkAddNextSymptomRestrictions();
                }

            }
        });
        symptomSelectionView.getBadSmiley().addClickListener(new MouseEvents.ClickListener() {
            @Override
            public void click(MouseEvents.ClickEvent clickEvent) {
                if(checkComboBox()== true) {
                    symptomSelectionView.getGoodLabel().setValue("");
                    symptomSelectionView.getMiddleLabel().setValue("");
                    symptomSelectionView.getBadLabel().setValue("stark");
                    choice = Symptom.Strength.SEVERE;
                    symptomArt = SymptomFactory.createSymptomByClass(selectedSymptom, choice);
                    diaryViewController.setSaveButtonEnabled(true);
                    checkAddNextSymptomRestrictions();
                }
            }
        });
    }

    public void addValueChangeListenerForComboBox(){
        symptomSelectionView.getComboBox().addValueChangeListener(new HasValue.ValueChangeListener<String>() {
            @Override
            public void valueChange(HasValue.ValueChangeEvent<String> valueChangeEvent) {

                symptomSelectionView.getSymptomName().setValue(valueChangeEvent.getValue());
                symptomSelectionView.getSymptomName().addStyleName(ValoTheme.LABEL_BOLD);
                selectedSymptom = valueChangeEvent.getValue();

                if(selectionCounter == 1){
                    symptomSelectionView.getAddNextSymptom().setEnabled(false);
                    symptomSelectionView.getAddNextSymptom().setVisible(false);

                    //auswahl automatisch zurücksetzen
                    symptomSelectionView.getGoodLabel().setValue("keine");
                    symptomSelectionView.getMiddleLabel().setValue("mäßig");
                    symptomSelectionView.getBadLabel().setValue("stark");
                    choice = null;
                    diaryViewController.setSaveButtonEnabled(false);
                    return;
                }
                if(diaryViewController.getSymptomList().size() == 1){
                    symptomSelectionView.getAddNextSymptom().setVisible(false);
                    return;
                }
            }
        });
    }
    private void checkAddNextSymptomRestrictions(){
        if(selectedSymptom != null && selectionCounter < 1 && diaryViewController.getSymptomList().size() >=2){
            symptomSelectionView.getAddNextSymptom().setEnabled(true);
            symptomSelectionView.getAddNextSymptom().setVisible(true);
        }
    }

    public String getSelectedSymptom(){
        return selectedSymptom;
    }
    public Symptom.Strength getChoice(){
        return this.choice;
    }
    public Symptom getSymptomArt(){
        return this.symptomArt;
    }





}
