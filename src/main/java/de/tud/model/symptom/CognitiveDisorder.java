package de.tud.model.symptom;

import javax.persistence.Entity;

@Entity
public class CognitiveDisorder extends Symptom {

    protected CognitiveDisorder(Strength strength) {
        super(strength);
    }

    /**
     * Constructor of CognitiveDisorder.
     */
    public CognitiveDisorder(){

    }
    /**
     * Returns a message containing the name of the symptom and its Strength value.
     * @return
     */
    @Override
    public String toString() {
        return "Kognitive Störung: " + this.getStrength();
    }
}
