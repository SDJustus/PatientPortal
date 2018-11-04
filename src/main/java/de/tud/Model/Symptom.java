package de.tud.Model;

public abstract class Symptom {

    public enum Strength{
        WEAK, MIDDLE, SEVERE
    }

   private Strength strength;


    protected Symptom (Strength strength)
    {
        this.strength = strength;


    }






}
