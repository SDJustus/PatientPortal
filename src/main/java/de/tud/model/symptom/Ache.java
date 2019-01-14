package de.tud.model.symptom;

import javax.persistence.Entity;

@Entity
public class Ache extends Symptom {

    protected Ache(Strength strength) {
        super(strength);
    }

    /**
     * Constructor of Ache.
     */
    public Ache(){

    }
    /**
     * Returns a message containing the name of the symptom and its Strength value.
     * @return
     */
    @Override
    public String toString() {
        return "Schmerzen: " + this.getStrength();
    }
}
