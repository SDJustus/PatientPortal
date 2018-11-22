package de.tud.model.symptom;

import javax.persistence.Entity;

@Entity
public class LeftLegSpasticity extends Symptom {
    protected LeftLegSpasticity(Strength strength) {
        super(strength);
    }

    public LeftLegSpasticity(){

    }
    @Override
    public String toString() {
        return "Spastik im linken Bein: " + this.getStrength();
    }
}
