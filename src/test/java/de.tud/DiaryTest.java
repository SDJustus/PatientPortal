package de.tud;

import de.tud.model.Diary;
import de.tud.model.DiaryEntry;
import de.tud.model.VitalDataSet;
import de.tud.model.symptom.Symptom;
import de.tud.model.symptom.SymptomFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


public class DiaryTest {

    private DiaryEntry testEntry1;
    private DiaryEntry testEntry2;
    private Set<Symptom> symptomSet;
    private Set<Symptom> symptomSet2;
    private Set<DiaryEntry> testDiaryEntrySet;
    private Symptom symptom1;
    private SymptomFactory factory;
    private static LocalDateTime testTime;
    private Diary testDiary;


    @BeforeEach
    public void initializer(){
        testTime = LocalDateTime.now();
        factory = new SymptomFactory();
        symptomSet = new HashSet<>();
            symptomSet.add(factory.createSymptomByClass("Depression", Symptom.Strength.WEAK));
        symptomSet = new HashSet<>();
        symptomSet.add(factory.createSymptomByClass("Müdigkeit", Symptom.Strength.SEVERE));
        testEntry1 = new DiaryEntry(testTime, symptomSet, new VitalDataSet());                          //TODO: Replace "new VitalDaraSet" - it is only a placeholder
        testEntry2 = new DiaryEntry(testTime, symptomSet2, new VitalDataSet());                         //TODO: Replace "new VitalDaraSet" - it is only a placeholder
        testDiaryEntrySet = new HashSet<>();
            testDiaryEntrySet.add(testEntry1);
            testDiaryEntrySet.add(testEntry2);
    }

    @Test
    public void getDiaryEntriesTest(){
        testDiary = new Diary();
        testDiary.setDiaryEntries(testDiaryEntrySet);
        assertEquals(testDiary.getDiaryEntries(),testDiaryEntrySet);
    }
    @Test
    public void getDiaryEntriesFromEmptyDiaryTest(){
        testDiary= new Diary();
        assertEquals(testDiary.getDiaryEntries(),new HashSet<>());
    }
}
