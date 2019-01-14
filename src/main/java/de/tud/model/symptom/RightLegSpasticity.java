package de.tud.model.symptom;

import javax.persistence.Entity;

@Entity
public class RightLegSpasticity extends Symptom {

    protected RightLegSpasticity(Strength strength) {
        super(strength);
    }

    /**
     * Constructor of RightLegSpasticity.
     */
    public RightLegSpasticity(){


    }
    /**
     * Returns a message containing the name of the symptom and its Strength value.
     * @return
     */
    @Override
    public String toString() {
        return "Spastik im rechten Bein: " + this.getStrength();
    }
}
