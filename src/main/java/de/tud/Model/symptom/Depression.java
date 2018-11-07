package de.tud.Model.symptom;

import de.tud.Model.symptom.Symptom;

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
