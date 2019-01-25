package de.tud.model.symptom;

import javax.persistence.Entity;

@Entity
public class LeftArmSpasticity extends Symptom {

    protected LeftArmSpasticity(Strength strength) {
        super(strength);
    }

    /**
     * Constructor of LeftArmSpasticity.
     */
    public LeftArmSpasticity(){


    }
    /**
     * Returns a message containing the name of the symptom and its Strength value.
     * @return
     */
    @Override
    public String toString() {
        return "Spastik im linken Arm: " + this.getStrength();
    }
}
