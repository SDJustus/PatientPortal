package de.tud.Model.symptom;

import javax.persistence.Entity;

@Entity
public class Depression extends Symptom {

    public Depression(Strength s)
    {
        super(s);

    }

    @Override
    public String toString() {
        return "Depression: " + this.getStrength();
    }

}
