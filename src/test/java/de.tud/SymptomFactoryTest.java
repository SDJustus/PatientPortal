package de.tud;


import de.tud.model.symptom.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SymptomFactoryTest {

    @Test
    void shouldReturnCorrectClass(){
        assertTrue(SymptomFactory.createSymptomByClass("Müdigkeit", Symptom.Strength.SEVERE) instanceof Fatigue);
    }

    @Test
    void shouldReturnCorrectClassnameAsString(){
        assertTrue(SymptomFactory.createSymptomByClass(("Spastik im linken Arm"), Symptom.Strength.MIDDLE) instanceof LeftArmSpasticity);
        assertTrue(SymptomFactory.createSymptomByClass(("Schmerzen"), Symptom.Strength.MIDDLE) instanceof Ache);
        assertTrue(SymptomFactory.createSymptomByClass(("Blasenstörung"), Symptom.Strength.MIDDLE) instanceof BladderDisorder);
        assertTrue(SymptomFactory.createSymptomByClass(("Darmstörung"), Symptom.Strength.MIDDLE) instanceof BowelDisorder);
        assertTrue(SymptomFactory.createSymptomByClass(("Kognitive Störung"), Symptom.Strength.MIDDLE) instanceof CognitiveDisorder);
        assertTrue(SymptomFactory.createSymptomByClass(("Depression"), Symptom.Strength.MIDDLE) instanceof Depression);
        assertTrue(SymptomFactory.createSymptomByClass(("Müdigkeit"), Symptom.Strength.MIDDLE) instanceof Fatigue);
        assertTrue(SymptomFactory.createSymptomByClass(("Spastik im rechten Arm"), Symptom.Strength.MIDDLE) instanceof RightArmSpasticity);
        assertTrue(SymptomFactory.createSymptomByClass(("Spastik im linken Bein"), Symptom.Strength.MIDDLE) instanceof LeftLegSpasticity);
        assertTrue(SymptomFactory.createSymptomByClass(("Spastik im rechten Bein"), Symptom.Strength.MIDDLE) instanceof RightLegSpasticity);
        assertTrue(SymptomFactory.createSymptomByClass(("Gehstörung"), Symptom.Strength.MIDDLE) instanceof GaitDisorder);
    }
}
