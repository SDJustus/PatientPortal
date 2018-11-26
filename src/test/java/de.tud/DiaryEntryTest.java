package de.tud;

import de.tud.model.DiaryEntry;
import de.tud.model.VitalData;
import de.tud.model.symptom.Depression;
import de.tud.model.symptom.Fatigue;
import de.tud.model.symptom.Symptom;
import de.tud.model.symptom.SymptomFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

class DiaryEntryTest {

    private DiaryEntry testEntry1;
    private Set<Symptom> symptomSet;
    private Set<DiaryEntry> testDiaryEntrySet;
    private static LocalDateTime testTime;

    private VitalData vitalData;

    @BeforeEach
    void setUp() {

    testTime =LocalDateTime.of(2018,10,11,12,10);
    symptomSet =new HashSet<>();
    vitalData =new VitalData();


            symptomSet.add(SymptomFactory.getInstance().createSymptomByClass(Depression .class,Symptom.Strength.WEAK));
        symptomSet.add(SymptomFactory.getInstance().createSymptomByClass(Fatigue .class,Symptom.Strength.SEVERE));
    testEntry1 =new DiaryEntry(testTime, symptomSet, vitalData);

    testDiaryEntrySet =new HashSet<>();
            testDiaryEntrySet.add(testEntry1);

}

    @Test
    void shouldGetAllSymptoms(){

        Set<Symptom> symptoms = testEntry1.getSymptom();
        boolean bool = false;
        for (Symptom symptom : symptoms){

            if (symptom.getClass().equals(Fatigue.class)){
                bool = true;
                break;
            }
        }
        Assertions.assertTrue(bool);
    }

    @Test
    void shouldGetDateOfDiaryEntry(){

        LocalDateTime localDateTime = LocalDateTime.of(2018,10,11,12,10);
        Assertions.assertEquals(testEntry1.getDate().toString(), localDateTime.toString());
    }

    @Test
    void shouldGetVitalDataFromDiaryEntry(){

        vitalData.setBloodPressureFirstValue(80);
        vitalData.setBloodPressureSecondValue(120);
        vitalData.setHeartRate(110);
        vitalData.setHeight(190);
        vitalData.setWeight(70);

        Assertions.assertEquals(vitalData.getHeight(), 190);
        Assertions.assertEquals(vitalData.getWeight(), 70);
        Assertions.assertEquals(vitalData.getHeartRate(), 110);
        Assertions.assertEquals(vitalData.getBloodPressureSecondValue(), 120);
        Assertions.assertEquals(vitalData.getBloodPressureFirstValue(), 80);

    }
}
