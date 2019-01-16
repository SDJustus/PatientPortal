package de.tud.view.medicationPlan;

import de.tud.model.medication.DummyMedication;
import de.tud.model.medication.Medication;


public class MedicationPlanUIModel {

    private Medication medication;
    private DummyMedication dummyMedication;

    public MedicationPlanUIModel(Medication medication, DummyMedication dummyMedication) {
        this.medication = medication;
        this.dummyMedication = dummyMedication;
    }

    public Medication getMedication() {
        return medication;
    }

    public void setMedication(Medication medication) {
        this.medication = medication;
    }

    public DummyMedication getDummyMedication() {
        return dummyMedication;
    }

    public void setDummyMedication(DummyMedication dummyMedication) {
        this.dummyMedication = dummyMedication;
    }

    public long getDummyMedicationID(){
        return dummyMedication.getId();
    }

    public String getDummyMedicationTradeName(){
        return dummyMedication.getTradeName();
    }

    public String getDummyMedicationSubstance(){
        return dummyMedication.getSubstance();
    }

    public String getDummyMedicationStrength(){
        return dummyMedication.getStrength();
    }

    public String getDummyMedicationForm(){
        return dummyMedication.getForm();
    }

    public float getMedicationMorningDosage(){return medication.getMorningDosage();}
    public float getMedicationNoonDosage(){return medication.getNoonDosage();}
    public float getMedicationAfternoonDosage(){return medication.getAfternoonDosage();}
    public float getMedicationNightDosage(){return medication.getNightDosage();}
    public String getMedicationHints(){return medication.getHints();}
    public String getMedicationReason(){return medication.getReason();}

}
