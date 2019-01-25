package de.tud.model.symptom;

import javax.persistence.Entity;

@Entity
public class RightArmSpasticity extends Symptom {

    protected RightArmSpasticity(Strength strength) {
        super(strength);
    }

    /**
     * Constructor of RightArmSpasticity.
     */
    public RightArmSpasticity(){


    }
    /**
     * Returns a message containing the name of the symptom and its Strength value.
     * @return
     */
    @Override
    public String toString() {
        return "Spastik im rechten Arm: " + this.getStrength();
    }
}
