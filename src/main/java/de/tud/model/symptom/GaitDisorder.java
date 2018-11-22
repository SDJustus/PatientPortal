package de.tud.model.symptom;

import javax.persistence.Entity;

@Entity
public class GaitDisorder extends Symptom {
    protected GaitDisorder(Strength strength) {
        super(strength);
    }

    public GaitDisorder(){

    }
    @Override
    public String toString() {return "Gehstörung: " + this.getStrength();}
}
