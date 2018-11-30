package de.tud.view.Welfare;

import com.github.appreciated.material.MaterialTheme;
import com.vaadin.server.ClassResource;
import com.vaadin.ui.*;

public class WelfareUISetup extends WelfareDesigner {

public WelfareUISetup() {
//Layout
    sleep.addStyleName("layoutwithborder");
    sleep.setSpacing(true);
    sleep.setMargin(true);
    sleep.addStyleName(MaterialTheme.CARD_1);

    concentration.addStyleName("layoutwithborder");
    concentration.setSpacing(true);
    concentration.setMargin(true);
    concentration.addStyleName(MaterialTheme.CARD_1);

    fitness.addStyleName("layoutwithborder");
    fitness.setSpacing(true);
    fitness.setMargin(true);
    fitness.addStyleName(MaterialTheme.CARD_1);


    sleepPicture.setSource(new ClassResource("/welfare/WelfareImages/sleep.png"));
    fitnessPicture.setSource(new ClassResource("/welfare/WelfareImages/fitness.png"));
    concentrationPicture.setSource(new ClassResource("/welfare/WelfareImages/concentration.png"));


    //save button config
    save.setCaption("Speichern");
    save.setDescription("Speichern der Vitaldaten");
    save.addStyleName(MaterialTheme.BUTTON_ROUND);
    save.setIcon(new ClassResource("/saveicon.png"));
    save.addStyleName(MaterialTheme.LABEL_TINY);



    //panel setup
    super.setWidth("100%");
    super.setHeight("720px");
    super.getContent().setSizeUndefined();

//Label Setup
    sleepLabel.addStyleName(MaterialTheme.LABEL_BORDERLESS);
    fitnessLabel.addStyleName(MaterialTheme.LABEL_BORDERLESS);
    concentrationLabel.addStyleName(MaterialTheme.LABEL_BORDERLESS);


    dataPicker.addStyleName(MaterialTheme.DATEFIELD_ALIGN_CENTER);





}

    public HorizontalLayout getConcentration() {
        return concentration;
    }

    public void setConcentration(HorizontalLayout concentration) {
        this.concentration = concentration;
    }

    public Image getConcentrationPicture() {
        return concentrationPicture;
    }

    public void setConcentrationPicture(Image concentrationPicture) {
        this.concentrationPicture = concentrationPicture;
    }

    public Label getConcentrationLabel() {
        return concentrationLabel;
    }

    public void setConcentrationLabel(Label concentrationLabel) {
        this.concentrationLabel = concentrationLabel;
    }

    public HorizontalLayout getConcentrationSmileys() {
        return concentrationSmileys;
    }

    public void setConcentrationSmileys(HorizontalLayout concentrationSmileys) {
        this.concentrationSmileys = concentrationSmileys;
    }

    public Image getGoodSmiley() {
        return goodSmiley;
    }

    public void setGoodSmiley(Image goodSmiley) {
        this.goodSmiley = goodSmiley;
    }

    public Image getMiddleSmiley() {
        return middleSmiley;
    }

    public void setMiddleSmiley(Image middleSmiley) {
        this.middleSmiley = middleSmiley;
    }

    public Image getBadSmiley() {
        return badSmiley;
    }

    public void setBadSmiley(Image badSmiley) {
        this.badSmiley = badSmiley;
    }

    public HorizontalLayout getFitness() {
        return fitness;
    }

    public void setFitness(HorizontalLayout fitness) {
        this.fitness = fitness;
    }

    public Image getFitnessPicture() {
        return fitnessPicture;
    }

    public void setFitnessPicture(Image fitnessPicture) {
        this.fitnessPicture = fitnessPicture;
    }

    public Label getFitnessLabel() {
        return fitnessLabel;
    }

    public void setFitnessLabel(Label fitnessLabel) {
        this.fitnessLabel = fitnessLabel;
    }

    public HorizontalLayout getFitnessSmileys() {
        return fitnessSmileys;
    }

    public void setFitnessSmileys(HorizontalLayout fitnessSmileys) {
        this.fitnessSmileys = fitnessSmileys;
    }

    public Image getGoodSmiley1() {
        return goodSmiley1;
    }

    public void setGoodSmiley1(Image goodSmiley1) {
        this.goodSmiley1 = goodSmiley1;
    }

    public Image getMiddleSmiley1() {
        return middleSmiley1;
    }

    public void setMiddleSmiley1(Image middleSmiley1) {
        this.middleSmiley1 = middleSmiley1;
    }

    public Image getBadSmiley1() {
        return badSmiley1;
    }

    public void setBadSmiley1(Image badSmiley1) {
        this.badSmiley1 = badSmiley1;
    }

    public HorizontalLayout getSleep() {
        return sleep;
    }

    public void setSleep(HorizontalLayout sleep) {
        this.sleep = sleep;
    }

    public Image getSleepPicture() {
        return sleepPicture;
    }

    public void setSleepPicture(Image sleepPicture) {
        this.sleepPicture = sleepPicture;
    }

    public Label getSleepLabel() {
        return sleepLabel;
    }

    public void setSleepLabel(Label sleepLabel) {
        this.sleepLabel = sleepLabel;
    }

    public HorizontalLayout getSleepSmileys() {
        return sleepSmileys;
    }

    public void setSleepSmileys(HorizontalLayout sleepSmileys) {
        this.sleepSmileys = sleepSmileys;
    }

    public Image getGoodSmiley2() {
        return goodSmiley2;
    }

    public void setGoodSmiley2(Image goodSmiley2) {
        this.goodSmiley2 = goodSmiley2;
    }

    public Image getMiddleSmiley2() {
        return middleSmiley2;
    }

    public void setMiddleSmiley2(Image middleSmiley2) {
        this.middleSmiley2 = middleSmiley2;
    }

    public Image getBadSmiley2() {
        return badSmiley2;
    }

    public void setBadSmiley2(Image badSmiley2) {
        this.badSmiley2 = badSmiley2;
    }

    public DateTimeField getDataPicker() {
        return dataPicker;
    }

    public void setDataPicker(DateTimeField dataPicker) {
        this.dataPicker = dataPicker;
    }

    public NativeButton getSave() {
        return save;
    }

    public void setSave(NativeButton save) {
        this.save = save;
    }



}
