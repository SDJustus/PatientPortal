package de.tud.Model;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Symptom {

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
    @Enumerated(EnumType.STRING)
   private Strength strength;

    public long getSymptomId() {
        return symptomId;
    }

    public void setSymptomId(long symptomId) {
        this.symptomId = symptomId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column (name = "symptom_id")
    protected long symptomId;


    protected Symptom (Strength strength)
    {
        this.strength = strength;
    }

    @Override
    public String toString(){
        return getClass().getSimpleName() + ": " + strength;
    }






}
