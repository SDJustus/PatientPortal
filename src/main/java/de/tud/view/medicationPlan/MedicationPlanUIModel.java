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
}
