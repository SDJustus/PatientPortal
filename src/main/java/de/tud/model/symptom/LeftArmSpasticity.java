package de.tud.model.symptom;

import javax.persistence.Entity;

@Entity
public class LeftArmSpasticity extends Symptom {

    protected LeftArmSpasticity(Strength strength) {
        super(strength);
    }

    public LeftArmSpasticity(){

    }
    @Override
    public String toString() {
        return "Spastik im linken Arm: " + this.getStrength();
    }
}
