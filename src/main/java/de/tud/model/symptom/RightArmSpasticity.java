package de.tud.model.symptom;

import javax.persistence.Entity;

@Entity
public class RightArmSpasticity extends Symptom {

    protected RightArmSpasticity(Strength strength) {
        super(strength);
    }

    public RightArmSpasticity(){

    }
    @Override
    public String toString() {
        return "Spastik im rechten Arm: " + this.getStrength();
    }
}
