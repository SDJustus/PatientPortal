package de.tud.controller;

import com.github.appreciated.material.MaterialTheme;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.server.ClassResource;
import de.tud.view.VitalData;
import javafx.scene.paint.Material;

import javax.swing.*;

public class VitalDataImplementation extends VitalData implements View {


public VitalDataImplementation()
{

 //Layout
    weight.addStyleName("layoutwithborder");
    weight.setSpacing(true);
    weight.setMargin(true);
    weight.addStyleName(MaterialTheme.CARD_1);

//Setup icons
weightPicture.setSource(new ClassResource("/waage.png"));


weightLabel.addStyleName(MaterialTheme.LABEL_BORDERLESS);



//Setup Stepper
weightStepper.setMinValue(0.0f);
weightStepper.setMaxValue(1000f);
weightStepper.setStepAmount(1f);
weightStepper.setValue(0f);
weightStepper.setNumberOfDecimals(2);
weightStepper.setDescription("Bitte geben Sie Ihr Gewicht ein.");
weightStepper.addStyleName(MaterialTheme.COMBOBOX_ALIGN_CENTER);






//save button config
    saveVitalData.setCaption("Speichern");
    saveVitalData.setDescription("Speichern der Vitaldaten");
    saveVitalData.addStyleName(MaterialTheme.BUTTON_ROUND);
    saveVitalData.setIcon(new ClassResource("/saveicon.png"));
    saveVitalData.addStyleName(MaterialTheme.LABEL_TINY);

}



}
