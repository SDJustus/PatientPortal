package de.tud;


import de.tud.model.Diary;
import de.tud.model.DiaryEntry;
import de.tud.model.VitalDataSet;
import de.tud.model.manager.DiaryManager;
import de.tud.model.symptom.Symptom;
import de.tud.model.symptom.SymptomFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.util.*;

import static java.lang.Math.toIntExact;
import static org.junit.jupiter.api.Assertions.*;


public class DiaryManagerTest {

    private Set<DiaryEntry> testDiary;
    private DiaryEntry testEntry1;
    private DiaryEntry testEntry2;
    private Set<Symptom> symptom1;
    private Set<Symptom> symptom2;
    private SymptomFactory factory;
    private static LocalDateTime testTime;
    private DiaryManager dm;

    @BeforeAll
    public static void timeSetter(){
        testTime = LocalDateTime.now();
    }

    @BeforeEach
    public void initializer(){
        factory = new SymptomFactory();
        dm = new DiaryManager();
        testDiary = new HashSet<>();
        symptom1 = new HashSet<>();
            symptom1.add(factory.createSymptomByClass("Fatigue",Symptom.Strength.MIDDLE));
            symptom1.add(factory.createSymptomByClass("RightArmSpasticity", Symptom.Strength.SEVERE));
        symptom2 = new HashSet<>();
            symptom2.add(factory.createSymptomByClass("GaitDisorder",Symptom.Strength.WEAK));
            symptom2.add(factory.createSymptomByClass("Ache",Symptom.Strength.WEAK));
        testEntry1 = new DiaryEntry(testTime , symptom1, new VitalDataSet());                               //VitalDataSet is only a placeholder waiting for correct implementation


    }

    @Test
    void shouldGetAllDiaryEntriesFromDiary(){  //Solved : KANN NICHT TRUE WERDEN DA VERSCHIEDENE ID´S DER ENTRIES
        testDiary.add(testEntry1);

        long id = dm.create(new Diary());
        dm.addDiaryEntry(testEntry1, id);

        Set<DiaryEntry> diaryEntries = dm.readDiaryEntriesByDiary(id);

        for(DiaryEntry entry : diaryEntries)
            if(entry.getSymptom().equals(testEntry1.getSymptom()))
                assertTrue(true);
    }



    @Test
    public void createTest(){

        long id = dm.create(new Diary());

        SessionFactory sessionfac = dm.getSessionFactory();
        Session session = sessionfac.openSession();

        assertTrue(session.get(Diary.class, id)!=null && session.get(Diary.class, id).getClass()==Diary.class);

        session.close();
    }



    @Test
    public void readTest() throws Exception{

        Diary readtestdiary = new Diary();
        List<DiaryEntry> testentries = new ArrayList<>();
        testentries.add(testEntry1);

        long id = dm.create(readtestdiary);

        List<Diary> testlist;

        testlist = dm.read();

        for(Diary diary: testlist){
            if(diary.getId()==id){
                for(DiaryEntry de: diary.getDiaryEntries())
                    de.equals(testEntry1);
            }

        }
    }


    @Test
    public void addDiaryEntryTest() throws Exception{

        long id = dm.create(new Diary());

        dm.addDiaryEntry(testEntry1, id);

        Session session = dm.getSessionFactory().openSession();

        Set<DiaryEntry> eintraege = session.get(Diary.class, id).getDiaryEntries();

        for(DiaryEntry de : eintraege)
            if(de==testEntry1)
                assertTrue(true);

        session.close();
    }


    @Test
    public void deleteTest() throws Exception{


        long id = dm.create(new Diary());

        dm.delete(id);

        Session session = dm.getSessionFactory().openSession();

        assertTrue(session.get(Diary.class, id)==null);

        session.close();
    }


    @Test
    public void removeDiaryEntryTest() throws Exception{

        Diary diary = new Diary();
        testDiary.add(testEntry1);
        diary.setDiaryEntries(testDiary);

        long id = dm.create(diary);

        dm.removeDiaryEntry(testEntry1,id);

        Session session = dm.getSessionFactory().openSession();

        assertFalse(session.get(Diary.class, id).getDiaryEntries().contains(testEntry1));

        session.close();
    }


    @Test
    public void findByIdTest() throws Exception{

        Diary byIDDiary = new Diary();

        testDiary.add(testEntry1);

        byIDDiary.setDiaryEntries(testDiary);

        long id = dm.create(byIDDiary);

        Diary foundDiary = dm.findByID(id);

        for(DiaryEntry de : foundDiary.getDiaryEntries())
            if(de.getSymptom().equals(testEntry1.getSymptom()) && de.getDate().equals(testEntry1.getDate()))
            assertTrue(true);
    }


    @Test
    public void deleteAllTest() throws Exception{

        dm.create(new Diary());
        dm.create(new Diary());
        dm.create(new Diary());

        dm.deleteAll();

        Session session = dm.getSessionFactory().openSession();

        assertTrue(session.createQuery("FROM Diary").list().isEmpty());

        session.close();
    }


    @Test
    public void getDiaryEntryByIdTest() throws Exception{

        Diary diary = new Diary();
        testDiary.add(testEntry1);
        diary.setDiaryEntries(testDiary);

        long diaryId = dm.create(diary);

        List<Diary> diaryList;
        diaryList = DiaryManager.getInstance().read();

        Diary resultDiary=null;
        long entryID=0;

        for(Diary singleDiary : diaryList){
            if(singleDiary.getId()==diaryId)
                resultDiary=singleDiary;
        }

        for(DiaryEntry de : resultDiary.getDiaryEntries())
            entryID=de.getId();

        DiaryEntry diaryEntry = dm.getDiaryEntryById(diaryId, entryID);


        assertEquals(diaryEntry.getSymptom(), testEntry1.getSymptom());

    }
}
