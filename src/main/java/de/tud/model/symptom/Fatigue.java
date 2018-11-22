package de.tud.model.symptom;

import javax.persistence.Entity;

@Entity
public class Fatigue extends Symptom {
    protected Fatigue(Strength strength) {
        super(strength);
    }

    public Fatigue(){

    }
    @Override
    public String toString() {
        return "Müdigkeit: " + this.getStrength();
    }
}
