package de.tud.model.welfare;
import javax.persistence.Entity;

@Entity
public class Sleep extends Welfare {

    protected Sleep() {
    }

    public Sleep(Strength strength) {
        super(strength);
    }

    @Override
    public String toString() {
        return "Konzentrationsfähigkeit: " + this.getStrength();   //TODO: Konzentrationsfähigkeit in Sleep?
    }
}