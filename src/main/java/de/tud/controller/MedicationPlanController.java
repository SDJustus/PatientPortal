package de.tud.controller;

import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.components.grid.ItemClickListener;
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
            medicationPlanView.getStepperId().setEnabled(true);
            if(checkIntegrityConditions()){
                if(safeMedicationPlanEntry()){
                    Notification.show("Eintrag erfolgreich gespeichert");
                    clearInputFields();
                    medicationPlanView.getDelete().setEnabled(false);
                }
                else{
                    Notification.show("Eintrag speichern fehlgeschlagen");
                }
            }

        });
    }


    public void addDeleteButtonListener(){
        medicationPlanView.getDelete().addClickListener((Button.ClickListener) clickEvent -> {
            List<Medication> medications = medicationPlanManager.read();
            for(Medication x : medications){
                if((long)medicationPlanView.getStepperId().getValue() == x.getDmId()){
                    medicationPlanManager.delete(x.getId());
                    Notification.show("Eintrag erfolgreich gelöscht");
                }
            }
            loadMedicationPlan();
            clearInputFields();
            medicationPlanView.getDelete().setEnabled(false);
            medicationPlanView.getStepperId().setEnabled(true);
        });
    }

    private boolean checkIntegrityConditions(){
        if(medicationPlanView.getStepperId().getValue()==0){
            Notification.show("Bitte ID eingeben");
            return false;
        }
        else if(medicationPlanView.getUnitComboBox().isEmpty()){
            Notification.show("Bitte Einheit angeben");
            return false;
        }
        else if(checkIfDmIdAllreadyExists(medicationPlanView.getStepperId().getValue())){
            Notification.show("Medikation existiert bereits");
            return false;
        }
        else if(!checkIfDmIdExists(medicationPlanView.getStepperId().getValue())){
            Notification.show("Dieses Medikament existiert nicht");
            return false;
        }
        else if(medicationPlanView.getStepperMorning().getValue()==0 &&
                medicationPlanView.getStepperNoon().getValue()==0 &&
                medicationPlanView.getStepperAfternoon().getValue()==0 &&
                medicationPlanView.getStepperNight().getValue()==0){
            Notification.show("Bitte geben Sie eine Dodierung an");
            return false;
        }
        else{
            return true;
        }
    }

    private boolean checkIfDmIdAllreadyExists(int dmID){
        List<Medication> medications = medicationPlanManager.read();
        for(Medication x: medications){
            if(dmID == x.getDmId()){
                return true;
            }
        }
        return false;
    }
    private boolean checkIfDmIdExists(int dmID){
        List<DummyMedication> dummyMedications = medicationPlanManager.getAllDummyMedication();
        for(DummyMedication x : dummyMedications){
            if(x.getId() == dmID){
                return true;
            }
        }
        return false;
    }

    private boolean safeMedicationPlanEntry(){
        Medication medication = new Medication();
        medication.setDmId(medicationPlanView.getStepperId().getValue());
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

    public void addDoubleClickListenerToGrid(){
        medicationPlanView.getMedicationPlanGrid().addItemClickListener((ItemClickListener) itemClick -> {
            if(itemClick.getMouseEventDetails().isDoubleClick()){
                if(medicationPlanView.getStepperId().getValue()>0){
                    Notification.show("Bitte den bearbeiteten Eintrag erst speichern!");
                }
                else{
                    editItemFromDoubleClick((MedicationPlanUIModel) itemClick.getItem());
                    medicationPlanManager.delete(((MedicationPlanUIModel) itemClick.getItem()).getMedication().getId());
                    medicationPlanView.getDelete().setEnabled(true);
                    medicationPlanView.getStepperId().setEnabled(false);
                }
            }
        });
    }

    public void editItemFromDoubleClick(MedicationPlanUIModel medicationPlanUIModel){
        medicationPlanView.getStepperId().setValue((int) medicationPlanUIModel.getMedication().getDmId());
        medicationPlanView.getUnitComboBox().setValue(medicationPlanUIModel.getMedication().getUnit());
        medicationPlanView.getStepperMorning().setValue(medicationPlanUIModel.getMedication().getMorningDosage());
        medicationPlanView.getStepperNoon().setValue(medicationPlanUIModel.getMedicationNoonDosage());
        medicationPlanView.getStepperAfternoon().setValue(medicationPlanUIModel.getMedicationAfternoonDosage());
        medicationPlanView.getHintsTextField().setValue(medicationPlanUIModel.getMedication().getHints());
        medicationPlanView.getReasonTextField().setValue(medicationPlanUIModel.getMedication().getReason());
    }

    public void clearInputFields(){
        medicationPlanView.getStepperId().setValue(0);
        medicationPlanView.getUnitComboBox().clear();
        medicationPlanView.getStepperMorning().setValue(0f);
        medicationPlanView.getStepperNoon().setValue(0f);
        medicationPlanView.getStepperAfternoon().setValue(0f);
        medicationPlanView.getStepperNight().setValue(0f);
        medicationPlanView.getReasonTextField().clear();
        medicationPlanView.getHintsTextField().clear();
    }

    public int getDummyMedicationCount(){
        return medicationPlanManager.getAllDummyMedication().size();
    }

}
