package de.tud.Model.symptom;

import javax.persistence.Entity;

@Entity
public class Fatigue extends Symptom {
    protected Fatigue(Strength strength) {
        super(strength);
    }

    @Override
    public String toString() {
        return "Fatigue: " + this.getStrength();
    }
}
