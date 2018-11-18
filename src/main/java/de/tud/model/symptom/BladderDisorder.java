package de.tud.model.symptom;

import javax.persistence.Entity;

@Entity
public class BladderDisorder extends Symptom{

    protected BladderDisorder(Strength strength) {
        super(strength);
    }

    public BladderDisorder(){

    }
    @Override
    public String toString() {
        return "Blasenstörung: " + this.getStrength();
    }
}
