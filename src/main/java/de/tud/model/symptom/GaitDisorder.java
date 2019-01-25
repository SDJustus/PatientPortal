package de.tud.model.symptom;

import javax.persistence.Entity;

@Entity
public class GaitDisorder extends Symptom {
    protected GaitDisorder(Strength strength) {
        super(strength);
    }

    /**
     * Constructor of GaitDisorder.
     */
    public GaitDisorder(){

    }
    /**
     * Returns a message containing the name of the symptom and its Strength value.
     * @return
     */
    @Override
    public String toString() {return "Gehstörung: " + this.getStrength();}
}
