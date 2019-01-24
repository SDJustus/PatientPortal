package de.tud.view.medicationPlan;

import com.github.appreciated.material.MaterialTheme;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.server.ClassResource;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.ui.renderers.ButtonRenderer;
import de.tud.controller.MedicationPlanController;
import de.tud.model.medication.DummyMedication;
import de.tud.model.medication.Unit;
import org.vaadin.risto.stepper.FloatStepper;
import org.vaadin.risto.stepper.IntStepper;

public class MedicationPlanView implements View {
    //Main Layout
    private VerticalLayout verticalLayoutMain;


    // Medication Plan Form Components
    private HorizontalLayout formFinalHorizontalLayout;
    private VerticalLayout formTextSafeLayout;
    private FormLayout medicationPlanFormLayout;

    //Medication Form Components
    private Label medicationFormHeadline;
    private Button saveMedicationFormButton;

    private IntStepper idTextField;
    private TextArea hintsTextField;
    private FloatStepper stepperMorning;
    private FloatStepper stepperNoon;
    private FloatStepper stepperAfternoon;
    private FloatStepper stepperNight;
    private TextArea reasonTextField;
    private ComboBox unitComboBox;
    private Button delete;

    //Medication Plan Grid Components
    private HorizontalLayout gridHeadlineLayout;
    private Label medicationEditHint;
    private Label medicationGridHeadline;
    private Grid<MedicationPlanUIModel> medicationPlanGrid;

    //Controller
    private MedicationPlanController medicationPlanController;

    public MedicationPlanView(){
        this.medicationPlanController = new MedicationPlanController(this);
    }

    @Override
    public Component getViewComponent(){
        //main Components Setup
        verticalLayoutMain = new VerticalLayout();

        //HeadlineForm
        medicationFormHeadline = new Label();
        medicationFormHeadline.setValue("Neue Medikation hinzufügen:");
        medicationFormHeadline.addStyleName(MaterialTheme.CARD_0_5);
        medicationFormHeadline.addStyleName(MaterialTheme.LABEL_H1);

        //Hint setup
        medicationEditHint = new Label();
        medicationEditHint.setValue("Klicken Sie doppelt auf einen Eintrag um diesen zu bearbeiten");

        //Headline MedicationPlan Grid
        medicationGridHeadline = new Label("Medikationsplan:");
        medicationGridHeadline.addStyleName(MaterialTheme.CARD_0_5);
        medicationGridHeadline.addStyleName(MaterialTheme.LABEL_H1);

        //Save-Button
        saveMedicationFormButton = new Button();
        saveMedicationFormButton.setCaption("Speichern");
        saveMedicationFormButton.setDescription("Speichern der Medikation");
        saveMedicationFormButton.setIcon(new ClassResource("/saveicon.png"));
        medicationPlanController.addSafeButtonListener();

        //Delete Button
        delete = new Button(VaadinIcons.CLOSE_CIRCLE);
        delete.setCaption("Löschen");
        delete.setDescription("Löscht den aktuell ausgewählten Eintrag");
        delete.setEnabled(false);
        medicationPlanController.addDeleteButtonListener();


        //Setup unitComboBox
        unitComboBox = new ComboBox();
        unitComboBox.setItems(Unit.values());
        
        //Hints Text field setup
        hintsTextField = new TextArea();
        hintsTextField.setCaption("Hinweise");
        hintsTextField.setWidth("400px");
        hintsTextField.setHeight("80px");

        //ReasonTextField Setup
        reasonTextField= new TextArea();
        reasonTextField.setCaption("Grund");
        reasonTextField.setWidth("400px");
        reasonTextField.setHeight("80px");

        //ID Field Setup
        idTextField = new IntStepper();
        idTextField.setCaption("ID");
        idTextField.setStepAmount(1);
        idTextField.setMinValue(0);
        idTextField.setMaxValue(medicationPlanController.getDummyMedicationCount());

        //Stepper Einnahmezeiten
        stepperNoon = new FloatStepper();
        stepperNoon.setCaption("Mittag");
        stepperNoon.setStepAmount(1.0f);
        stepperNoon.setManualInputAllowed(true);
        stepperNoon.setMinValue(0f);
        stepperNoon.setMaxValue(2000f);
        stepperNoon.setNumberOfDecimals(2);


        //Stepper Einnahmezeiten
        stepperMorning = new FloatStepper();
        stepperMorning.setCaption("Morgen");
        stepperMorning.setStepAmount(1.0f);
        stepperMorning.setManualInputAllowed(true);
        stepperMorning.setMinValue(0f);
        stepperMorning.setMaxValue(2000f);
        stepperMorning.setNumberOfDecimals(2);


        //Stepper Einnahmezeiten
        stepperAfternoon = new FloatStepper();
        stepperAfternoon.setCaption("Abend");
        stepperAfternoon.setStepAmount(1.0f);
        stepperAfternoon.setManualInputAllowed(true);
        stepperAfternoon.setMinValue(0f);
        stepperAfternoon.setMaxValue(2000f);
        stepperAfternoon.setNumberOfDecimals(2);

        //Stepper Einnahmezeiten
        stepperNight = new FloatStepper();
        stepperNight.setCaption("Nacht");
        stepperNight.setStepAmount(1.0f);
        stepperNight.setManualInputAllowed(true);
        stepperNight.setMinValue(0f);
        stepperNight.setMaxValue(2000f);
        stepperNight.setNumberOfDecimals(2);


        //Setup Medication Plan Grid
        medicationPlanGrid = new Grid<>();
        medicationPlanGrid.addColumn(MedicationPlanUIModel::getDummyMedicationID).setCaption("ID").setWidth(55).setId("ID");
        medicationPlanGrid.addColumn(MedicationPlanUIModel::getDummyMedicationTradeName).setCaption("Handelsname").setWidthUndefined().setMinimumWidth(150);
        medicationPlanGrid.addColumn(MedicationPlanUIModel::getDummyMedicationSubstance).setCaption("Wirkstoff").setWidthUndefined().setMinimumWidth(150);
        medicationPlanGrid.addColumn(MedicationPlanUIModel::getDummyMedicationForm).setCaption("Form").setWidth(125);
        medicationPlanGrid.addColumn(MedicationPlanUIModel::getDummyMedicationStrength).setCaption("Stärke").setWidth(125);
        medicationPlanGrid.addColumn(MedicationPlanUIModel::getMedicationUnit).setCaption("Einheit").setWidth(125);
        medicationPlanGrid.addColumn(MedicationPlanUIModel::getMedicationMorningDosage).setCaption("Morgens").setWidth(125);

        medicationPlanGrid.addColumn(MedicationPlanUIModel::getMedicationNoonDosage).setCaption("Mittag").setWidth(125);
        medicationPlanGrid.addColumn(MedicationPlanUIModel::getMedicationAfternoonDosage).setCaption("Nachmittag").setWidth(125);
        medicationPlanGrid.addColumn(MedicationPlanUIModel::getMedicationNightDosage).setCaption("Nacht").setWidth(125);
        medicationPlanGrid.addColumn(MedicationPlanUIModel::getMedicationHints).setCaption("Hinweise").setWidthUndefined().setMinimumWidth(150);
        medicationPlanGrid.addColumn(MedicationPlanUIModel::getMedicationReason).setCaption("Grund").setWidthUndefined().setMinimumWidth(150);

        medicationPlanController.loadMedicationPlan();
        medicationPlanGrid.sort("ID");
        medicationPlanController.addDoubleClickListenerToGrid();

        //Grid auto size
        medicationPlanGrid.setHeight("" + (Integer.valueOf(Page.getCurrent().getBrowserWindowHeight()) - 200 ));
        medicationPlanGrid.setWidth("" + (Integer.valueOf(Page.getCurrent().getBrowserWindowWidth()) - 350 ));

        UI.getCurrent().getPage().addBrowserWindowResizeListener(e -> {
            medicationPlanGrid.setHeight("" + (e.getHeight()- 200 ));
            medicationPlanGrid.setWidth("" + (e.getWidth()- 350 ));
        });


        //Form Setup
        medicationPlanFormLayout = new FormLayout();
        medicationPlanFormLayout.addComponents(idTextField, stepperMorning, stepperNoon, stepperAfternoon, stepperNight);
        medicationPlanFormLayout.setMargin(new MarginInfo(false,true,false,true));
        formTextSafeLayout = new VerticalLayout();
        formTextSafeLayout.addComponents(hintsTextField, reasonTextField);
        formTextSafeLayout.setMargin(new MarginInfo(false,true,false,true));
        formFinalHorizontalLayout = new HorizontalLayout();
        formFinalHorizontalLayout.addComponents(medicationPlanFormLayout,unitComboBox, formTextSafeLayout,saveMedicationFormButton, delete);
        formFinalHorizontalLayout.setMargin(new MarginInfo(false,true,false,true));

        //Grid Headline Setup
        gridHeadlineLayout = new HorizontalLayout();
        gridHeadlineLayout.addComponents(medicationGridHeadline,medicationEditHint);
        gridHeadlineLayout.setComponentAlignment(medicationEditHint,Alignment.MIDDLE_CENTER);

        //FinalLayoutAddComponents
        verticalLayoutMain.addComponents(medicationFormHeadline, formFinalHorizontalLayout, gridHeadlineLayout, medicationPlanGrid);
        return verticalLayoutMain;
    }

    public Label getMedicationGridHeadline() {
        return medicationGridHeadline;
    }

    public void setMedicationGridHeadline(Label medicationGridHeadline) {
        this.medicationGridHeadline = medicationGridHeadline;
    }

    public Grid getMedicationPlanGrid() {
        return medicationPlanGrid;
    }

    public void setMedicationPlanGrid(Grid medicationPlanGrid) {
        this.medicationPlanGrid = medicationPlanGrid;
    }

    public MedicationPlanController getMedicationPlanController() {
        return medicationPlanController;
    }

    public void setMedicationPlanController(MedicationPlanController medicationPlanController) {
        this.medicationPlanController = medicationPlanController;
    }

    public Button getSaveMedicationFormButton() {
        return saveMedicationFormButton;
    }

    public IntStepper getIdTextField() {
        return idTextField;
    }

    public TextArea getHintsTextField() {
        return hintsTextField;
    }

    public FloatStepper getStepperMorning() {
        return stepperMorning;
    }

    public FloatStepper getStepperNoon() {
        return stepperNoon;
    }

    public FloatStepper getStepperAfternoon() {
        return stepperAfternoon;
    }

    public FloatStepper getStepperNight() {
        return stepperNight;
    }

    public TextArea getReasonTextField() {
        return reasonTextField;
    }

    public ComboBox getUnitComboBox() {
        return unitComboBox;
    }

    public Button getDelete() {
        return delete;
    }
}
