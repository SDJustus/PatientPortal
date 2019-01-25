package de.tud.controller;

import com.vaadin.data.HasValue;
import com.vaadin.event.MouseEvents;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;
import com.vaadin.ui.themes.ValoTheme;
import de.tud.model.symptom.Symptom;
import de.tud.model.symptom.SymptomFactory;
import de.tud.view.symptoms.SymptomSelectionView;

public class SymptomSelectionViewController {

    private SymptomSelectionView symptomSelectionView;
    private SymptomViewController symptomViewController;
    private String selectedSymptom;
    private Symptom symptomArt;
    private Symptom.Strength choice;
    private int selectionCounter = 0; //um dafür zu sorgen, dass nur einmal der Button "+weiteres Symptom" erscheint


    /**
     * Establish a connection between SymptomSelectionView and SymptomViewController.
     */
    public SymptomSelectionViewController(SymptomSelectionView symptomSelectionView, SymptomViewController symptomViewController){
        this.symptomViewController = symptomViewController;
        this.symptomSelectionView = symptomSelectionView;
    }

    /**
     * Method for checking the combox (ensure that first a symptom is selected, before smiley choice)
     */
    public boolean checkComboBox(){
        if(symptomSelectionView.getComboBox().getValue() == null){
            Notification.show("Bitte erst ein Symptom auswählen!");
            return false;
        }
        return true;
    }


    /**
     * Click listener to add another symptom to the diary entry.
     */
    public void addClickListenerToAddNextSymptom(){
        symptomSelectionView.getAddNextSymptom().addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                //SymptomSelectionView newSymptomSelectionView= new SymptomSelectionView(symptomViewController);
                selectionCounter += 1;

                symptomViewController.getSymptomList().remove(selectedSymptom);


                if(symptomViewController.getSymptomList().size() == 2){
                    symptomViewController.setSaveButtonEnabled(false);
                    symptomSelectionView.getAddNextSymptom().setVisible(false);
                    symptomViewController.addNewSymptomSelectionView();
                    return;
                }

                symptomViewController.addNewSymptomSelectionView();
                symptomViewController.setSaveButtonEnabled(false);

                symptomSelectionView.getAddNextSymptom().setVisible(false);
            }
        });
    }

    /**
     * Click listener for smileys (good, moderate, bad)
     */

    public void addClickListenerForSmileys(){
        symptomSelectionView.getGoodSmiley().addClickListener(new MouseEvents.ClickListener() {
            @Override
            public void click(MouseEvents.ClickEvent clickEvent) {
                if(checkComboBox()== true) {
                    symptomSelectionView.getMiddleLabel().setValue("");
                    symptomSelectionView.getBadLabel().setValue("");
                    symptomSelectionView.getGoodLabel().setValue("schwach");
                    choice = Symptom.Strength.WEAK;
                    symptomArt = SymptomFactory.createSymptomByClass(selectedSymptom, choice);

                    symptomSelectionView.getGoodSmiley().setId("");
                    symptomSelectionView.getMiddleSmiley().setId("greyscale");
                    symptomSelectionView.getBadSmiley().setId("greyscale");

                    checkAddNextSymptomRestrictions();
                    symptomSelectionView.getComboBox().setEnabled(false);

                }
            }
        });
        symptomSelectionView.getMiddleSmiley().addClickListener(new MouseEvents.ClickListener() {
            @Override
            public void click(MouseEvents.ClickEvent clickEvent) {
                if(checkComboBox()== true) {
                    symptomSelectionView.getGoodLabel().setValue("");
                    symptomSelectionView.getBadLabel().setValue("");
                    symptomSelectionView.getMiddleLabel().setValue("mittel");
                    choice = Symptom.Strength.MIDDLE;
                    symptomArt = SymptomFactory.createSymptomByClass(selectedSymptom, choice);

                    symptomSelectionView.getMiddleSmiley().setId("");
                    symptomSelectionView.getGoodSmiley().setId("greyscale");
                    symptomSelectionView.getBadSmiley().setId("greyscale");

                    checkAddNextSymptomRestrictions();
                    symptomSelectionView.getComboBox().setEnabled(false);
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

                    symptomSelectionView.getBadSmiley().setId("");
                    symptomSelectionView.getGoodSmiley().setId("greyscale");
                    symptomSelectionView.getMiddleSmiley().setId("greyscale");

                    checkAddNextSymptomRestrictions();
                    symptomSelectionView.getComboBox().setEnabled(false);

                }
            }
        });
    }


    /**
     * If a symptom from the combobox is selected, text label above the smileys is activated with
     * the selected symptom.
     * Ensures also integrity restrictions (if there is only one symptom in the symptom list over, the add next symptom button
     * will be disabled.
     */

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

                    symptomSelectionView.getGoodLabel().setValue("schwach");
                    symptomSelectionView.getMiddleLabel().setValue("mittel");
                    symptomSelectionView.getBadLabel().setValue("stark");
                    choice = null;
                    symptomViewController.setSaveButtonEnabled(false);
                    return;
                }

                if(symptomViewController.getSymptomList().size() == 1){
                    symptomSelectionView.getAddNextSymptom().setVisible(false);
                    return;
                }
            }
        });
    }
    /**
     * Click listener for the delete button to delete single symptom selections.
     */

    public void addClickListenerForDelete(){
        SymptomSelectionViewController s = this;
        symptomSelectionView.getDelete().addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {


                int index = symptomViewController.getSymptomSelectionViewControllers().indexOf(s);

                Component c = symptomViewController.getSymptomView().getVerticalLayout().getComponent(index);
                symptomViewController.getSymptomView().getVerticalLayout().removeComponent(c);
                symptomViewController.getSymptomSelectionViewControllers().remove(s);

                if(s.selectedSymptom!= null){
                    symptomViewController.getSymptomList().add(s.selectedSymptom);
                }


                checkAddNextSymptomRestrictions();
                if(symptomViewController.checkSymptomSelection()){
                    symptomViewController.setSaveButtonEnabled(true);
                }

                //wenn gelöscht wird, muss der AddNextSymptom Button beim letzten Element in der Liste erscheinen

                int index2 = symptomViewController.getSymptomSelectionViewControllers().size();

                if(index2 >= 1 && index2 < symptomViewController.getNumberOfSymptoms()) {

                    //symptomViewController.getSymptomSelectionViewControllers().get(index2 - 1).getSymptomSelectionView().getAddNextSymptom().setVisible(true);
                    symptomViewController.getSymptomSelectionViewControllers().get(index2 - 1).selectionCounter = 0;
                    if(symptomViewController.getSymptomSelectionViewControllers().get(index2-1).selectedSymptom != null){
                        symptomViewController.getSymptomSelectionViewControllers().get(index2 - 1).getSymptomSelectionView().getAddNextSymptom().setVisible(true);
                    }
                    return;
                }


                if(symptomViewController.getSymptomView().getVerticalLayout().getComponentCount() == 0){
                    symptomViewController.getSymptomView().getNewDiaryEntry().click();
                }
            }
        });

    }
    /**
     * Method to check integrity restrictions for adding another symptom to the list.
     */

    private void checkAddNextSymptomRestrictions(){
        if(symptomViewController.getNumberOfSymptoms() == symptomViewController.getSymptomSelectionViewControllers().size()){
            symptomViewController.setSaveButtonEnabled(true);
            return;
        }

        if(symptomSelectionView.getComboBox().getValue() != null
                &&selectionCounter == 0
                && symptomViewController.getSymptomList().size() == 1
                && symptomViewController.getSymptomView().getVerticalLayout().getComponentCount() > 0){
            symptomViewController.setSaveButtonEnabled(true);
            return;
        }
        if(symptomSelectionView.getComboBox().getValue() != null
                && selectionCounter == 0
                && symptomViewController.getSymptomList().size() >=2
                && symptomViewController.getSymptomView().getVerticalLayout().getComponentCount() > 0){

            symptomSelectionView.getAddNextSymptom().setEnabled(true);
            symptomSelectionView.getAddNextSymptom().setVisible(true);

            symptomViewController.setSaveButtonEnabled(true);
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
    public SymptomSelectionView getSymptomSelectionView(){ return symptomSelectionView;}





}
