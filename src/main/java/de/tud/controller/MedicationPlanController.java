package de.tud.controller;

import de.tud.model.manager.MedicationPlanManager;
import de.tud.model.medication.DummyMedication;
import de.tud.model.medication.Medication;
import de.tud.view.medicationPlan.MedicationPlanUIModel;
import de.tud.view.medicationPlan.MedicationPlanView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MedicationPlanController {

    private MedicationPlanView medicationPlanView;
    private MedicationPlanManager medicationPlanManager;

    private Set<MedicationPlanUIModel> medicationPlanEntityList;

    public MedicationPlanController(MedicationPlanView medicationPlanView){

        this.medicationPlanView = medicationPlanView;
        medicationPlanManager = MedicationPlanManager.getInstance();
    }

    public void loadMedicationPlan(){
        medicationPlanEntityList = new HashSet<>();
        List<Medication> allMedications = medicationPlanManager.read();
        for(Medication x:allMedications){
            medicationPlanEntityList.add(new MedicationPlanUIModel(x,null));
        }

        for(MedicationPlanUIModel x: medicationPlanEntityList){
            long dummyID = x.getMedication().getDmId();
            x.setDummyMedication(medicationPlanManager.getDummyMedicationByDummyMedicationId(dummyID));
        }

        medicationPlanView.getMedicationPlanGrid().setItems(medicationPlanEntityList);
    }
}
