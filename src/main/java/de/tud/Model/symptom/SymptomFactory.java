package de.tud.Model.symptom;

public class SymptomFactory {

    public static Symptom createSymptomByClass(String className, Symptom.Strength strength) {
        switch (className) {
            case "Depression":
                return new Depression(strength);
            case "Fatigue":
                return new Fatigue(strength);

            default:
                throw new IllegalArgumentException("can't create Class from className!");
        }

    }
}