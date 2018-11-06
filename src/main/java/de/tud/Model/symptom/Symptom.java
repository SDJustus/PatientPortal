package de.tud.Model.symptom;

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

    public Strength getStrength(){
        return strength;
    }

    public void setStrength(Strength strength){
        this.strength = strength;
    }

    @Override
    public abstract String toString();






}
