package de.tud.controller;

import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import de.tud.model.manager.MedicationPlanManager;
import de.tud.model.medication.DummyMedication;
import de.tud.model.medication.Medication;
import de.tud.model.medication.Unit;
import de.tud.view.medicationPlan.MedicationPlanUIModel;
import de.tud.view.medicationPlan.MedicationPlanView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MedicationPlanController {

    private MedicationPlanView medicationPlanView;
    private static MedicationPlanManager medicationPlanManager;

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

    public void addSafeButtonListener(){
        medicationPlanView.getSaveMedicationFormButton().addClickListener((Button.ClickListener) clickEvent -> {
            if(checkIntegrityConditions()){
                if(safeMedicationPlanEntry()){
                    Notification.show("Eintrag erfolgreich gespeichert");
                }
                else{
                    Notification.show("Eintrag speichern fehlgeschlagen");
                }
            }

        });
    }

    private boolean checkIntegrityConditions(){
        if(medicationPlanView.getIdTextField().isEmpty()){
            Notification.show("Bitte ID eingeben");
            return false;
        }
        else if(medicationPlanView.getUnitComboBox().isEmpty()){
            Notification.show("Bitte Einheit angeben");
            return false;
        }
        else{
            return true;
        }
    }

    private boolean safeMedicationPlanEntry(){
        Medication medication = new Medication();
        medication.setDmId(medicationPlanView.getIdTextField().getValue());
        medication.setMorningDosage(medicationPlanView.getStepperMorning().getValue());
        medication.setNoonDosage(medicationPlanView.getStepperNoon().getValue());
        medication.setAfternoonDosage(medicationPlanView.getStepperAfternoon().getValue());
        medication.setNightDosage(medicationPlanView.getStepperNight().getValue());
        medication.setHints(medicationPlanView.getHintsTextField().getValue());
        medication.setReason(medicationPlanView.getReasonTextField().getValue());
        medication.setUnit((Unit) medicationPlanView.getUnitComboBox().getValue());
        medicationPlanManager.create(medication);

        loadMedicationPlan();
        return true;
    }

    public static void remove(MedicationPlanUIModel medicationPlanUIModel){
        medicationPlanManager.delete(medicationPlanUIModel.getMedication().getId());
        Notification.show("Eintrag erfolgreich gelöscht");
    }

}
