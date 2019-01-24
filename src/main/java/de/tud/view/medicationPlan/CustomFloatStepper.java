package de.tud.view.medicationPlan;

import org.vaadin.risto.stepper.FloatStepper;

public class CustomFloatStepper extends FloatStepper {

    private FloatStepper floatStepper;

    public CustomFloatStepper(){
        floatStepper = new FloatStepper();
        configure();
    }

    private void configure(){
        floatStepper.setStepAmount(1.0f);
        floatStepper.setManualInputAllowed(true);
        floatStepper.setMinValue(0f);
        floatStepper.setMaxValue(2000f);
        floatStepper.setNumberOfDecimals(2);
    }
}
