package de.tud.model.symptom;

import javax.persistence.Entity;

@Entity
public class CognitiveDisorder extends Symptom {

    protected CognitiveDisorder(Strength strength) {
        super(strength);
    }

    public CognitiveDisorder(){

    }
    @Override
    public String toString() {
        return "Kognitive Störung: " + this.getStrength();
    }
}
