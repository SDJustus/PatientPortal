package de.tud.model.symptom;

import javax.persistence.Entity;

@Entity
public class Fatigue extends Symptom {
    protected Fatigue(Strength strength) {
        super(strength);
    }

    /**
     * Constructor of Fatigue.
     */
    public Fatigue(){

    }

    /**
     * Returns a message containing the name of the symptom and its Strength value.
     * @return
     */
    @Override
    public String toString() {
        return "Müdigkeit: " + this.getStrength();
    }
}
