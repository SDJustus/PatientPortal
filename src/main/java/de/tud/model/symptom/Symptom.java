package de.tud.model.symptom;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

public abstract class Symptom {

    /**
     * Defines the possible Strength values for a Symptom object.
     */
    public enum Strength{
        WEAK{
            @Override
            public String toString() {
                return "Schwach";
            }
        }, MIDDLE{
            @Override
            public String toString() {
                return "Mittel";
            }
        }, SEVERE{
            @Override
            public String toString() {
                return "Stark";
            }
        }
    }

    /**
     * Holds the Strength value of the Symptom object.
     */
    @Enumerated(EnumType.STRING)
   private Strength strength;

    /**
     * Returns the ID of the persistent Symptom object.
     * @return value of id
     */
    public long getSymptomId() {
        return symptomId;
    }

    /**
     * Sets the ID of the Symptom object.
     * @param symptomId
     */
    public void setSymptomId(long symptomId) {
        this.symptomId = symptomId;
    }

    /**
     * Holds the ID value of the persistent Symptom object.
     */
    @Id
    @SequenceGenerator(name = "SymptomGenerator", sequenceName = "SymptomSequence", allocationSize = 1)
    @GeneratedValue(generator = "SymptomGenerator")
    @Column (name = "symptom_id")
    protected long symptomId;


    /**
     * Constructor of Symptom.
     * @param strength
     */
    protected Symptom (Strength strength)
    {
        this.strength = strength;
    }

    /**
     * Returns the Strength value of the Symptom object.
     * @return
     */
    public Strength getStrength(){
        return strength;
    }

    /**
     * Sets the Strength value of the Symptom object.
     * @param strength
     */
    public void setStrength(Strength strength){
        this.strength = strength;
    }

    /**
     * Returns a message containing the name of the symptom and its Strength value.
     * @return
     */
    //TODO: adjust all toString-Methods
    @Override
    public String toString(){
       return getClass().getSimpleName()+": "+strength.toString();
    }

    /**
     * Empty constructor of Symptom.
     */
    public Symptom(){}



}
