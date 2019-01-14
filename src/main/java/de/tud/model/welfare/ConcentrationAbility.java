package de.tud.model.welfare;


import javax.persistence.Entity;

@Entity
public class ConcentrationAbility extends Welfare{

    /**
     * Empty constructor of ConcentrationAbility.
     */
    protected ConcentrationAbility(){}

    /**
     * Constructor of ConcentrationAbility.
     * @param strength
     */
    public ConcentrationAbility(Strength strength){
        super(strength);
    }

    /**
     * Returns a message containing the welfare name and its Strength value.
     * @return
     */
    @Override
    public String toString() {
        return "Konzentrationsfähigkeit: " + this.getStrength();
    }
}
