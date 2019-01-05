package de.tud.controller;

import de.tud.model.manager.MedicationPlanManager;
import de.tud.model.medication.DummyMedication;
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
        try{
            List<DummyMedication> dummyMedication = medicationPlanManager.getAllDummyMedication();
            medicationScheduleView.getMedicationSchedule().setItems(dummyMedication);
        }
        catch(NullPointerException e){
            e.getMessage();
        }

    }
}
