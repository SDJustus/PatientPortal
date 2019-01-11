package de.tud.controller;

import de.tud.model.manager.MedicationPlanManager;
import de.tud.model.medication.DummyMedication;
import de.tud.model.medication.Medication;
import de.tud.view.MedicationPlanView;
import java.util.List;

public class MedicationPlanController {

    private MedicationPlanView medicationPlanView;
    MedicationPlanManager medicationPlanManager;

    public MedicationPlanController(MedicationPlanView medicationPlanView){

        this.medicationPlanView = medicationPlanView;
        medicationPlanManager = MedicationPlanManager.getInstance();
    }

    public void loadMedicationSchedule(){
        try{
            List<DummyMedication> dummyMedication = medicationPlanManager.getAllDummyMedication();
            medicationPlanView.getMedicationPlan().setItems(dummyMedication);

        }
        catch(NullPointerException e){
            e.getMessage();
        }

    }
}
