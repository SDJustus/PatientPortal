package de.tud.controller;

import com.kbdunn.vaadin.addons.fontawesome.FontAwesome;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Label;
import de.tud.view.VitalData;
import org.vaadin.risto.stepper.client.ui.FloatStepper;

import javax.swing.*;

public class VitalDataImplementation extends VitalData implements View {


public VitalDataImplementation()
{

//Setup icons

weight.addStyleName("icon50px");



//Setup Stepper
weightStepper.setMinValue(0.0f);
weightStepper.setMaxValue(1000f);
weightStepper.setStepAmount(1f);
weightStepper.setValue(0f);
weightStepper.setNumberOfDecimals(2);
weightStepper.setDescription("Bitte geben Sie Ihr Gewicht ein.");





}



}
