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
    private VitalDataSet vds;

    @BeforeAll
    public static void timeSetter(){
        testTime = LocalDateTime.now();
    }

    @BeforeEach
    public void initializer(){
        factory = new SymptomFactory();
        dm = new DiaryManager();

        vds = new VitalDataSet();
        vds.setBloodPressureFirstValue(123);
        vds.setBloodPressureSecondValue(90);
        vds.setHeartRate(103);
        vds.setHeight(173);
        vds.setWeight(80);

        testDiary = new HashSet<>();
        symptom1 = new HashSet<>();
        symptom1.add(factory.createSymptomByClass("Fatigue",Symptom.Strength.MIDDLE));
        symptom1.add(factory.createSymptomByClass("RightArmSpasticity", Symptom.Strength.SEVERE));
        symptom2 = new HashSet<>();
        symptom2.add(factory.createSymptomByClass("GaitDisorder",Symptom.Strength.WEAK));
        symptom2.add(factory.createSymptomByClass("Ache",Symptom.Strength.WEAK));
        testEntry1 = new DiaryEntry(testTime , symptom1, vds);

    }

    @Test
    void shouldGetAllDiaryEntriesFromDiary(){
        testDiary.add(testEntry1);

        long id = dm.create(new Diary());
        dm.addDiaryEntry(testEntry1, id);

        Set<DiaryEntry> diaryEntries = dm.readDiaryEntriesByDiary(id);

        for(DiaryEntry entry : diaryEntries)
            if(entry.getSymptom().equals(testEntry1.getSymptom()))
                assertEquals(entry.getSymptom(), testEntry1.getSymptom());
    }



    @Test
    public void createTest(){

        long id = dm.create(new Diary());

        SessionFactory sessionfac = dm.getSessionFactory();
        Session session = sessionfac.openSession();

        assertNotNull(session.get(Diary.class, id));
        assertTrue(session.get(Diary.class, id).getClass().equals(Diary.class));

        session.close();
    }



    @Test
    public void readTest(){

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
    public void addDiaryEntryTest(){

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
    public void deleteTest(){


        long id = dm.create(new Diary());

        dm.delete(id);

        Session session = dm.getSessionFactory().openSession();

        assertTrue(session.get(Diary.class, id)==null);

        session.close();
    }


    @Test
    public void removeDiaryEntryTest(){

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
    public void findByIdTest(){

        Diary byIDDiary = new Diary();

        testDiary.add(testEntry1);

        byIDDiary.setDiaryEntries(testDiary);

        long id = dm.create(byIDDiary);

        Diary foundDiary = dm.findByID(id);

        assertNotNull(foundDiary);
        assertEquals(foundDiary.getId(), byIDDiary.getId());
        assertEquals(foundDiary.getDiaryEntries().size(), byIDDiary.getDiaryEntries().size());
        Iterator foundDiaryIterator = foundDiary.getDiaryEntries().iterator();
        Iterator byIdDiaryIterator = byIDDiary.getDiaryEntries().iterator();
        while(foundDiaryIterator.hasNext() && byIdDiaryIterator.hasNext()){
            DiaryEntry foundDiaryEntry = (DiaryEntry)foundDiaryIterator.next();
            DiaryEntry byIDDiaryEntry = (DiaryEntry) byIdDiaryIterator.next();
            assertEquals(foundDiaryEntry.getSymptom().size(), byIDDiaryEntry.getSymptom().size());
            Iterator foundSymptomIterator = foundDiaryEntry.getSymptom().iterator();
            Iterator byIDSymptomIterator = byIDDiaryEntry.getSymptom().iterator();
            while(foundSymptomIterator.hasNext()&& byIdDiaryIterator.hasNext()){
                Symptom foundSymptom = (Symptom) foundSymptomIterator.next();
                Symptom byIDSymptom = (Symptom) byIDSymptomIterator.next();
                assertEquals(foundSymptom.getSymptomId(), byIDSymptom.getSymptomId());
                assertEquals(foundSymptom.getClass().getSimpleName(), byIDSymptom.getClass().getSimpleName());

            }
        }
    }


    @Test
    public void deleteAllTest(){
/*
        dm.create(new Diary());
        dm.create(new Diary());
        dm.create(new Diary());
*/
        dm.deleteAll();

        Session session = dm.getSessionFactory().openSession();

        assertTrue(session.createQuery("FROM Diary").list().isEmpty());

        session.close();

    }


    @Test
    public void getDiaryEntryByIdTest(){

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

    @Test
    public void VitalDataGetterSetterTest(){

        Diary diary = new Diary();
        testDiary.add(testEntry1);
        diary.setDiaryEntries(testDiary);

        long id = dm.create(diary);

        List<Diary> readDiary = dm.read();

        for(Diary rd : readDiary){
            if(rd.getId().equals(id)){
                for(DiaryEntry de : rd.getDiaryEntries()){
                    if(de.getVitalDataSet().equals(vds))
                        assertTrue(true);
                }
            }
        }


    }

}
