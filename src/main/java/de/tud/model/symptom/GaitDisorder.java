package de.tud.model.symptom;

import javax.persistence.Entity;

@Entity
public class GaitDisorder extends Symptom {
    protected GaitDisorder(Strength strength) {
        super(strength);
    }

    @Override
    public String toString() {
        return null;
    }
}