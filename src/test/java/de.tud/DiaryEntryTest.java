package de.tud;

import de.tud.model.DiaryEntry;
import de.tud.model.VitalDataSet;
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

    private VitalDataSet vitalDataSet;

    @BeforeEach
    void setUp() {

    testTime =LocalDateTime.of(2018,10,11,12,10);
    symptomSet =new HashSet<>();
    vitalDataSet =new VitalDataSet();


            symptomSet.add(SymptomFactory.getInstance().createSymptomByClass(Depression .class,Symptom.Strength.WEAK));
        symptomSet.add(SymptomFactory.getInstance().createSymptomByClass(Fatigue .class,Symptom.Strength.SEVERE));
    testEntry1 =new DiaryEntry(testTime, symptomSet, vitalDataSet);

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

        vitalDataSet.setBloodPressureFirstValue(80);
        vitalDataSet.setBloodPressureSecondValue(120);
        vitalDataSet.setHeartRate(110);
        vitalDataSet.setHeight(190);
        vitalDataSet.setWeight(70);

        Assertions.assertEquals(vitalDataSet.getHeight(), 190);
        Assertions.assertEquals(vitalDataSet.getWeight(), 70);
        Assertions.assertEquals(vitalDataSet.getHeartRate(), 110);
        Assertions.assertEquals(vitalDataSet.getBloodPressureSecondValue(), 120);
        Assertions.assertEquals(vitalDataSet.getBloodPressureFirstValue(), 80);

    }
}
