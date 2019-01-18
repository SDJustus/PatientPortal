package de.tud.view.Welfare;

import com.github.appreciated.material.MaterialTheme;
import com.vaadin.server.ClassResource;
import com.vaadin.ui.*;
import de.tud.controller.WelfareController;

import java.time.LocalDateTime;

public class WelfareUISetup extends WelfareDesigner {

WelfareController welfareController;

public WelfareUISetup() {
//Layout
/*
    welfareController = new WelfareController(this);
*/
    captionLabel.addStyleName(MaterialTheme.CARD_0_5);
    captionLabel.addStyleName(MaterialTheme.LABEL_H1);
    captionLabel.addStyleName("catbackground");


    sleep.addStyleName("layoutwithborder");
    sleep.setSpacing(true);
    sleep.setMargin(true);
    sleep.addStyleName(MaterialTheme.CARD_1);

    concentration.addStyleName("layoutwithborder");
    concentration.setSpacing(true);
    concentration.setMargin(true);
    concentration.addStyleName(MaterialTheme.CARD_1);

    fitnesslay.addStyleName("layoutwithborder");
    fitnesslay.setSpacing(true);
    fitnesslay.setMargin(true);
    fitnesslay.addStyleName(MaterialTheme.CARD_1);


    sleepPicture.setSource(new ClassResource("/welfareImages/sleep.png"));
    fitnessPicture.setSource(new ClassResource("/welfareImages/fitnesspicture.png"));
    concentrationPicture.setSource(new ClassResource("/welfareImages/concentration.png"));

    //Label Setup
    concentrationLabel.setValue("Konzentrationsfähigkeit:");
    fitnessLabel.setValue("Fitness:");
    sleepLabel.setValue("Schlaf:");

   //radioButtonSetup

    concentrationRadioButton.setItems("gut", "mittel", "schlecht");
    sleepRadioButton.setItems("gut", "mittel", "schlecht");
    fitnessRadioButton.setItems("gut", "mittel", "schlecht");

    //default value on radiobuttons
    concentrationRadioButton.setValue("gut");
    sleepRadioButton.setValue("gut");
    fitnessRadioButton.setValue("gut");

    //save button config
    save.setCaption("Speichern");
    save.setDescription("Speichern des Wohlbefindens");
    //save.setPrimaryStyleName(MaterialTheme.BUTTON_ROUND);
    save.setIcon(new ClassResource("/saveicon.png"));
    //save.addStyleName(MaterialTheme.LABEL_TINY);

    concentrationRadiobuttonPicture.setSource(new ClassResource("/gut.png"));
    concentrationRadiobuttonPicture.setWidth("96px");
    concentrationRadiobuttonPicture.setHeight("96px");

    fitnessRadiobuttonPicture.setSource(new ClassResource("/gut.png"));
    fitnessRadiobuttonPicture.setWidth("96px");
    fitnessRadiobuttonPicture.setHeight("96px");

    sleepRadiobuttonPicture.setSource(new ClassResource("/gut.png"));
    sleepRadiobuttonPicture.setWidth("96px");
    sleepRadiobuttonPicture.setHeight("96px");



    //panel setup
    super.setWidth("100%");
    super.setHeight("720px");
    super.getContent().setSizeUndefined();

//Label Setup
    sleepLabel.addStyleName(MaterialTheme.LABEL_BORDERLESS);
    fitnessLabel.addStyleName(MaterialTheme.LABEL_BORDERLESS);
    concentrationLabel.addStyleName(MaterialTheme.LABEL_BORDERLESS);


    dataPicker.addStyleName(MaterialTheme.DATEFIELD_ALIGN_CENTER);
    captionLabel.setValue("Bitte geben Sie Ihre Daten an:");

  //  this.addSaveButtonListener();
   // this.addDateTimeFieldChangeListener();
    //welfareController.addRadioButtonListenerPiucture();


dataPicker.setDefaultValue( LocalDateTime.now());
dataPicker.setRangeEnd(LocalDateTime.now());


}


}
