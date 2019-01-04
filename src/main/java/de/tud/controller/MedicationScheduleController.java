package de.tud.controller;

import com.vaadin.ui.Grid;
import de.tud.model.manager.MedicationPlanManager;
import de.tud.model.medication.DummyMedication;
import de.tud.model.medication.Medication;
import de.tud.view.MedicationScheduleView;
import java.util.List;

public class MedicationScheduleController {

    private MedicationScheduleView medicationScheduleView;
    MedicationPlanManager medicationPlanManager = MedicationPlanManager.getInstance();

    public MedicationScheduleController(MedicationScheduleView medicationScheduleView){
        this.medicationScheduleView = medicationScheduleView;
        medicationScheduleView.getViewComponent();
        loadMedicationSchedule();
    }

    public void loadMedicationSchedule(){
        Grid<DummyMedication> medicationScheduleGrid = new Grid(DummyMedication.class);
        List<DummyMedication> dummyMedication = medicationPlanManager.getAllDummyMedication();
        medicationScheduleGrid.setItems(dummyMedication);
        medicationScheduleView.getMedicationSchedule().setItems(dummyMedication);



        //medicationScheduleView.setMedicationSchedule(medicationScheduleGrid);
    }
}
