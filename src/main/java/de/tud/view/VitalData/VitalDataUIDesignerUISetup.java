package de.tud.view.VitalData;

import com.github.appreciated.material.MaterialTheme;
import com.vaadin.navigator.View;
import com.vaadin.server.ClassResource;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.*;
import de.tud.controller.DiaryViewController;
import de.tud.controller.VitalDataUIController;
import de.tud.view.VitalDataUIDesigner;
import org.vaadin.risto.stepper.FloatStepper;

public class VitalDataUIDesignerUISetup extends VitalDataUIDesigner implements View {

VitalDataUIController vitalDataUIController;
public VitalDataUIDesignerUISetup()
{

    //Verbindung zu Controller
    vitalDataUIController = new VitalDataUIController(this);

 //Layout
    weight.addStyleName("layoutwithborder");
    weight.setSpacing(true);
    weight.setMargin(true);
    weight.addStyleName(MaterialTheme.CARD_1_5);
    weight.addStyleName("catbackground");

    heartRate.addStyleName("layoutwithborder");
    heartRate.setSpacing(true);
    heartRate.setMargin(true);
    heartRate.addStyleName(MaterialTheme.CARD_1_5);

    height.addStyleName("layoutwithborder");
    height.setSpacing(true);
    height.setMargin(true);
    height.addStyleName(MaterialTheme.CARD_1_5);

    bloodPressure.addStyleName("layoutwithborder");
    bloodPressure.setSpacing(true);
    bloodPressure.setMargin(true);
    bloodPressure.addStyleName(MaterialTheme.CARD_1_5);

//Setup icons
    weightPicture.setSource(new ClassResource("/vitaldatapictures/waage.png"));
    heightPicture.setSource(new ClassResource("/vitaldatapictures/lineal.png"));
    bloodPressurePicture.setSource(new ClassResource("/vitaldatapictures/anatomischesherz.png"));
    heartRatePicture.setSource(new ClassResource("/vitaldatapictures/puls.png"));
    bloodPressurePicture11.setSource(new ClassResource("/vitaldatapictures/herz2.png"));



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

//panel setup
    super.setWidth("100%");
    super.setHeight("850px");
    super.getContent().setSizeUndefined();

//Label Setup
    weightLabel.addStyleName(MaterialTheme.LABEL_BORDERLESS);
    heightLabel.addStyleName(MaterialTheme.LABEL_BORDERLESS);
    heartRateLabel.addStyleName(MaterialTheme.LABEL_BORDERLESS);
    bloodPressureLabel.addStyleName(MaterialTheme.LABEL_BORDERLESS);






form.setSizeUndefined();
form.addStyleName("overflow");
captionLabel.addStyleName(MaterialTheme.CARD_0_5);
captionLabel.addStyleName(MaterialTheme.LABEL_H1);
dataPicker.addStyleName(MaterialTheme.DATEFIELD_ALIGN_CENTER);

this.addSaveButtonListener();
this.addDateTimeFieldChangeListener();






}


    private void addDateTimeFieldChangeListener(){
        vitalDataUIController.addDateTimeFieldChangeListener();
    }
    private void addSaveButtonListener(){
        vitalDataUIController.addSaveButtonListener();
    }

    public HorizontalLayout getWeight() {
        return weight;
    }

    public void setWeight(HorizontalLayout weight) {
        this.weight = weight;
    }

    public Image getWeightPicture() {
        return weightPicture;
    }

    public void setWeightPicture(Image weightPicture) {
        this.weightPicture = weightPicture;
    }

    public Label getWeightLabel() {
        return weightLabel;
    }

    public void setWeightLabel(Label weightLabel) {
        this.weightLabel = weightLabel;
    }

    public FloatStepper getWeightStepper() {
        return weightStepper;
    }

    public void setWeightStepper(FloatStepper weightStepper) {
        this.weightStepper = weightStepper;
    }



    public void setHeight(HorizontalLayout height) {
        this.height = height;
    }

    public Image getHeightPicture() {
        return heightPicture;
    }

    public void setHeightPicture(Image heightPicture) {
        this.heightPicture = heightPicture;
    }

    public Label getHeightLabel() {
        return heightLabel;
    }

    public void setHeightLabel(Label heightLabel) {
        this.heightLabel = heightLabel;
    }

    public FloatStepper getHeightStepper() {
        return heightStepper;
    }

    public void setHeightStepper(FloatStepper heightStepper) {
        this.heightStepper = heightStepper;
    }

    public HorizontalLayout getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(HorizontalLayout bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public Image getBloodPressurePicture() {
        return bloodPressurePicture;
    }

    public void setBloodPressurePicture(Image bloodPressurePicture) {
        this.bloodPressurePicture = bloodPressurePicture;
    }

    public Label getBloodPressureLabel() {
        return bloodPressureLabel;
    }

    public void setBloodPressureLabel(Label bloodPressureLabel) {
        this.bloodPressureLabel = bloodPressureLabel;
    }

    public FloatStepper getBloodPressureStepper1() {
        return bloodPressureStepper1;
    }

    public void setBloodPressureStepper1(FloatStepper bloodPressureStepper1) {
        this.bloodPressureStepper1 = bloodPressureStepper1;
    }

    public FloatStepper getBloodPressureStepper11() {
        return bloodPressureStepper11;
    }

    public void setBloodPressureStepper11(FloatStepper bloodPressureStepper11) {
        this.bloodPressureStepper11 = bloodPressureStepper11;
    }

    public HorizontalLayout getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(HorizontalLayout heartRate) {
        this.heartRate = heartRate;
    }

    public Image getHeartRatePicture() {
        return heartRatePicture;
    }

    public void setHeartRatePicture(Image heartRatePicture) {
        this.heartRatePicture = heartRatePicture;
    }

    public Label getHeartRateLabel() {
        return heartRateLabel;
    }

    public void setHeartRateLabel(Label heartRateLabel) {
        this.heartRateLabel = heartRateLabel;
    }

    public FloatStepper getHeartRateStepper() {
        return heartRateStepper;
    }

    public void setHeartRateStepper(FloatStepper heartRateStepper) {
        this.heartRateStepper = heartRateStepper;
    }

    public DateTimeField getDataPicker() {
        return dataPicker;
    }

    public void setDataPicker(DateTimeField dataPicker) {
        this.dataPicker = dataPicker;
    }

    public NativeButton getSaveVitalData() {
        return saveVitalData;
    }

    public void setSaveVitalData(NativeButton saveVitalData) {
        this.saveVitalData = saveVitalData;
    }



}
