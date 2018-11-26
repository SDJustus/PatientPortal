package de.tud.model.welfare;

import java.util.logging.Level;
import java.util.logging.Logger;

public class WelfareFactory {

    private static final Logger logger = Logger.getLogger(WelfareFactory.class.getName());

    /**
     * The class to get the singleton instance.
     */
    private static class WelfareFactoryInstance
    {
        private static final WelfareFactory INSTANCE = new WelfareFactory();
    }

    /**
     * @return the singleton instance of this factory.
     */
    public static WelfareFactory getInstance()
    {
        return WelfareFactoryInstance.INSTANCE;
    }

    private WelfareFactory(){

    }
    @Deprecated
    public static Welfare createSymptomByClass(String className, Welfare.Strength strength) {
        switch (className) {
            case "Fitness":
                return new Fitness(strength);
            case "Sleep":
                return new Sleep(strength);
            case "ConcentrationAbility":
                return new ConcentrationAbility(strength);
            default:
                throw new IllegalArgumentException("can't create Symptom from className!" + className);
        }

    }
    public Welfare createSymptomByClass(Class<? extends Welfare> className, Welfare.Strength strength) {
        try {
            Welfare welfare = className.newInstance();
            welfare.setStrength(strength);
            return welfare;
        } catch (InstantiationException e) {
            logger.log(Level.SEVERE, "an");
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }

    }
}