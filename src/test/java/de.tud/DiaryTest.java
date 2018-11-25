package de.tud;

import de.tud.model.Diary;
import de.tud.model.DiaryEntry;
import de.tud.model.VitalDataSet;
import de.tud.model.symptom.Depression;
import de.tud.model.symptom.Fatigue;
import de.tud.model.symptom.Symptom;
import de.tud.model.symptom.SymptomFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


class DiaryTest {

    private DiaryEntry testEntry1;
    private DiaryEntry testEntry2;
    private Set<Symptom> symptomSet;
    private Set<Symptom> symptomSet2;
    private Set<DiaryEntry> testDiaryEntrySet;
    private Symptom symptom1;
    private SymptomFactory factory;
    private static LocalDateTime testTime;
    private Diary testDiary;
    private VitalDataSet vitalDataSet;


    @BeforeEach
    void initializer(){
        testTime = LocalDateTime.now();
        symptomSet = new HashSet<>();
        vitalDataSet = new VitalDataSet();
        vitalDataSet.setBloodPressureFirstValue(80);
        vitalDataSet.setBloodPressureSecondValue(120);
        vitalDataSet.setHeartRate(110);
        vitalDataSet.setHeight(190);
        vitalDataSet.setWeight(70);

            symptomSet.add(SymptomFactory.getInstance().createSymptomByClass(Depression.class, Symptom.Strength.WEAK));
        symptomSet = new HashSet<>();
        symptomSet.add(SymptomFactory.getInstance().createSymptomByClass(Fatigue.class, Symptom.Strength.SEVERE));
        testEntry1 = new DiaryEntry(testTime, symptomSet, vitalDataSet);
        testEntry2 = new DiaryEntry(testTime, symptomSet2, vitalDataSet);
        testDiaryEntrySet = new HashSet<>();
            testDiaryEntrySet.add(testEntry1);
            testDiaryEntrySet.add(testEntry2);
    }

    @Test
    void getDiaryEntriesTest(){
        testDiary = new Diary();
        testDiary.setDiaryEntries(testDiaryEntrySet);
        assertEquals(testDiary.getDiaryEntries(),testDiaryEntrySet);
    }
    @Test
    void getDiaryEntriesFromEmptyDiaryTest(){
        testDiary= new Diary();
        Set<DiaryEntry> testEntries = new HashSet<>();
        testDiary.setDiaryEntries(testEntries);
        assertEquals(testDiary.getDiaryEntries(),testEntries);
    }
}
