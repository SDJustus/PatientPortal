package de.tud;

import de.tud.model.symptom.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SymptomTest {

    @Test
    public  void symptomToStringTest(){


        assertTrue(SymptomFactory.getInstance().createSymptomByClass("Spastik im linken Arm", Symptom.Strength.MIDDLE).toString().equals("Spastik im linken Arm: Mittel") );
        assertTrue(SymptomFactory.getInstance().createSymptomByClass("Schmerzen", Symptom.Strength.MIDDLE).toString().equals("Schmerzen: Mittel") );
        assertTrue(SymptomFactory.getInstance().createSymptomByClass("Blasenstörung", Symptom.Strength.MIDDLE).toString().equals("Blasenstörung: Mittel") );
        assertTrue(SymptomFactory.getInstance().createSymptomByClass("Darmstörung", Symptom.Strength.MIDDLE).toString().equals("Darmstörung: Mittel") );
        assertTrue(SymptomFactory.getInstance().createSymptomByClass("Kognitive Störung", Symptom.Strength.MIDDLE).toString().equals("Kognitive Störung: Mittel") );
        assertTrue(SymptomFactory.getInstance().createSymptomByClass("Depression", Symptom.Strength.SEVERE).toString().equals("Depression: Stark") );
        assertTrue(SymptomFactory.getInstance().createSymptomByClass("Müdigkeit", Symptom.Strength.MIDDLE).toString().equals("Müdigkeit: Mittel") );
        assertTrue(SymptomFactory.getInstance().createSymptomByClass("Spastik im rechten Arm", Symptom.Strength.MIDDLE).toString().equals("Spastik im rechten Arm: Mittel") );
        assertTrue(SymptomFactory.getInstance().createSymptomByClass("Spastik im linken Bein", Symptom.Strength.MIDDLE).toString().equals("Spastik im linken Bein: Mittel") );
        assertTrue(SymptomFactory.getInstance().createSymptomByClass("Spastik im rechten Bein", Symptom.Strength.WEAK).toString().equals("Spastik im rechten Bein: Schwach") );
        assertTrue(SymptomFactory.getInstance().createSymptomByClass("Gehstörung", Symptom.Strength.MIDDLE).toString().equals("Gehstörung: Mittel") );
    }

}
