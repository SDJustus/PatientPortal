package de.tud;


import de.tud.model.Diary;
import de.tud.model.DiaryEntry;
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
            symptom1.add(factory.createSymptomByClass("Spasticity", Symptom.Strength.SEVERE));
        symptom2 = new HashSet<>();
            symptom2.add(factory.createSymptomByClass("GaitDisorder",Symptom.Strength.WEAK));
            symptom2.add(factory.createSymptomByClass("Ache",Symptom.Strength.WEAK));
        testEntry1 = new DiaryEntry(testTime , symptom1);


    }

    @Test
    void shouldGetAllDiaryEntriesFromDiary(){
        long id = dm.create(new Diary());
        dm.addDiaryEntry(testEntry1, id);

        Set<DiaryEntry> diaryEntries = dm.readDiaryEntriesByDiary(id);

        assertEquals(diaryEntries.toString(), " ");
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

        long id = dm.create(new Diary());

        List<Diary> testlist;

        testlist = dm.read();

        for(Diary diary: testlist){
            if(diary.getId()==id)
                assertTrue(true);
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

        Diary testdiary = new Diary();

        testDiary.add(testEntry1);

        testdiary.setDiaryEntries(testDiary);

        long id = dm.create(new Diary());

        assertNotNull(dm.findByID(id));
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

        Session session = dm.getSessionFactory().openSession();

        long diaryId = dm.create(diary);

        Set<DiaryEntry> entries = session.get(Diary.class, diaryId).getDiaryEntries();

        Iterator<DiaryEntry> iter = entries.iterator();

        long entryId = iter.next().getId();

         DiaryEntry diaryEntry = dm.getDiaryEntryById(diaryId, entryId);

        assertEquals(testEntry1 ,diaryEntry);

        session.close();
    }
}
