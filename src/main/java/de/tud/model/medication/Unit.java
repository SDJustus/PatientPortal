package de.tud.model.medication;

/**
 * Determines the possible unit types of Medication.
 */
public enum Unit{
        MG{
                @Override
                public String toString(){return "Milligramm";
                }
        }, PIECES{
                @Override
                public String toString(){return "Stück";
                }
        }, ML{
                @Override
                public String toString(){return "Milliliter";}
        }
        }
