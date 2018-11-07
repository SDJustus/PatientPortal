package de.tud.Model;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
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


    protected Symptom (Strength strength)
    {
        this.strength = strength;
    }

    @Override
    public String toString(){
        return getClass().getSimpleName() + ": " + strength;
    }






}
