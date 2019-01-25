package de.tud.model.welfare;
import javax.persistence.Entity;

@Entity
public class PhysicalCondition extends Welfare {

    /**
     * Empty constructor of PhysicalCondition.
     */
    protected PhysicalCondition() {
    }

    /**
     * Constructor of PhysicalCondition.
     * @param strength
     */
    public PhysicalCondition(Strength strength) {
        super(strength);
    }

    /**
     * Returns a message containing the welfare name and its Strength value.
     * @return
     */
    @Override
    public String toString() {
        return "Kondition: " + this.getStrength();   //TODO: Konzentrationsfähigkeit in Sleep?
    }
}