package de.tud.Model.symptom;

public class Ache extends Symptom {

    protected Ache(Strength strength) {
        super(strength);
    }

    @Override
    public String toString() {
        return "Schmerzen: " + this.getStrength();
    }
}
