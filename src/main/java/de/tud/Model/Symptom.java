package de.tud.Model;

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
