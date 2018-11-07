package de.tud.Model.symptom;

public class Spasticity extends Symptom {

    protected Spasticity(Strength strength) {
        super(strength);
    }

    @Override
    public String toString() {
        return "Spastik: " + this.getStrength();
    }
}
