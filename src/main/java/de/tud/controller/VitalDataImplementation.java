package de.tud.controller;

import com.github.appreciated.material.MaterialTheme;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.server.ClassResource;
import de.tud.view.VitalData;

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
weightLabel.addStyleName(MaterialTheme.CARD_2);



//Setup Stepper
weightStepper.setMinValue(0.0f);
weightStepper.setMaxValue(1000f);
weightStepper.setStepAmount(1f);
weightStepper.setValue(0f);
weightStepper.setNumberOfDecimals(2);
weightStepper.setDescription("Bitte geben Sie Ihr Gewicht ein.");
weightStepper.setIncreaseIcon(VaadinIcons.PLUS);
weightStepper.setDecreaseIcon(VaadinIcons.MINUS);




}



}
