package de.tud.view;

import com.vaadin.data.HasValue;
import com.vaadin.event.MouseEvents;
import com.vaadin.server.ClassResource;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import de.tud.model.symptom.*;
import java.util.List;

public class SymptomSelectionViewFactory {
    private Symptom symptomArt;
    private Label symptomName;
    private String selectedSymptom;

    private Image goodSmiley;
    private Image middleSmiley;
    private Image badSmiley;

    private Label goodLabel;
    private Label middleLabel;
    private Label badLabel;
    private Symptom.Strength choice;
    private ComboBox<String> comboBox;
    private Button addNextSymptom;
    private int selectionCounter;   //um dafür zu sorgen, dass nur einmal der Button "+weiteres Symptom" erscheint
    private List<String> symptomList;


    public SymptomSelectionViewFactory(){
    }

    public GridLayout getSymptomSelectionView(){
        GridLayout gridLayout= new GridLayout(3,3);
        //Label erzeugen für Beschriftung
        symptomName = new Label("Bitte ein Symptom auswählen.");

        //save Button aussschalten
        DefaultView.setSaveButtonEnabled(false);

        //ComboBox erzeugen
        comboBox = new ComboBox<>();
        comboBox.setWidth("250px");
        comboBox.setItems(DefaultView.getSymptomsList());

        comboBox.addValueChangeListener(new HasValue.ValueChangeListener<String>() {
            @Override
            public void valueChange(HasValue.ValueChangeEvent<String> valueChangeEvent) {

                symptomName.setValue(valueChangeEvent.getValue());
                symptomName.addStyleName(ValoTheme.LABEL_BOLD);
                selectedSymptom = valueChangeEvent.getValue();

                if(selectionCounter == 1){
                    return;
                }
                if(DefaultView.getSymptomsList().size() == 1){
                    addNextSymptom.setVisible(false);
                    return;
                }
                addNextSymptom.setEnabled(true);
                addNextSymptom.setVisible(true);
                selectionCounter = 1;
            }
        });

        //Vertical Layout Container erzeugen
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setWidth("360px");
        verticalLayout.setHeight("30px");


        //Horizontal Layout für Smiley Bilder erzeugen
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setWidth("270px");
        horizontalLayout.setHeight("30px");

        //SmileyBilder zu Horizontal Layout hinzufügen
        goodSmiley = new Image();
        goodSmiley.setWidth("60px");
        goodSmiley.setHeight("60px");
        goodSmiley.setId("smileybild");

        middleSmiley = new Image();
        middleSmiley.setWidth("60px");
        middleSmiley.setHeight("60px");
        middleSmiley.setId("smileybild");

        badSmiley = new Image();
        badSmiley.setWidth("60px");
        badSmiley.setHeight("60px");
        badSmiley.setId("smileybild");

        goodSmiley.setSource(new ClassResource("/gut.png"));
        middleSmiley.setSource(new ClassResource("/mittel.png"));
        badSmiley.setSource(new ClassResource("/schlecht.png"));

        addClickListenerForSmileys();   //Click Listener hinzufügen


        horizontalLayout.addComponents(goodSmiley, middleSmiley, badSmiley);
        horizontalLayout.setComponentAlignment(goodSmiley, Alignment.TOP_CENTER);
        horizontalLayout.setComponentAlignment(middleSmiley, Alignment.TOP_CENTER);
        horizontalLayout.setComponentAlignment(badSmiley, Alignment.TOP_CENTER);
        //horizontalLayout.setSpacing(true);
        horizontalLayout.setExpandRatio(goodSmiley, 1);
        horizontalLayout.setExpandRatio(middleSmiley, 1);
        horizontalLayout.setExpandRatio(badSmiley, 1);


        //Beschriftungen der Smileys
        HorizontalLayout horizontalLayout1 = new HorizontalLayout();
        horizontalLayout1.setWidth("270px");
        horizontalLayout1.setHeight("1px");


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

        //zweiter Spacer
        VerticalLayout spacer2 = new VerticalLayout();
        spacer2.setWidth("50px");

        //weiteres Symptom hinzufügen
        addNextSymptom = new Button("+ weiteres Symptom");
        addNextSymptom.setEnabled(false);
        addNextSymptom.setVisible(false);
        addClickListenerToAddNextSymptom();



        gridLayout.addComponent(comboBox, 0,1 );
        gridLayout.addComponent(addNextSymptom,0,2);
        gridLayout.addComponent(spacer2, 1,2);
        gridLayout.addComponent(symptomName, 2,0);
        gridLayout.addComponent(horizontalLayout, 2,1);
        gridLayout.addComponent(horizontalLayout1, 2, 2);
        gridLayout.setSpacing(true);
        //verticalLayout.addComponents(symptomName, horizontalLayout, spacer,horizontalLayout1);

        return gridLayout;
    }

    private void addClickListenerForSmileys(){
        goodSmiley.addClickListener(new MouseEvents.ClickListener() {
            @Override
            public void click(MouseEvents.ClickEvent clickEvent) {
                if(checkComboBox()== true) {
                    middleLabel.setValue("");
                    badLabel.setValue("");
                    goodLabel.setValue("keine");
                    choice = Symptom.Strength.WEAK;
                    symptomArt = SymptomFactory.createSymptomByClass(symptomName.getValue(), choice);
                    DefaultView.setSaveButtonEnabled(true);
                }
            }
        });
        middleSmiley.addClickListener(new MouseEvents.ClickListener() {
            @Override
            public void click(MouseEvents.ClickEvent clickEvent) {
                if(checkComboBox()== true) {
                    goodLabel.setValue("");
                    badLabel.setValue("");
                    middleLabel.setValue("mäßig");
                    choice = Symptom.Strength.MIDDLE;
                    symptomArt = SymptomFactory.createSymptomByClass(symptomName.getValue(), choice);
                    DefaultView.setSaveButtonEnabled(true);
                }

            }
        });
        badSmiley.addClickListener(new MouseEvents.ClickListener() {
            @Override
            public void click(MouseEvents.ClickEvent clickEvent) {
                if(checkComboBox()== true) {
                    goodLabel.setValue("");
                    middleLabel.setValue("");
                    badLabel.setValue("stark");
                    choice = Symptom.Strength.SEVERE;
                    symptomArt = SymptomFactory.createSymptomByClass(symptomName.getValue(), choice);
                    DefaultView.setSaveButtonEnabled(true);
                }
            }
        });

    }
    public void addClickListenerToAddNextSymptom(){
        addNextSymptom.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                SymptomSelectionViewFactory symptomSelectionViewFactory = new SymptomSelectionViewFactory();
                DefaultView.getSymptomsList().remove(selectedSymptom);
                if(DefaultView.getSymptomsList().size() == 0){
                    addNextSymptom.setVisible(false);
                    return;
                }
                symptomSelectionViewFactory.setSymptomListItems(DefaultView.getSymptomsList());

                DefaultView.addNewSymptom(symptomSelectionViewFactory);
                addNextSymptom.setVisible(false);
            }
        });
    }

    public Symptom.Strength getSelection(){
        return this.choice;
    }
    public String getSymptomName(){
        return this.symptomName.getValue();
    }
    public Symptom getSymptomArt(){
        return  this.symptomArt;
    }


    public void setSymptomListItems(List<String> symptomList){
        this.symptomList = symptomList;
    }
    public boolean checkComboBox(){
        if(selectedSymptom == null){
            Notification.show("Bitte ein Symptom auswählen!");
            return false;
        }
        return true;
    }
    public String getSelectedSymptom(){
        return this.selectedSymptom;
    }



}
