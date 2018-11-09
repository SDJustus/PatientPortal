package de.tud.model.symptom;

import javax.persistence.Entity;

@Entity
public class Ache extends Symptom {

    protected Ache(Strength strength) {
        super(strength);
    }

    @Override
    public String toString() {
        return "Schmerzen: " + this.getStrength();
    }
}
