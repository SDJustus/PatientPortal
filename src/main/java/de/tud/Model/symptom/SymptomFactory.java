package de.tud.Model;

public class SymptomFactory {
    //Müdigkeit,
    //Gehstörung,	kognitive
    //Störung,	Schmerzen,
    //Blasenstörung,	Spastik,
    //Depression,
    //Darmstörung
    public static Symptom createSymptomByClass(String className, Symptom.Strength strength){
            switch(className){
                case "Depression":
                    return new Depression(strength);
                case "Fatigue":
                    
            }

        }


}
