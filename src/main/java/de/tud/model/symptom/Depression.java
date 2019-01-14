package de.tud.model.symptom;

import javax.persistence.Entity;

@Entity
public class Depression extends Symptom {

    public Depression(Strength s) {
        super(s);

    }
    /**
     * Constructor of Depression.
     */
    public Depression(){

    }
    /**
     * Returns a message containing the name of the symptom and its Strength value.
     * @return
     */
    @Override
    public String toString() {
        return "Depression: " + this.getStrength();
    }

}
