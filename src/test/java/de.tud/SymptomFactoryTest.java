package de.tud;


import de.tud.model.symptom.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SymptomFactoryTest {

    @Test
    void shouldReturnCorrectClass(){
        assertTrue(SymptomFactory.getInstance().createSymptomByClass(Fatigue.class, Symptom.Strength.SEVERE) instanceof Fatigue);
    }

    @Test
    void shouldReturnCorrectClassnameAsString(){
        assertTrue(SymptomFactory.getInstance().createSymptomByClass(("Spastik im linken Arm"), Symptom.Strength.MIDDLE) instanceof LeftArmSpasticity);
        assertTrue(SymptomFactory.getInstance().createSymptomByClass(("Schmerzen"), Symptom.Strength.MIDDLE) instanceof Ache);
        assertTrue(SymptomFactory.getInstance().createSymptomByClass(("Blasenstörung"), Symptom.Strength.MIDDLE) instanceof BladderDisorder);
        assertTrue(SymptomFactory.getInstance().createSymptomByClass(("Darmstörung"), Symptom.Strength.MIDDLE) instanceof BowelDisorder);
        assertTrue(SymptomFactory.getInstance().createSymptomByClass(("Kognitive Störung"), Symptom.Strength.MIDDLE) instanceof CognitiveDisorder);
        assertTrue(SymptomFactory.getInstance().createSymptomByClass(("Depression"), Symptom.Strength.MIDDLE) instanceof Depression);
        assertTrue(SymptomFactory.getInstance().createSymptomByClass(("Müdigkeit"), Symptom.Strength.MIDDLE) instanceof Fatigue);
        assertTrue(SymptomFactory.getInstance().createSymptomByClass(("Spastik im rechten Arm"), Symptom.Strength.MIDDLE) instanceof RightArmSpasticity);
        assertTrue(SymptomFactory.getInstance().createSymptomByClass(("Spastik im linken Bein"), Symptom.Strength.MIDDLE) instanceof LeftLegSpasticity);
        assertTrue(SymptomFactory.getInstance().createSymptomByClass(("Spastik im rechten Bein"), Symptom.Strength.MIDDLE) instanceof RightLegSpasticity);
        assertTrue(SymptomFactory.getInstance().createSymptomByClass(("Gehstörung"), Symptom.Strength.MIDDLE) instanceof GaitDisorder);
    }
}
