package de.tud.Model.symptom;

import javax.persistence.Entity;

@Entity
public class BowelDisorder extends Symptom {

    protected BowelDisorder(Strength strength) {
        super(strength);
    }

    @Override
    public String toString() {
        return "Darmstörung: " + this.getStrength();
    }
}
