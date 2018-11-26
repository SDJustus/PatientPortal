package de.tud.model.welfare;


import javax.persistence.Entity;

@Entity
public class ConcentrationAbility extends Welfare{

    protected ConcentrationAbility(){}

    public ConcentrationAbility(Strength strength){
        super(strength);
    }

    @Override
    public String toString() {
        return "Konzentrationsfähigkeit: " + this.getStrength();
    }
}
