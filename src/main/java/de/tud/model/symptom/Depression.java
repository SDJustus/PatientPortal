package de.tud.model.symptom;

import javax.persistence.Entity;

@Entity
public class Depression extends Symptom {

    public Depression(Strength s) {
        super(s);

    }

    @Override
    public String toString() {
        return "Depression: " + this.getStrength();
    }

}
