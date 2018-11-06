package de.tud.Model.symptom;

public class BladderDisorder extends Symptom{

    protected BladderDisorder(Strength strength) {
        super(strength);
    }

    @Override
    public String toString() {
        return "Blasenstörung: " + this.getStrength();
    }
}
