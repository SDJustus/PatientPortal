package de.tud.model.welfare;
import javax.persistence.Entity;

@Entity
public class PhysicalCondition extends Welfare {

    protected PhysicalCondition() {
    }

    public PhysicalCondition(Strength strength) {
        super(strength);
    }

    @Override
    public String toString() {
        return "Kondition: " + this.getStrength();   //TODO: Konzentrationsfähigkeit in Sleep?
    }
}