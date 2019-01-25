package de.tud.model.welfare;
import javax.persistence.Entity;

@Entity
public class Sleep extends Welfare {

    /**
     * Empty constructor of Sleep.
     */
    protected Sleep() {
    }

    /**
     * Constructor of Sleep.
     * @param strength
     */
    public Sleep(Strength strength) {
        super(strength);
    }

    /**
     * Returns a message containing the welfare name and its Strength value.
     * @return
     */
    @Override
    public String toString() {
        return "Schlaf: " + this.getStrength();   //TODO: Konzentrationsfähigkeit in Sleep?
    }
}