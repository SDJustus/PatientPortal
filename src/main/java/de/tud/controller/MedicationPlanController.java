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

    /**
     * holds MedicationPlanView object - for UI
     */
    private MedicationPlanView medicationPlanView;
    /**
     * holds MedicationPlanManager
     */
    private static MedicationPlanManager medicationPlanManager;
    /**
     * holds MedicationPlanUIModel adapter objects for MediationPlan Grid
     */
    private Set<MedicationPlanUIModel> medicationPlanEntityList;

    /**
     * Constructor of MedicationPlanController
     * @param medicationPlanView
     */
    public MedicationPlanController(MedicationPlanView medicationPlanView){

        this.medicationPlanView = medicationPlanView;
        medicationPlanManager = MedicationPlanManager.getInstance();
    }
    /**
     * loads Medication Entries from Medication Database Table
     * creates adapter class entities for all Medication Entries
     * filly adapter entities with DummyMedication Data according to the Medication Entries inside
     * provides Items for the MedicationPlan Grid in form of MedicationPlanUIModel adapter objects
     */
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

    /**
     * Adds a ButtonClickListener to the Safe Button
     * When called, enables the StepperID field to end the edit process
     */
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

    /**
     * deletes the medication that is currently in edit mode - deletes all Medications with this DummyMedicationID
     * updates MedicationPlan grid and clears Input fields
     * When called, deactivates the delete Button and activates the StepperID field to end the edit process
     */
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
    /**
     * checks integrity conditions for Safe Action
     * checks: ID not 0, Unit not Null, DmID not already in use, DmId exists, at least one dosage field is filled
     * @return
     */
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
    /**
     * checks in Medication Table if DmID already in use in at least one medication entry
     * @param dmID
     * @return
     */
    private boolean checkIfDmIdAllreadyExists(int dmID){
        List<Medication> medications = medicationPlanManager.read();
        for(Medication x: medications){
            if(dmID == x.getDmId()){
                return true;
            }
        }
        return false;
    }
    /**
     * checks if DmId exists in DummyMedication Table
     * @param dmID
     * @return
     */
    private boolean checkIfDmIdExists(int dmID){
        List<DummyMedication> dummyMedications = medicationPlanManager.getAllDummyMedication();
        for(DummyMedication x : dummyMedications){
            if(x.getId() == dmID){
                return true;
            }
        }
        return false;
    }
    /**
     * creates new Medication Entry and calls for MedicationPlan Grid update
     * should check if medication entry is correctly written to Database, not implemented yet due to time limitation
     * @return
     */
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
    /**
     * adds ItemClickListener to MedicationPlan Grid - checks for double click event
     * checks if there is currently an item in edit mode (an DmId is set in the StepperID Field)
     * on double click deletes selected item to ensure that there is only one Item for each DummyMedication in the Database
     * calls editItemFromDoubleClick and proceeds the clicked item
     * enables Delete button
     * blocks the StepperID field to ensure that the ID will not be changed during the edit process
     */
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
    /**
     * fills edit form with item values - provided by addDoubleClickListenerToGrid() Method
     */
    public void editItemFromDoubleClick(MedicationPlanUIModel medicationPlanUIModel){
        medicationPlanView.getStepperId().setValue((int) medicationPlanUIModel.getMedication().getDmId());
        medicationPlanView.getUnitComboBox().setValue(medicationPlanUIModel.getMedication().getUnit());
        medicationPlanView.getStepperMorning().setValue(medicationPlanUIModel.getMedication().getMorningDosage());
        medicationPlanView.getStepperNoon().setValue(medicationPlanUIModel.getMedicationNoonDosage());
        medicationPlanView.getStepperAfternoon().setValue(medicationPlanUIModel.getMedicationAfternoonDosage());
        medicationPlanView.getHintsTextField().setValue(medicationPlanUIModel.getMedication().getHints());
        medicationPlanView.getReasonTextField().setValue(medicationPlanUIModel.getMedication().getReason());
    }
    /**
     * clears all edit form fields
     */
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
    /**
     * returns the count of Dummy Medications in the Dummy Medication Database
     * @return
     */
    public int getDummyMedicationCount(){
        return medicationPlanManager.getAllDummyMedication().size();
    }

}
