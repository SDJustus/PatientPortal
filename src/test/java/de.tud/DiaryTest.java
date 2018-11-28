package de.tud;

import de.tud.model.Diary;
import de.tud.model.DiaryEntry;
import de.tud.model.VitalData;
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
    private VitalData vitalData;


    @BeforeEach
    void initializer(){
        testTime = LocalDateTime.now();
        symptomSet = new HashSet<>();
        vitalData = new VitalData();
        vitalData.setBloodPressureFirstValue(80);
        vitalData.setBloodPressureSecondValue(120);
        vitalData.setHeartRate(110);
        vitalData.setHeight(190);
        vitalData.setWeight(70);

            symptomSet.add(SymptomFactory.getInstance().createSymptomByClass(Depression.class, Symptom.Strength.WEAK));
        symptomSet = new HashSet<>();
        symptomSet.add(SymptomFactory.getInstance().createSymptomByClass(Fatigue.class, Symptom.Strength.SEVERE));
        testEntry1 = new DiaryEntry(testTime, symptomSet, vitalData, new HashSet<>());                                  //TODO: "new HashSet" is placeholder for Welfare implementation
        testEntry2 = new DiaryEntry(testTime, symptomSet2, vitalData, new HashSet<>());                                 //TODO: "new HashSet" is placeholder for Welfare implementation
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
