package de.tud.model.symptom;

public class SymptomFactory {


    public static Symptom createSymptomByClass(String className, Symptom.Strength strength) {
        switch (className) {
            case "Ache":
                return new Ache(strength);
            case "BladderDisorder":
                return new BladderDisorder(strength);
            case "BowelDisorder":
                return new BowelDisorder(strength);
            case "CognitiveDisorder":
                return new CognitiveDisorder(strength);
            case "Depression":
                return new Depression(strength);
            case "Fatigue":
                return new Fatigue(strength);
            case "GaitDisorder":
                return new GaitDisorder(strength);
            case "LeftLegSpasticity":
                return new LeftLegSpasticity(strength);
            case "RightLegSpasticity":
                return new RightLegSpasticity(strength);
            case "LeftArmSpasticity":
                return new LeftArmSpasticity(strength);
            case "RightArmSpasticity":
                return new RightArmSpasticity(strength);

            default:
                throw new IllegalArgumentException("can't create Symptom from className!" + className);
        }

    }
}