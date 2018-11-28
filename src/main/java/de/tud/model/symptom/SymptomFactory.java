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
@Deprecated
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
    public Symptom createSymptomByClass(Class<? extends Symptom> className, Symptom.Strength strength) {
        try {
            Symptom symptom = className.newInstance();
            symptom.setStrength(strength);
            return symptom;
        } catch (InstantiationException e) {
            logger.log(Level.SEVERE, "an");
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }

    }
}