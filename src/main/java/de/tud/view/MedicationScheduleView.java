package de.tud.view;

import com.vaadin.navigator.View;
import com.vaadin.ui.*;
import de.tud.controller.MedicationScheduleController;
import de.tud.model.medication.DummyMedication;


public class MedicationScheduleView implements View {
    //Elemente definiert
    private Label headline;
    private Grid<DummyMedication> medicationSchedule;

    //Controller
    private MedicationScheduleController medicationScheduleController;

    public MedicationScheduleView(){
        this.medicationScheduleController= new MedicationScheduleController(this);
    }

    @Override
    public Component getViewComponent(){
        final VerticalLayout verticalLayout= new VerticalLayout();
        headline = new Label("Medikationsplan");
        verticalLayout.addComponent(headline);
        verticalLayout.setSpacing(true);
        verticalLayout.setMargin(true);

        //Grid erzeugen
        medicationSchedule = new Grid<>();
        medicationSchedule.addColumn(DummyMedication::getId).setCaption("ID");
        medicationSchedule.addColumn(DummyMedication::getTradeName).setCaption("Handelsname");
        medicationSchedule.addColumn(DummyMedication::getSubstance).setCaption("Substanz");
        medicationSchedule.addColumn(DummyMedication::getForm).setCaption("Form");
        medicationSchedule.addColumn(DummyMedication::getStrength).setCaption("Stärke");
        medicationSchedule.addColumn(DummyMedication::getIncompatibleWithAsString).setCaption("Inkompatibel mit ...");

        verticalLayout.addComponent(medicationSchedule);
        return verticalLayout;
    }

    public Label getHeadline() {
        return headline;
    }

    public void setHeadline(Label headline) {
        this.headline = headline;
    }

    public Grid getMedicationSchedule() {
        return medicationSchedule;
    }

    public void setMedicationSchedule(Grid medicationSchedule) {
        this.medicationSchedule = medicationSchedule;
    }

    public MedicationScheduleController getMedicationScheduleController() {
        return medicationScheduleController;
    }

    public void setMedicationScheduleController(MedicationScheduleController medicationScheduleController) {
        this.medicationScheduleController = medicationScheduleController;
    }
}
