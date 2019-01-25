package de.tud.model.symptom;

import javax.persistence.Entity;

@Entity
public class LeftLegSpasticity extends Symptom {
    protected LeftLegSpasticity(Strength strength) {
        super(strength);
    }

    /**
     * Constructor of LeftLegSpasticity.
     */
    public LeftLegSpasticity(){


    }
    /**
     * Returns a message containing the name of the symptom and its Strength value.
     * @return
     */
    @Override
    public String toString() {
        return "Spastik im linken Bein: " + this.getStrength();
    }
}
