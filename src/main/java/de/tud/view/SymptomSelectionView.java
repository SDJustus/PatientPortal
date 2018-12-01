package de.tud.view;

import com.vaadin.data.HasValue;
import com.vaadin.navigator.View;
import com.vaadin.server.ClassResource;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import de.tud.controller.DiaryViewController;
import de.tud.controller.SymptomSelectionViewController;
import de.tud.model.symptom.*;
import java.util.List;

public class SymptomSelectionView implements View {
    private Label symptomName;

    private Image goodSmiley;
    private Image middleSmiley;
    private Image badSmiley;
    private Label goodLabel;
    private Label middleLabel;
    private Label badLabel;
    private Symptom.Strength choice;
    private ComboBox<String> comboBox;
    private Button addNextSymptom;

    //Controller
    private SymptomSelectionViewController symptomSelectionViewController;
    private DiaryViewController diaryViewController;


    public SymptomSelectionView(DiaryViewController diaryViewController){
        this.diaryViewController = diaryViewController;
        this.symptomSelectionViewController = new SymptomSelectionViewController(this, diaryViewController);

    }

    public GridLayout getSymptomSelectionView(){
        GridLayout gridLayout= new GridLayout(3,3);
        //Label erzeugen für Beschriftung
        symptomName = new Label("Bitte ein Symptom auswählen.");

        //save Button aussschalten
        diaryViewController.setSaveButtonEnabled(false);

        //ComboBox erzeugen
        comboBox = new ComboBox<>();
        comboBox.setWidth("250px");
        comboBox.setItems(diaryViewController.getSymptomList());
        addValueChangeListenerForComboBox();


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


        //TODO wieder auskommentieren


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

    //Listener

    private void addValueChangeListenerForComboBox(){
        symptomSelectionViewController.addValueChangeListenerForComboBox();
    }

    private void addClickListenerForSmileys(){
        symptomSelectionViewController.addClickListenerForSmileys();

    }

    public void addClickListenerToAddNextSymptom() {
        symptomSelectionViewController.addClickListenerToAddNextSymptom();
    }

    public Label getSymptomName(){
        return this.symptomName;
    }
    public Image getGoodSmiley() {
        return goodSmiley;
    }
    public Image getMiddleSmiley() {
        return middleSmiley;
    }
    public Image getBadSmiley() {
        return badSmiley;
    }
    public Label getGoodLabel() {
        return goodLabel;
    }
    public Label getMiddleLabel() {
        return middleLabel;
    }
    public Label getBadLabel() {
        return badLabel;
    }
    public Symptom.Strength getChoice() {
        return choice;
    }
    public ComboBox<String> getComboBox() {
        return comboBox;
    }
    public Button getAddNextSymptom() {
        return addNextSymptom;
    }
    public SymptomSelectionViewController getSymptomSelectionViewController(){
        return this.symptomSelectionViewController;
    }

}
