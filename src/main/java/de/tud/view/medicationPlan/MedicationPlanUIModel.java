package de.tud.view.medicationPlan;

import de.tud.model.medication.DummyMedication;
import de.tud.model.medication.Medication;
import de.tud.model.medication.Unit;

/**
 * Adapter Class for Medication and Dummy Medication
 * Because a Vaadin Grid can only store one datatype the two classes need to be merged into this adapter class
 */
public class MedicationPlanUIModel {

    /**
     * Holds the Medication Object
     */
    private Medication medication;
    /**
     * Holds the Dummy Medication Object
     */
    private DummyMedication dummyMedication;

    /**
     * Constructor of the Class MedicationPlanUIModel
     * @param medication
     * @param dummyMedication
     */
    public MedicationPlanUIModel(Medication medication, DummyMedication dummyMedication) {
        this.medication = medication;
        this.dummyMedication = dummyMedication;
    }

    /**
     * returns Medication object
     * @return
     */
    public Medication getMedication() {
        return medication;
    }

    /**
     * sets the Medication object
     * @param medication
     */
    public void setMedication(Medication medication) {
        this.medication = medication;
    }

    /**
     * returns the Dummy Medication object
     * @return
     */
    public DummyMedication getDummyMedication() {
        return dummyMedication;
    }

    /**
     * sets the Dummy Medication object
     * @param dummyMedication
     */
    public void setDummyMedication(DummyMedication dummyMedication) {
        this.dummyMedication = dummyMedication;
    }

    /**
     * returns the Dummy Medication ID
     * @return
     */
    public long getDummyMedicationID(){
        return dummyMedication.getId();
    }

    /**
     * returns the Dummy Medication Trade Name
     * @return
     */
    public String getDummyMedicationTradeName(){
        return dummyMedication.getTradeName();
    }

    /**
     * returns the Dummy Medication Substance
     * @return
     */
    public String getDummyMedicationSubstance(){
        return dummyMedication.getSubstance();
    }
    /**
     * returns the Dummy Medication Strength
     * @return
     */
    public String getDummyMedicationStrength(){
        return dummyMedication.getStrength();
    }
    /**
     * returns the Dummy Medication Form
     * @return
     */
    public String getDummyMedicationForm(){
        return dummyMedication.getForm();
    }

    /**
     * returns the Medication Morning Dosage
     * @return
     */
    public float getMedicationMorningDosage(){
        return medication.getMorningDosage();
    }
    /**
     * returns the Medication Noon Dosage
     * @return
     */
    public float getMedicationNoonDosage(){
        return medication.getNoonDosage();
    }
    /**
     * returns the Medication Afternoon Dosage
     * @return
     */
    public float getMedicationAfternoonDosage(){
        return medication.getAfternoonDosage();
    }
    /**
     * returns the Medication Night Dosage
     * @return
     */
    public float getMedicationNightDosage(){
        return medication.getNightDosage();
    }
    /**
     * returns the Medication Hints
     * @return
     */
    public String getMedicationHints(){
        return medication.getHints();
    }
    /**
     * returns the Medication Reason
     * @return
     */
    public String getMedicationReason(){
        return medication.getReason();
    }
    /**
     * returns the Medication Unit
     * @return
     */
    public Unit getMedicationUnit(){
        return medication.getUnit();
    }
}
