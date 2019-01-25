package de.tud.view.symptoms;

import com.github.appreciated.material.MaterialTheme;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.server.Page;
import com.vaadin.ui.*;
import de.tud.controller.SymptomViewController;
import de.tud.controller.SymptomSelectionViewController;
import de.tud.model.symptom.*;
import de.tud.view.ButtonView;

import java.util.TreeSet;

public class SymptomSelectionView extends ButtonView implements View  {
    private Label symptomName;
    private ComboBox<String> comboBox;
    private Button addNextSymptom;
    private Button delete;
    private SymptomSelectionViewController symptomSelectionViewController;
    private SymptomViewController symptomViewController;


    public SymptomSelectionView(SymptomViewController symptomViewController){
        this.symptomViewController = symptomViewController;
        this.symptomSelectionViewController = new SymptomSelectionViewController(this, symptomViewController);

    }

    /**
     * returns Symptom Selection View Layout container.
     */

    @Override
    public Component getViewComponent(){
        GridLayout gridLayout= new GridLayout(4,3);
        //Label erzeugen für Beschriftung
        symptomName = new Label("Bitte ein Symptom auswählen.");

        //save Button aussschalten
        symptomViewController.setSaveButtonEnabled(false);

        //ComboBox erzeugen
        comboBox = new ComboBox<>();
        comboBox.setItems(new TreeSet<>(symptomViewController.getSymptomList()));
        addValueChangeListenerForComboBox();


        //delete Button
        delete = new Button(VaadinIcons.CLOSE_CIRCLE);
        addClickListenerDelete();


        //Vertical Layout Container erzeugen
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setWidth("360px");
        verticalLayout.setHeight("30px");


        addClickListenerForSmileys();   //Click Listener hinzufügen

        //zweiter Spacer
        VerticalLayout spacer2 = new VerticalLayout();
        spacer2.setWidth("50px");


        //weiteres Symptom hinzufügen
        addNextSymptom = new Button("weiteres Symptom", VaadinIcons.PLUS_CIRCLE);
        addNextSymptom.setVisible(false);
        addClickListenerToAddNextSymptom();

        goodLabel.setResponsive(true);


        UI.getCurrent().getPage().addBrowserWindowResizeListener(e -> {
            iterateOverContainers(gridLayout, e.getWidth());
            comboBox.setWidth("230px");
            spacer2.setWidth(""+0.001*e.getWidth());
            horizontalLayout1.setHeight("1px");
            symptomName.setWidth("300px");

        });


        gridLayout.addComponent(comboBox, 0,1 );
        gridLayout.addComponent(addNextSymptom,0,2);
        gridLayout.addComponent(spacer2, 1,2);
        gridLayout.addComponent(symptomName, 2,0);
        gridLayout.addComponent(horizontalLayout, 2,1);
        gridLayout.addComponent(horizontalLayout1, 2, 2);
        gridLayout.addComponent(delete, 3,1);

        gridLayout.setSpacing(true);
        //horizontalLayout.addComponents(symptomName, horizontalLayout, spacer,horizontalLayout1);

        iterateOverContainers(gridLayout, Page.getCurrent().getBrowserWindowWidth());

        comboBox.setWidth("230px");
        spacer2.setWidth(""+0.001*Page.getCurrent().getBrowserWindowWidth());
        horizontalLayout1.setHeight("1px");
        symptomName.setWidth("300px");

        HorizontalLayout mainLayout = new HorizontalLayout();
        mainLayout.addStyleName(MaterialTheme.CARD_1);
        mainLayout.addStyleName("layoutwithborder");

        mainLayout.addComponent(gridLayout);
        mainLayout.setWidth("85%");

        return mainLayout;
    }


    //Listener
    private void addClickListenerDelete(){
        symptomSelectionViewController.addClickListenerForDelete();
    }

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
    public ComboBox<String> getComboBox() {
        return comboBox;
    }
    public Button getAddNextSymptom() {
        return addNextSymptom;
    }
    public Button getDelete(){return delete;}
    public SymptomSelectionViewController getSymptomSelectionViewController(){
        return this.symptomSelectionViewController;
    }

}
