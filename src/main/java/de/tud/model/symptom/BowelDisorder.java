package de.tud.model.symptom;

import javax.persistence.Entity;

@Entity
public class BowelDisorder extends Symptom {

    protected BowelDisorder(Strength strength) {
        super(strength);
    }

    /**
     * Constructor of BowelDisorder.
     */
    public BowelDisorder(){

    }
    /**
     * Returns a message containing the name of the symptom and its Strength value.
     * @return
     */
    @Override
    public String toString() {
        return "Darmstörung: " + this.getStrength();
    }
}
