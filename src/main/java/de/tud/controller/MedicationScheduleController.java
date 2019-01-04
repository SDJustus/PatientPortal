package de.tud.controller;

import com.vaadin.ui.Grid;
import de.tud.model.manager.MedicationPlanManager;
import de.tud.model.medication.Medication;
import de.tud.view.MedicationScheduleView;
import java.util.List;

public class MedicationScheduleController {

    private MedicationScheduleView medicationScheduleView;

    public MedicationScheduleController(MedicationScheduleView medicationScheduleView){
        this.medicationScheduleView = medicationScheduleView;
        loadMedicationSchedule();
    }

    public void loadMedicationSchedule(){
        MedicationPlanManager medicationPlanManager = MedicationPlanManager.getInstance();
        Grid<Medication> medicationScheduleGrid = new Grid(Medication.class);
        List<Medication> medication = medicationPlanManager.read();
        medicationScheduleGrid.setItems(medication);
        medicationScheduleView.setMedicationSchedule(medicationScheduleGrid);
    }
}
