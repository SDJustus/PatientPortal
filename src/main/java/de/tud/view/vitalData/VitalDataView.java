package de.tud.view.vitalData;

import com.github.appreciated.material.MaterialTheme;
import com.vaadin.server.ClassResource;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.navigator.View;
import de.tud.controller.VitalDataUIController;
import org.vaadin.risto.stepper.FloatStepper;

import java.time.LocalDateTime;

public class VitalDataView extends Composite implements View {
    private HorizontalLayout menuBar;
    private VerticalLayout verticalLayoutMain;
    private Panel panel;
    private FormLayout form;
    private Label captionLabel;
    private DateTimeField dataPicker;
    private Button saveVitalData;
    private HorizontalLayout weight;
    private Image weightPicture;
    private Label weightLabel;
    private FloatStepper weightStepper;
    private HorizontalLayout height;
    private Image heightPicture;
    private Label heightLabel;
    private FloatStepper heightStepper;
    private HorizontalLayout bloodPressure;
    private Image bloodPressurePicture;
    private Label bloodPressureLabel;
    private FloatStepper bloodPressureStepper1;
    private HorizontalLayout bloodPressure11;
    private Image bloodPressurePicture11;
    private Label bloodPressureLabel11;
    private FloatStepper bloodPressureStepper11;
    private HorizontalLayout heartRate;
    private Image heartRatePicture;
    private Label heartRateLabel;
    private FloatStepper heartRateStepper;
    private VitalDataUIController vitalDataUIController;

    private String width = "800px";
    private String heights = "160px";


    public VitalDataView(){
        verticalLayoutMain = new VerticalLayout();
        menuBar = new HorizontalLayout();
        panel = new Panel();
        form = new FormLayout();
        captionLabel = new Label();
        dataPicker = new DateTimeField();
        saveVitalData = new Button();
        weight = new HorizontalLayout();
        weightPicture = new Image();
        weightLabel = new Label();
        weightStepper = new FloatStepper();
        height = new HorizontalLayout();
        heightPicture = new Image();
        heightLabel = new Label();
        heightStepper = new FloatStepper();
        bloodPressure = new HorizontalLayout();
        bloodPressurePicture = new Image();
        bloodPressureLabel = new Label();
        bloodPressureStepper1 = new FloatStepper();
        bloodPressureLabel11 = new Label();
        bloodPressure11 = new HorizontalLayout();
        bloodPressurePicture11 = new Image();
        bloodPressureStepper11 = new FloatStepper();
        heartRate = new HorizontalLayout();
        heartRatePicture = new Image();
        heartRateLabel = new Label();
        heartRateStepper = new FloatStepper();

        //Verbindung zu Controller
        vitalDataUIController = new VitalDataUIController(this);

        //caption setup
        captionLabel.setValue("Bitte tragen Sie ihre Vitaldaten ein:");
        captionLabel.addStyleName(MaterialTheme.CARD_0_5);
        captionLabel.addStyleName(MaterialTheme.LABEL_H1);
        captionLabel.addStyleName("header-label");
        //captionLabel.addStyleName("catbackground");


        //Layout
        weight.addStyleName("layoutwithborder");
        weight.setSpacing(true);
        //weight.setMargin(true);
        weight.addStyleName(MaterialTheme.CARD_1_5);
        weight.addStyleName("catbackground");
        weight.setWidth(width);
        weight.setHeight(heights);
        weight.addComponents(weightPicture, weightLabel, weightStepper);

        heartRate.addStyleName("layoutwithborder");
        heartRate.setSpacing(true);
        //heartRate.setMargin(true);
        heartRate.addStyleName(MaterialTheme.CARD_1_5);
        heartRate.addStyleName("catbackground");
        heartRate.setHeight(heights);
        heartRate.setWidth(width);
        heartRate.addComponents(heartRatePicture, heartRateLabel, heartRateStepper);


        height.addStyleName("layoutwithborder");
        height.setSpacing(true);
        height.setWidth(width);
        height.setHeight(heights);
        height.addStyleName(MaterialTheme.CARD_1_5);
        height.addStyleName("catbackground");
        height.addComponents(heightPicture, heightLabel, heightStepper);

        bloodPressure.addStyleName("layoutwithborder");
        bloodPressure.setSpacing(true);
        bloodPressure.addStyleName(MaterialTheme.CARD_1_5);
        //bloodPressure.addStyleName("catbackground");
        bloodPressure.setWidth(width);
        bloodPressure.setHeight(heights);
        bloodPressure.addComponents(bloodPressurePicture, bloodPressureLabel, bloodPressureStepper1);

        bloodPressure11.addStyleName("layoutwithborder");
        bloodPressure11.setSpacing(true);
        bloodPressure11.addStyleName(MaterialTheme.CARD_1_5);
         //bloodPressure11.addStyleName("catbackground");
        bloodPressure11.setWidth(width);
        bloodPressure11.setHeight(heights);
        bloodPressure11.addComponents(bloodPressurePicture11, bloodPressureLabel11, bloodPressureStepper11);

        //Setup icons
        weightPicture.setSource(new ClassResource("/vitaldatapictures/waage.png"));
        heightPicture.setSource(new ClassResource("/vitaldatapictures/lineal.png"));
        bloodPressurePicture.setSource(new ClassResource("/vitaldatapictures/anatomischesherz.png"));
        heartRatePicture.setSource(new ClassResource("/vitaldatapictures/puls.png"));
        bloodPressurePicture11.setSource(new ClassResource("/vitaldatapictures/herz2.png"));


//Setup Stepper
        weightStepper.setMinValue(5f);
        weightStepper.setMaxValue(1000f);
        weightStepper.setStepAmount(1f);
        weightStepper.setValue(70f);
        weightStepper.setNumberOfDecimals(2);
        weightStepper.setDescription("Bitte geben Sie Ihr Gewicht ein.");
        weightStepper.addStyleName(MaterialTheme.COMBOBOX_ALIGN_CENTER);

        heightStepper.setMinValue(20f);
        heightStepper.setMaxValue(300f);
        heightStepper.setStepAmount(1f);
        heightStepper.setValue(170f);
        heightStepper.setNumberOfDecimals(0);
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
        bloodPressureStepper1.setDescription("Bitte geben Sie Ihren Blutdruck ein.");
        bloodPressureStepper1.addStyleName(MaterialTheme.COMBOBOX_ALIGN_CENTER);


        bloodPressureStepper11.setMinValue(0f);
        bloodPressureStepper11.setMaxValue(300f);
        bloodPressureStepper11.setStepAmount(1f);
        bloodPressureStepper11.setValue(100f);
        bloodPressureStepper11.setNumberOfDecimals(0);
        bloodPressureStepper11.setDescription("Bitte geben Sie Ihren Blutdruck ein.");
        bloodPressureStepper11.addStyleName(MaterialTheme.COMBOBOX_ALIGN_CENTER);



//save button config
        saveVitalData.setCaption("Speichern");
        saveVitalData.setDescription("Speichern der Vitaldaten");
        //saveVitalData.setPrimaryStyleName(MaterialTheme.BUTTON_ROUND);
        saveVitalData.setIcon(new ClassResource("/saveicon.png"));
        //saveVitalData.addStyleName(MaterialTheme.LABEL_TINY);

//panel setup

        panel.setHeight(""+0.67* Page.getCurrent().getBrowserWindowHeight());
        panel.setWidth(""+0.95*Page.getCurrent().getBrowserWindowWidth());

        UI.getCurrent().getPage().addBrowserWindowResizeListener(e -> {
            panel.setHeight(""+0.67*e.getHeight());
            panel.setWidth(""+0.95*e.getWidth());
        });

        //Label Setup
        //weightLabel.addStyleName(MaterialTheme.LABEL_BORDERLESS);
        //heightLabel.addStyleName(MaterialTheme.LABEL_BORDERLESS);
        //heartRateLabel.addStyleName(MaterialTheme.LABEL_BORDERLESS);
        //bloodPressureLabel.addStyleName(MaterialTheme.LABEL_BORDERLESS);


        weightLabel.setValue("Gewicht (kg)");
        heightLabel.setValue("Körpergröße (cm)");
        heightLabel.setStyleName(MaterialTheme.LABEL_BORDERLESS);
        heartRateLabel.setValue("Puls (Schläge/min)");
        bloodPressureLabel.setValue("Blutdruck systolisch (mmHg)");
        bloodPressureLabel11.setValue("Blutdruck diastolisch (mmHg)");


        //dataPicker.addStyleName(MaterialTheme.DATEFIELD_ALIGN_CENTER);
        //dataPicker.addStyleName("catbackground");
        dataPicker.setTextFieldEnabled(false);
        addSaveButtonListener();
        addDateTimeFieldChangeListener();

        dataPicker.setDefaultValue(LocalDateTime.now());
        dataPicker.setRangeEnd(LocalDateTime.now());



        form.addComponents(weight, height, heartRate, bloodPressure,bloodPressure11);
        panel.setContent(form);

        menuBar.addComponents(dataPicker, saveVitalData);
        menuBar.setMargin(new MarginInfo(false, false, false, true ));

        verticalLayoutMain.addComponents(captionLabel, menuBar, panel);
        setCompositionRoot(verticalLayoutMain);
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
    public FloatStepper getWeightStepper() {
        return weightStepper;
    }
    public void setHeight(HorizontalLayout height) {
        this.height = height;
    }
    public FloatStepper getHeightStepper() {
        return heightStepper;
    }
    public FloatStepper getBloodPressureStepper1() {
        return bloodPressureStepper1;
    }
    public FloatStepper getBloodPressureStepper11() {
        return bloodPressureStepper11;
    }
    public FloatStepper getHeartRateStepper() {
        return heartRateStepper;
    }
    public DateTimeField getDataPicker() {
        return dataPicker;
    }
    public Button getSaveVitalData() {
        return saveVitalData;
    }

}
