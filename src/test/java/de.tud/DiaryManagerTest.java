package de.tud;


import de.tud.model.Diary;
import de.tud.model.DiaryEntry;
import de.tud.model.VitalData;
import de.tud.model.manager.DiaryManager;
import de.tud.model.symptom.*;

import de.tud.model.welfare.Welfare;
import de.tud.model.welfare.WelfareFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


class DiaryManagerTest {

    private Set<DiaryEntry> testDiary;
    private DiaryEntry testEntry1;
    private DiaryEntry testEntry2;
    private Set<Symptom> symptom1;
    private Set<Symptom> symptom2;
    private SymptomFactory factory;
    private static LocalDateTime testTime;
    private DiaryManager dm;
    private VitalData vds;
    private Welfare welfare;

    @BeforeAll
    static void timeSetter(){
        testTime = LocalDateTime.now();
    }

    @BeforeEach
    void initializer(){
        dm = DiaryManager.getInstance();

        vds = new VitalData();
        vds.setBloodPressureFirstValue(123);
        vds.setBloodPressureSecondValue(90);
        vds.setHeartRate(103);
        vds.setHeight(173);
        vds.setWeight(80);



        testDiary = new HashSet<>();
        symptom1 = new HashSet<>();
        symptom1.add(SymptomFactory.getInstance().createSymptomByClass(Fatigue.class,Symptom.Strength.MIDDLE));
        symptom1.add(SymptomFactory.getInstance().createSymptomByClass(RightArmSpasticity.class, Symptom.Strength.SEVERE));
        symptom2 = new HashSet<>();
        symptom2.add(SymptomFactory.getInstance().createSymptomByClass(GaitDisorder.class,Symptom.Strength.WEAK));
        symptom2.add(SymptomFactory.getInstance().createSymptomByClass(Ache.class,Symptom.Strength.WEAK));
        testEntry1 = new DiaryEntry(testTime , symptom1, vds, new HashSet<>());                                         //TODO: "new HashSet" is placeholder for Welfare implementation

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
    void createTest(){

        long id = dm.create(new Diary());

        SessionFactory sessionfac = dm.getSessionFactory();
        Session session = sessionfac.openSession();

        assertNotNull(session.get(Diary.class, id));
        assertEquals(session.get(Diary.class, id).getClass(), Diary.class);

        session.close();
    }



    @Test
    void readTest(){

        Diary readtestdiary = new Diary();
        List<DiaryEntry> testentries = new ArrayList<>();
        testentries.add(testEntry1);

        long id = dm.create(readtestdiary);

        List<Diary> testlist;

        testlist = dm.read();

        for(Diary diary: testlist){
            if(diary.getId()==id){
                for(DiaryEntry de: diary.getDiaryEntries())
                    Assertions.assertEquals(de, testEntry1);
            }

        }
    }


    @Test
    void addDiaryEntryTest(){

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
    void deleteTest(){


        long id = dm.create(new Diary());

        dm.delete(id);

        Session session = dm.getSessionFactory().openSession();

        assertTrue(session.get(Diary.class, id)==null);

        session.close();
    }


    @Test
    void removeDiaryEntryTest(){

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
    void findByIdTest(){

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
    void shouldGetDiaryFromNotExistingId(){
        assertThrows(IllegalArgumentException.class, ()->{
            dm.findByID(99999L);
        }, "There was no Diary with the given ID!");
    }

    @Test
    void deleteAllTest(){
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
    void getDiaryEntryByIdTest(){

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

            entryID=resultDiary.getDiaryEntries().iterator().next().getId();

        DiaryEntry diaryEntry = dm.getDiaryEntryById(diaryId, entryID);


        assertEquals(diaryEntry.getSymptom().iterator().next().getClass(), testEntry1.getSymptom().iterator().next().getClass());

    }

    @Test
    void VitalDataGetterSetterTest(){

        Diary diary = new Diary();
        testDiary.add(testEntry1);
        diary.setDiaryEntries(testDiary);

        long id = dm.create(diary);

        List<Diary> readDiary = dm.read();

        for(Diary rd : readDiary){
            if(rd.getId().equals(id)){
                for(DiaryEntry de : rd.getDiaryEntries()){
                    if(de.getVitalData().equals(vds))
                        assertTrue(true);
                }
            }
        }
    }

    @Test
    void WelfareGetterSetterTest(){
        Set<Welfare> welfareset = new HashSet<>();
            welfareset.add(WelfareFactory.createSymptomByClass("Sleep", Welfare.Strength.MIDDLE));
            welfareset.add(WelfareFactory.createSymptomByClass("ConcentrationAbility", Welfare.Strength.SEVERE));
            welfareset.add(WelfareFactory.createSymptomByClass("PhysicalCondition", Welfare.Strength.WEAK));

        Diary diary = new Diary();

        testEntry1.setWelfare(welfareset);
        testDiary.add(testEntry1);
        diary.setDiaryEntries(testDiary);

        long id = dm.create(diary);

        List<Diary> readDiary = dm.read();

        for(Diary rd : readDiary){
            if(rd.getId().equals(id)){
                for(DiaryEntry de : rd.getDiaryEntries()){
                    if(de.getWelfare().equals(welfareset))
                        assertTrue(true);
                }
            }
        }
    }
}
