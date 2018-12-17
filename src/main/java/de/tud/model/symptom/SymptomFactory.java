package de.tud.model.symptom;


import java.util.logging.Level;
import java.util.logging.Logger;

public class SymptomFactory {

    private static final Logger logger = Logger.getLogger(SymptomFactory.class.getName());

    /**
     * The class to get the singleton instance.
     */
    private static class SymptomFactoryInstance
    {
        private static final SymptomFactory INSTANCE = new SymptomFactory();
    }

    /**
     * @return the singleton instance of this factory.
     */
    public static SymptomFactory getInstance()
    {
        return SymptomFactoryInstance.INSTANCE;
    }

    private SymptomFactory(){

    }
    public static Symptom createSymptomByClass(String className, Symptom.Strength strength) {
        switch (className) {
            case "Schmerzen":
                return new Ache(strength);
            case "Blasenstörung":
                return new BladderDisorder(strength);
            case "Darmstörung":
                return new BowelDisorder(strength);
            case "Kognitive Störung":
                return new CognitiveDisorder(strength);
            case "Depression":
                return new Depression(strength);
            case "Müdigkeit":
                return new Fatigue(strength);
            case "Gehstörung":
                return new GaitDisorder(strength);
            case "Spastik im linken Bein":
                return new LeftLegSpasticity(strength);
            case "Spastik im rechten Bein":
                return new RightLegSpasticity(strength);
            case "Spastik im linken Arm":
                return new LeftArmSpasticity(strength);
            case "Spastik im rechten Arm":
                return new RightArmSpasticity(strength);

            default:
                throw new IllegalArgumentException("can't create Symptom from className!" + className);
        }

    }
}