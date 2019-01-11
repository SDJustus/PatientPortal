package de.tud.view;

import com.github.appreciated.material.MaterialTheme;
import com.vaadin.navigator.View;
import com.vaadin.server.ClassResource;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import de.tud.controller.MedicationPlanController;
import de.tud.model.medication.DummyMedication;
import org.vaadin.risto.stepper.FloatStepper;
import org.vaadin.risto.stepper.IntStepper;


public class MedicationPlanView implements View {
    // Medication Plan Form Components
    private HorizontalLayout menuBar;
    private VerticalLayout verticalLayoutMain;

    private Label captionLabelMenuBar;
    private Button saveMedPlan;
    private ComboBox<String> comboBox;
    private TextField textfieldid;
    private TextField textfieldamount;
    private TextArea hintsTextField;
    private FloatStepper stepperFrüh;
    private FloatStepper stepperMittag;
    private FloatStepper stepperAbend;
    private FloatStepper stepperNacht;


    //Medication Plan Grid Components
    private VerticalLayout medicationPlanLayout;
    private Label headline;
    private Grid<DummyMedication> medicationPlan;

    //Controller
    private MedicationPlanController medicationPlanController;

    public MedicationPlanView(){
        this.medicationPlanController = new MedicationPlanController(this);
    }

    @Override
    public Component getViewComponent(){

        verticalLayoutMain = new VerticalLayout();
        menuBar = new HorizontalLayout();
        captionLabelMenuBar = new Label();
        saveMedPlan = new Button();

        //Caption

        captionLabelMenuBar.setValue("Neue Medikation hinzufügen:");
        captionLabelMenuBar.addStyleName(MaterialTheme.CARD_0_5);
        captionLabelMenuBar.addStyleName(MaterialTheme.LABEL_H1);

        //Save-Button

        saveMedPlan.setCaption("Speichern");
        saveMedPlan.setDescription("Speichern der Medikation");
        saveMedPlan.setIcon(new ClassResource("/saveicon.png"));


        //Combobox
        comboBox = new ComboBox<>();
        comboBox.setCaption("Einnahme");
        comboBox.setItems("Morgens","Mittags","Abends");

        //Hints Text field setup
        hintsTextField = new TextArea();
        hintsTextField.setCaption("Hinweise");
        hintsTextField.setWidth("150%");
        hintsTextField.setHeight("200%");


        //Stepper Einnahmezeiten
        stepperMittag = new FloatStepper();
        stepperMittag.setStepAmount(1.0f);
        stepperMittag.setManualInputAllowed(true);
        stepperMittag.setMinValue(0f);
        stepperMittag.setMaxValue(2000f);
        stepperMittag.setNumberOfDecimals(2);
        stepperMittag.setCaption("Mittag");


        //Stepper Einnahmezeiten
        stepperFrüh = new FloatStepper();
        stepperFrüh.setStepAmount(1.0f);
        stepperFrüh.setManualInputAllowed(true);
        stepperFrüh.setMinValue(0f);
        stepperFrüh.setMaxValue(2000f);
        stepperFrüh.setNumberOfDecimals(2);
        stepperFrüh.setCaption("Morgen");


        //Stepper Einnahmezeiten
        stepperAbend = new FloatStepper();
        stepperAbend.setStepAmount(1.0f);
        stepperAbend.setManualInputAllowed(true);
        stepperAbend.setMinValue(0f);
        stepperAbend.setMaxValue(2000f);
        stepperAbend.setNumberOfDecimals(2);
        stepperAbend.setCaption("Abend");

        //Stepper Einnahmezeiten
        stepperNacht = new FloatStepper();
        stepperNacht.setStepAmount(1.0f);
        stepperNacht.setManualInputAllowed(true);
        stepperNacht.setMinValue(0f);
        stepperNacht.setMaxValue(2000f);
        stepperNacht.setNumberOfDecimals(2);
        stepperNacht.setCaption("Nacht");

        //Setup Medication Plan Grid
        medicationPlan = new Grid<>();
        //medicationPlan.addColumn(DummyMedication::getId).setCaption("ID");
        medicationPlan.addColumn(DummyMedication::getTradeName).setCaption("Handelsname");
        medicationPlan.addColumn(DummyMedication::getSubstance).setCaption("Substanz");
        medicationPlan.addColumn(DummyMedication::getForm).setCaption("Form");
        medicationPlan.addColumn(DummyMedication::getStrength).setCaption("Stärke");
        //medicationPlan.addColumn(DummyMedication::getIncompatibleWithAsString).setCaption("Inkompatibel mit ...");

        medicationPlanController.loadMedicationSchedule();


        //Grid auto size
        medicationPlan.setHeight("" + (Integer.valueOf(Page.getCurrent().getBrowserWindowHeight()) - 200 ));
        medicationPlan.setWidth("" + (Integer.valueOf(Page.getCurrent().getBrowserWindowWidth()) - 350 ));

        UI.getCurrent().getPage().addBrowserWindowResizeListener(e -> {
            medicationPlan.setHeight("" + (e.getHeight()));
            medicationPlan.setWidth("" + (e.getWidth()));
        });


        //Headline Setup
        headline = new Label("Medikationsplan:");
        headline.addStyleName(MaterialTheme.CARD_0_5);
        headline.addStyleName(MaterialTheme.LABEL_H1);

        //Medication Plan Vertical Layout Setup
        //medicationPlanLayout = new VerticalLayout();
        //medicationPlanLayout.setMargin(true);
        //medicationPlanLayout.setSpacing(true);
        //medicationPlanLayout.addComponent(headline);
        //medicationPlanLayout.addComponent(medicationPlan);

        //Fields
        textfieldid = new TextField();
        textfieldid.setCaption("ID");
        textfieldamount = new TextField();
        textfieldamount.setCaption("Menge");

        //Add Components to Layout
        menuBar.addComponents(textfieldid, stepperFrüh, stepperMittag, stepperAbend,stepperNacht, hintsTextField, saveMedPlan);
        menuBar.setComponentAlignment(saveMedPlan,Alignment.TOP_CENTER);
        menuBar.setMargin(new MarginInfo(false, false, false, true ));

        verticalLayoutMain.addComponents(captionLabelMenuBar, menuBar, headline,medicationPlan);
        return verticalLayoutMain;
    }

    public Label getHeadline() {
        return headline;
    }

    public void setHeadline(Label headline) {
        this.headline = headline;
    }

    public Grid getMedicationPlan() {
        return medicationPlan;
    }

    public void setMedicationPlan(Grid medicationPlan) {
        this.medicationPlan = medicationPlan;
    }

    public MedicationPlanController getMedicationPlanController() {
        return medicationPlanController;
    }

    public void setMedicationPlanController(MedicationPlanController medicationPlanController) {
        this.medicationPlanController = medicationPlanController;
    }
}
