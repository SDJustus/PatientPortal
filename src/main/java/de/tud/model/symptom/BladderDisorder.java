package de.tud.model.symptom;

import javax.persistence.Entity;

@Entity
public class BladderDisorder extends Symptom{

    protected BladderDisorder(Strength strength) {
        super(strength);
    }

    /**
     * Constructor of BladderDisorder.
     */
    public BladderDisorder(){

    }
    /**
     * Returns a message containing the name of the symptom and its Strength value.
     * @return
     */
    @Override
    public String toString() {
        return "Blasenstörung: " + this.getStrength();
    }
}
