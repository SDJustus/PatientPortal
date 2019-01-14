package de.tud.model.welfare;

import java.util.logging.Level;
import java.util.logging.Logger;

public class WelfareFactory {

    private static final Logger logger = Logger.getLogger(WelfareFactory.class.getName());


    private static class WelfareFactoryInstance
    {
        private static final WelfareFactory INSTANCE = new WelfareFactory();
    }

    /**
     * Returns the instance of the WelfareFactory.
     * @return
     */
    public static WelfareFactory getInstance()
    {
        return WelfareFactoryInstance.INSTANCE;
    }

    /**
     * Constructor of WelfareFactory.
     */
    private WelfareFactory(){

    }
    @Deprecated
    public static Welfare createSymptomByClass(String className, Welfare.Strength strength) {
        switch (className) {
            case "PhysicalCondition":
                return new PhysicalCondition(strength);
            case "Sleep":
                return new Sleep(strength);
            case "ConcentrationAbility":
                return new ConcentrationAbility(strength);
            default:
                throw new IllegalArgumentException("can't create Symptom from className!" + className);
        }

    }

    /**
     * Returns a desired Welfare object fitting the delivered welfare name.
     * @param className
     * @param strength
     * @return
     */
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