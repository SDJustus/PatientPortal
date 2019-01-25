package de.tud.model.welfare;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Welfare {

    /**
     * Defines the possible Strength values of a Welfare object.
     */
    public enum Strength{
        WEAK{
            @Override
            public String toString() {
                return "Gut";
            }
        }, MIDDLE{
            @Override
            public String toString() {
                return "Mäßig";
            }
        }, SEVERE{
            @Override
            public String toString() {
                return "Schlecht";
            }
        }
    }

    /**
     * Holds the Strength value of the Welfare object.
     */
    @Enumerated(EnumType.STRING)
    private Strength strength;

    /**
     * Holds the ID of the persistent Welfare object.
     */
    @Id
    @SequenceGenerator(name = "WelfareGenerator", sequenceName = "WelfareSequence", allocationSize = 1)
    @GeneratedValue(generator = "WelfareGenerator")
    @Column (name = "welfare_id")
    protected long welfareId;

    /**
     * Empty constructor of Welfare.
     */
    public Welfare(){}

    /**
     * Constructor of Welfare.
     * @param strength
     */
    protected Welfare(Strength strength){
        this.strength=strength;
    }

    /**
     * Returns a message containing the welfare name and its Strength value.
     * @return
     */
    @Override
    public String toString(){
        return getClass().getSimpleName()+": "+strength.toString();
    }

    /**
     * Returns the Strength value of the Welfare object.
     * @return
     */
    public Strength getStrength() {
        return strength;
    }

    /**
     * Sets the Strength value of the Welfare object.
     */
    public void setStrength(Strength strength) {
        this.strength = strength;
    }

    /**
     * Returns the ID of the persistent Welfare object.
     * @return
     */
    public long getWelfareId() {
        return welfareId;
    }

    /**
     * Sets the ID of the Welfare object.
     * @param welfareId
     */
    public void setWelfareId(long welfareId) {
        this.welfareId = welfareId;
    }
}
