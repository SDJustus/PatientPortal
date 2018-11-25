package de.tud;


import de.tud.model.symptom.Fatigue;
import de.tud.model.symptom.Symptom;
import de.tud.model.symptom.SymptomFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SymptomFactoryTest {

    @Test
    void shouldReturnCorrectClass(){
        assertTrue(SymptomFactory.getInstance().createSymptomByClass(Fatigue.class, Symptom.Strength.SEVERE) instanceof Fatigue);
    }
}
