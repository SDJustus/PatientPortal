package de.tud.controller;

import com.github.appreciated.material.MaterialTheme;
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

    heartRate.addStyleName("layoutwithborder");
    heartRate.setSpacing(true);
    heartRate.setMargin(true);
    heartRate.addStyleName(MaterialTheme.CARD_1);

    height.addStyleName("layoutwithborder");
    height.setSpacing(true);
    height.setMargin(true);
    height.addStyleName(MaterialTheme.CARD_1);

    bloodPressure.addStyleName("layoutwithborder");
    bloodPressure.setSpacing(true);
    bloodPressure.setMargin(true);
    bloodPressure.addStyleName(MaterialTheme.CARD_1);

//Setup icons
    weightPicture.setSource(new ClassResource("/vitaldatapictures/waage.png"));
    heightPicture.setSource(new ClassResource("/vitaldatapictures/lineal.png"));
    bloodPressurePicture.setSource(new ClassResource("/vitaldatapictures/anatomischesherz.png"));
    heartRatePicture.setSource(new ClassResource("/vitaldatapictures/puls.png"));



    weightLabel.addStyleName(MaterialTheme.LABEL_BORDERLESS);



//Setup Stepper
    weightStepper.setMinValue(0.0f);
    weightStepper.setMaxValue(1000f);
    weightStepper.setStepAmount(1f);
    weightStepper.setValue(0f);
    weightStepper.setNumberOfDecimals(2);
    weightStepper.setDescription("Bitte geben Sie Ihr Gewicht ein.");
    weightStepper.addStyleName(MaterialTheme.COMBOBOX_ALIGN_CENTER);

    heightStepper.setMinValue(0.3f);
    heightStepper.setMaxValue(3f);
    heightStepper.setStepAmount(0.01f);
    heightStepper.setValue(1.7f);
    heightStepper.setNumberOfDecimals(2);
    heightStepper.setDescription("Bitte geben Sie Ihre Körpergröße ein.");
    heightStepper.addStyleName(MaterialTheme.COMBOBOX_ALIGN_CENTER);

    heartRateStepper.setMinValue(30f);
    heartRateStepper.setMaxValue(400f);
    heartRateStepper.setStepAmount(1f);
    heartRateStepper.setValue(80f);
    heartRateStepper.setNumberOfDecimals(0);
    heartRateStepper.setDescription("Bitte geben Sie Ihren Puls ein.");
    heartRateStepper.addStyleName(MaterialTheme.COMBOBOX_ALIGN_CENTER);

    bloodPressureStepper1.setMinValue(0f);
    bloodPressureStepper1.setMaxValue(300f);
    bloodPressureStepper1.setStepAmount(1f);
    bloodPressureStepper1.setValue(100f);
    bloodPressureStepper1.setNumberOfDecimals(0);
    bloodPressureStepper1.setDescription("Bitte geben Sie Ihre Blutdruck ein.");
    bloodPressureStepper1.addStyleName(MaterialTheme.COMBOBOX_ALIGN_CENTER);


    bloodPressureStepper11.setMinValue(0f);
    bloodPressureStepper11.setMaxValue(300f);
    bloodPressureStepper11.setStepAmount(1f);
    bloodPressureStepper11.setValue(100f);
    bloodPressureStepper11.setNumberOfDecimals(0);
    bloodPressureStepper11.setDescription("Bitte geben Sie Ihre Blutdruck ein.");
    bloodPressureStepper11.addStyleName(MaterialTheme.COMBOBOX_ALIGN_CENTER);

//save button config
    saveVitalData.setCaption("Speichern");
    saveVitalData.setDescription("Speichern der Vitaldaten");
    saveVitalData.addStyleName(MaterialTheme.BUTTON_ROUND);
    saveVitalData.setIcon(new ClassResource("/saveicon.png"));
    saveVitalData.addStyleName(MaterialTheme.LABEL_TINY);

}



}
