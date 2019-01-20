package de.tud.view.medicationPlan;

import com.vaadin.ui.Component;
import org.vaadin.risto.stepper.FloatStepper;

public class CustomFloatStepper extends FloatStepper implements Component {

    private FloatStepper floatStepper;

    public CustomFloatStepper(){
        floatStepper = new FloatStepper();
        floatStepper.setStepAmount(1.0f);
        floatStepper.setManualInputAllowed(true);
        floatStepper.setMinValue(0f);
        floatStepper.setMaxValue(2000f);
        floatStepper.setNumberOfDecimals(2);
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
