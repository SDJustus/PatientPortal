package de.tud.model.symptom;

import javax.persistence.Entity;

@Entity
public class RightLegSpasticity extends Symptom {

    protected RightLegSpasticity(Strength strength) {
        super(strength);
    }

    public RightLegSpasticity(){

    }
    @Override
    public String toString() {
        return "Spastik im Rechten Bein: " + this.getStrength();
    }
}
