package de.tud.Model.symptom;

import javax.persistence.Entity;

@Entity
public class CognitiveDisorder extends Symptom {

    protected CognitiveDisorder(Strength strength) {
        super(strength);
    }

    @Override
    public String toString() {
        return "Kognitive Störung: " + this.getStrength();
    }
}
