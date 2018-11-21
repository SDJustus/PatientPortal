package de.tud.view;

import clojure.lang.PersistentStructMap;
import com.vaadin.data.HasValue;
import com.vaadin.event.FieldEvents;
import com.vaadin.event.MouseEvents;
import com.vaadin.server.ClassResource;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import de.tud.model.symptom.*;

import java.util.ArrayList;
import java.util.List;

public class SymptomSelectionViewFactory {
    private String symptom;
    private Symptom symptomArt;
    private Label symptomName;

    private Image goodSmiley;
    private Image middleSmiley;
    private Image badSmiley;

    private Label goodLabel;
    private Label middleLabel;
    private Label badLabel;
    private Symptom.Strength choice;
    private ComboBox<String> comboBox;


    public SymptomSelectionViewFactory(String symptomname){
        this.symptom = symptomname;
    }


    public GridLayout getSymptomSelectionView(){
        GridLayout gridLayout= new GridLayout(2,3);
        //Label erzeugen für Beschriftung
        symptomName = new Label("Bitte Symptom auswählen.");

        //ComboBox erzeugen
        comboBox = new ComboBox<>();
        List<String> symptomList = new ArrayList<>();
        symptomList.add("Depression");
        symptomList.add("Schmerzen");
        symptomList.add("Spastik");
        symptomList.add("Kognitive Störung");
        symptomList.add("Spastik am rechten Arm");
        symptomList.add("Spastik am linken Arm");
        symptomList.add("Spastik am linken Bein");
        comboBox.setItems(symptomList);


        comboBox.addValueChangeListener(new HasValue.ValueChangeListener<String>() {
            @Override
            public void valueChange(HasValue.ValueChangeEvent<String> valueChangeEvent) {
                symptomName.setValue(valueChangeEvent.getValue());
                symptomName.addStyleName(ValoTheme.LABEL_BOLD);
            }
        });



        //Vertical Layout Container erzeugen
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setWidth("360px");
        verticalLayout.setHeight("240px");

        /*
        //Text Label für Symptom erzeugen
        Label symptomName = new Label(symptom+":");
        symptomName.addStyleName(ValoTheme.LABEL_BOLD);
        */


        //Horizontal Layout für Smiley Bilder erzeugen
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setWidth("360px");
        horizontalLayout.setHeight("80px");

        //SmileyBilder zu Horizontal Layout hinzufügen
        goodSmiley = new Image();
        goodSmiley.setWidth("90px");
        goodSmiley.setHeight("90px");
        goodSmiley.setId("smileybild");

        middleSmiley = new Image();
        middleSmiley.setWidth("90px");
        middleSmiley.setHeight("90px");
        middleSmiley.setId("smileybild");

        badSmiley = new Image();
        badSmiley.setWidth("90px");
        badSmiley.setHeight("90px");
        badSmiley.setId("smileybild");

        goodSmiley.setSource(new ClassResource("/gut.png"));
        middleSmiley.setSource(new ClassResource("/mittel.png"));
        badSmiley.setSource(new ClassResource("/schlecht.png"));

        addClickListenerForSmileys();   //Click Listener hinzufügen


        horizontalLayout.addComponents(goodSmiley, middleSmiley, badSmiley);
        horizontalLayout.setComponentAlignment(goodSmiley, Alignment.TOP_LEFT);
        horizontalLayout.setComponentAlignment(middleSmiley, Alignment.TOP_LEFT);
        horizontalLayout.setComponentAlignment(badSmiley, Alignment.TOP_LEFT);
        //horizontalLayout.setSpacing(true);
        horizontalLayout.setExpandRatio(goodSmiley, 1);
        horizontalLayout.setExpandRatio(middleSmiley, 1);
        horizontalLayout.setExpandRatio(badSmiley, 1);


        //Beschriftungen der Smileys
        HorizontalLayout horizontalLayout1 = new HorizontalLayout();
        horizontalLayout1.setWidth("360px");
        horizontalLayout1.setHeight("3px");


        goodLabel = new Label("keine");
        middleLabel = new Label("mäßig");
        badLabel = new Label("stark");

        horizontalLayout1.addComponents(goodLabel, middleLabel, badLabel);
        horizontalLayout1.setComponentAlignment(goodLabel, Alignment.MIDDLE_CENTER);
        horizontalLayout1.setComponentAlignment(middleLabel, Alignment.MIDDLE_CENTER);
        horizontalLayout1.setComponentAlignment(badLabel, Alignment.MIDDLE_CENTER);
        horizontalLayout1.setSpacing(true);


        //Horizontal Layout als Spacer
        HorizontalLayout spacer = new HorizontalLayout();
        horizontalLayout.setSpacing(true);

        gridLayout.addComponent(comboBox, 0,1 );
        gridLayout.addComponent(symptomName, 1,0);
        gridLayout.addComponent(horizontalLayout, 1,1);
        gridLayout.addComponent(horizontalLayout1, 1, 2);
        gridLayout.setSpacing(true);
        //verticalLayout.addComponents(symptomName, horizontalLayout, spacer,horizontalLayout1);



        return gridLayout;
    }

    private void addClickListenerForSmileys(){
        goodSmiley.addClickListener(new MouseEvents.ClickListener() {
            @Override
            public void click(MouseEvents.ClickEvent clickEvent) {
                middleLabel.setValue("");
                badLabel.setValue("");
                goodLabel.setValue("keine");
                choice = Symptom.Strength.WEAK;
                symptomArt = SymptomFactory.createSymptomByClass(symptom, choice);
                DefaultView.setSaveButton(true);
            }
        });
        middleSmiley.addClickListener(new MouseEvents.ClickListener() {
            @Override
            public void click(MouseEvents.ClickEvent clickEvent) {
                goodLabel.setValue("");
                badLabel.setValue("");
                middleLabel.setValue("mäßig");
                choice = Symptom.Strength.MIDDLE;
                symptomArt = SymptomFactory.createSymptomByClass(symptom, choice);
                DefaultView.setSaveButton(true);

            }
        });
        badSmiley.addClickListener(new MouseEvents.ClickListener() {
            @Override
            public void click(MouseEvents.ClickEvent clickEvent) {
                goodLabel.setValue("");
                middleLabel.setValue("");
                badLabel.setValue("stark");
                choice = Symptom.Strength.SEVERE;
                symptomArt = SymptomFactory.createSymptomByClass(symptom, choice);
                DefaultView.setSaveButton(true);
            }
        });

    }
    public Symptom.Strength getSelection(){
        return this.choice;
    }
    public String getSymptomName(){
        return this.symptom;
    }
    public Symptom getSymptomArt(){
        return  this.symptomArt;
    }
    public void setComboBoxItems(List<String> symptoms){
        comboBox.setItems(symptoms);
    }


}
