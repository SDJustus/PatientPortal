package de.tud.controller;

import de.tud.model.manager.MedicationPlanManager;
import de.tud.model.medication.DummyMedication;
import de.tud.view.MedicationScheduleSetup;
import java.util.List;

public class MedicationScheduleController {

    private MedicationScheduleSetup medicationScheduleSetup;
    MedicationPlanManager medicationPlanManager;

    public MedicationScheduleController(MedicationScheduleSetup medicationScheduleSetup){

        this.medicationScheduleSetup = medicationScheduleSetup;
        medicationPlanManager = MedicationPlanManager.getInstance();
        medicationScheduleSetup.getViewComponent();
        loadMedicationSchedule();
    }

    public void loadMedicationSchedule(){
        try{
            List<DummyMedication> dummyMedication = medicationPlanManager.getAllDummyMedication();
            medicationScheduleSetup.getMedicationSchedule().setItems(dummyMedication);

        }
        catch(NullPointerException e){
            e.getMessage();
        }

    }
}
