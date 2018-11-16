package de.tud.model.symptom;

import javax.persistence.Entity;

@Entity
public class Spasticity extends Symptom {

    protected Spasticity(Strength strength) {
        super(strength);
    }

    public Spasticity(){

    }
    @Override
    public String toString() {
        return "Spastik: " + this.getStrength();
    }
}
