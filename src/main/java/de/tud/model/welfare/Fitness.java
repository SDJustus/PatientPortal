package de.tud.model.welfare;
import javax.persistence.Entity;

@Entity
public class Fitness extends Welfare {

    protected Fitness() {
    }

    public Fitness(Strength strength) {
        super(strength);
    }

    @Override
    public String toString() {
        return "Konzentrationsfähigkeit: " + this.getStrength();   //TODO: Konzentrationsfähigkeit in Fitness?
    }
}