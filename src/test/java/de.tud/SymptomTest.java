package de.tud;

import de.tud.model.symptom.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SymptomTest {

    @Test
    void symptomToStringTest(){


        assertEquals("Spastik im linken Arm: Mittel", SymptomFactory.createSymptomByClass("Spastik im linken Arm", Symptom.Strength.MIDDLE).toString());
        assertEquals("Schmerzen: Mittel", SymptomFactory.createSymptomByClass("Schmerzen", Symptom.Strength.MIDDLE).toString());
        assertEquals("Blasenstörung: Mittel", SymptomFactory.createSymptomByClass("Blasenstörung", Symptom.Strength.MIDDLE).toString());
        assertEquals("Darmstörung: Mittel", SymptomFactory.createSymptomByClass("Darmstörung", Symptom.Strength.MIDDLE).toString());
        assertEquals("Kognitive Störung: Mittel", SymptomFactory.createSymptomByClass("Kognitive Störung", Symptom.Strength.MIDDLE).toString());
        assertEquals("Depression: Stark", SymptomFactory.createSymptomByClass("Depression", Symptom.Strength.SEVERE).toString());
        assertEquals("Müdigkeit: Mittel", SymptomFactory.createSymptomByClass("Müdigkeit", Symptom.Strength.MIDDLE).toString());
        assertEquals("Spastik im rechten Arm: Mittel", SymptomFactory.createSymptomByClass("Spastik im rechten Arm", Symptom.Strength.MIDDLE).toString());
        assertEquals("Spastik im linken Bein: Mittel", SymptomFactory.createSymptomByClass("Spastik im linken Bein", Symptom.Strength.MIDDLE).toString());
        assertEquals("Spastik im rechten Bein: Schwach", SymptomFactory.createSymptomByClass("Spastik im rechten Bein", Symptom.Strength.WEAK).toString());
        assertEquals("Gehstörung: Mittel", SymptomFactory.createSymptomByClass("Gehstörung", Symptom.Strength.MIDDLE).toString());
    }

}
