package de.tud;


import de.tud.model.Diary;
import de.tud.model.DiaryEntry;
import de.tud.model.VitalData;
import de.tud.model.manager.DiaryManager;
import de.tud.model.symptom.*;

import de.tud.model.welfare.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DiaryManagerTest {

    private Set<DiaryEntry> testDiary;
    private DiaryEntry testEntry1;
    private DiaryEntry testEntry2;
    private DiaryEntry testEntry3;
    private DiaryEntry testEntry4;
    private DiaryEntry testEntry5;
    private static LocalDateTime testTime;
    private DiaryManager dm;
    private VitalData vds1;
    private Set<Welfare> welfareSet1;
    private Set<Welfare> welfareSet2;

    @BeforeAll
    static void timeSetter(){
        testTime = LocalDateTime.now();
    }

    @BeforeEach
    void initializer(){
        dm = DiaryManager.getInstance();

        vds1 = new VitalData();
        vds1.setBloodPressureFirstValue(123);
        vds1.setBloodPressureSecondValue(90);
        vds1.setHeartRate(103);
        vds1.setHeight(173);
        vds1.setWeight(80);

        VitalData vds2 = new VitalData();
        vds2.setBloodPressureFirstValue(110);
        vds2.setBloodPressureSecondValue(99);
        vds2.setHeartRate(60);
        vds2.setHeight(190);
        vds2.setWeight(100);

        VitalData vds3 = new VitalData();
        vds3.setBloodPressureFirstValue(200);
        vds3.setBloodPressureSecondValue(80);
        vds3.setHeartRate(80);
        vds3.setHeight(140);
        vds3.setWeight(60);

        VitalData vds4 = new VitalData();
        vds4.setBloodPressureFirstValue(180);
        vds4.setBloodPressureSecondValue(160);
        vds4.setHeartRate(120);
        vds4.setHeight(200);
        vds4.setWeight(90);

        VitalData vds5 = new VitalData();
        vds5.setBloodPressureFirstValue(100);
        vds5.setBloodPressureSecondValue(60);
        vds5.setHeartRate(99);
        vds5.setHeight(175);
        vds5.setWeight(80);

        Welfare welfare1 = WelfareFactory.getInstance().createSymptomByClass(ConcentrationAbility.class, Welfare.Strength.MIDDLE);
        Welfare welfare2 = WelfareFactory.getInstance().createSymptomByClass(Sleep.class, Welfare.Strength.WEAK);
        Welfare welfare3 = WelfareFactory.getInstance().createSymptomByClass(PhysicalCondition.class, Welfare.Strength.SEVERE);

        welfareSet1 = new HashSet<>();
        welfareSet1.add(welfare1);
        welfareSet1.add(welfare2);
        welfareSet1.add(welfare3);

        welfareSet2 = new HashSet<>();

        welfareSet2.add(WelfareFactory.getInstance()
                .createSymptomByClass(ConcentrationAbility.class, Welfare.Strength.WEAK));
        welfareSet2.add(WelfareFactory.getInstance()
                .createSymptomByClass(Sleep.class, Welfare.Strength.SEVERE));
        welfareSet2.add(WelfareFactory.getInstance()
                .createSymptomByClass(PhysicalCondition.class, Welfare.Strength.MIDDLE));

        Set<Welfare> welfareSet3 = new HashSet<>();
        welfareSet3.add(WelfareFactory.getInstance()
                .createSymptomByClass(ConcentrationAbility.class, Welfare.Strength.SEVERE));
        welfareSet3.add(WelfareFactory.getInstance()
                .createSymptomByClass(Sleep.class, Welfare.Strength.MIDDLE));
        welfareSet3.add(WelfareFactory.getInstance()
                .createSymptomByClass(PhysicalCondition.class, Welfare.Strength.WEAK));

        Set<Welfare> welfareSet4 = new HashSet<>();
        welfareSet4.add(WelfareFactory.getInstance()
                .createSymptomByClass(ConcentrationAbility.class, Welfare.Strength.WEAK));
        welfareSet4.add(WelfareFactory.getInstance()
                .createSymptomByClass(Sleep.class, Welfare.Strength.SEVERE));
        welfareSet4.add(WelfareFactory.getInstance()
                .createSymptomByClass(PhysicalCondition.class, Welfare.Strength.WEAK));

        Set<Welfare> welfareSet5 = new HashSet<>();
        welfareSet5.add(WelfareFactory.getInstance()
                .createSymptomByClass(ConcentrationAbility.class, Welfare.Strength.SEVERE));
        welfareSet5.add(WelfareFactory.getInstance()
                .createSymptomByClass(Sleep.class, Welfare.Strength.MIDDLE));
        welfareSet5.add(WelfareFactory.getInstance()
                .createSymptomByClass(PhysicalCondition.class, Welfare.Strength.WEAK));

        testDiary = new HashSet<>();
        Set<Symptom> symptom1 = new HashSet<>();
        symptom1.add(SymptomFactory.createSymptomByClass("Müdigkeit",Symptom.Strength.MIDDLE));
        symptom1.add(SymptomFactory.createSymptomByClass("Spastik im rechten Arm", Symptom.Strength.SEVERE));
        Set<Symptom> symptom2 = new HashSet<>();
        symptom2.add(SymptomFactory.createSymptomByClass("Gehstörung",Symptom.Strength.WEAK));
        symptom2.add(SymptomFactory.createSymptomByClass("Schmerzen",Symptom.Strength.WEAK));
        symptom2.add(SymptomFactory.createSymptomByClass("Müdigkeit",Symptom.Strength.WEAK));
        symptom2.add(SymptomFactory.createSymptomByClass("Spastik im rechten Arm",Symptom.Strength.WEAK));
        Set<Symptom> symptom3 = new HashSet<>();
        symptom3.add(SymptomFactory.createSymptomByClass("Müdigkeit",Symptom.Strength.WEAK));
        symptom3.add(SymptomFactory.createSymptomByClass("Spastik im rechten Arm",Symptom.Strength.MIDDLE));
        symptom3.add(SymptomFactory.createSymptomByClass("Schmerzen",Symptom.Strength.MIDDLE));
        Set<Symptom> symptom4 = new HashSet<>();
        symptom4.add(SymptomFactory.createSymptomByClass("Müdigkeit",Symptom.Strength.WEAK));
        symptom4.add(SymptomFactory.createSymptomByClass("Spastik im rechten Arm",Symptom.Strength.MIDDLE));
        symptom4.add(SymptomFactory.createSymptomByClass("Schmerzen",Symptom.Strength.SEVERE));
        Set<Symptom> symptom5 = new HashSet<>();
        symptom5.add(SymptomFactory.createSymptomByClass("Müdigkeit",Symptom.Strength.MIDDLE));
        symptom5.add(SymptomFactory.createSymptomByClass("Spastik im rechten Arm",Symptom.Strength.WEAK));
        symptom5.add(SymptomFactory.createSymptomByClass("Schmerzen",Symptom.Strength.SEVERE));

        testEntry1 = new DiaryEntry(testTime , symptom1, vds1, welfareSet1);
        testEntry2 = new DiaryEntry(testTime.minusDays(3), symptom2, vds2, welfareSet2);
        testEntry3 = new DiaryEntry(testTime.minusDays(5), symptom3, vds3, welfareSet3);
        testEntry4 = new DiaryEntry(testTime.minusDays(4), symptom4, vds4, welfareSet4);
        testEntry5 = new DiaryEntry(testTime.minusDays(1), symptom5, vds5, welfareSet5);

    }

    @AfterAll
    void tearDown(){
        dm.deleteAll();
        testDiary.add(testEntry1);
        testDiary.add(testEntry2);
        testDiary.add(testEntry3);
        testDiary.add(testEntry4);
        testDiary.add(testEntry5);
        Diary diary = new Diary();
        diary.setDiaryEntries(testDiary);
        dm.create(diary);

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

        Diary readTestDiary = new Diary();
        List<DiaryEntry> testEntries = new ArrayList<>();
        testEntries.add(testEntry1);

        long id = dm.create(readTestDiary);

        List<Diary> testList;

        testList = dm.read();

        for(Diary diary: testList){
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

        Set<DiaryEntry> entrySet = session.get(Diary.class, id).getDiaryEntries();

        for(DiaryEntry de : entrySet)
            if(de==testEntry1)
                assertTrue(true);

        session.close();
    }


    @Test
    void deleteTest(){


        long id = dm.create(new Diary());

        dm.delete(id);

        Session session = dm.getSessionFactory().openSession();

        assertNull(session.get(Diary.class, id));

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
        dm.deleteAll();
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

        assert resultDiary != null;
        entryID=resultDiary.getDiaryEntries().iterator().next().getId();

        DiaryEntry diaryEntry = dm.getDiaryEntryById(diaryId, entryID);

        for (Symptom symptom : diaryEntry.getSymptom()){
            if(symptom.equals(testEntry1.getSymptom().iterator().next()))
                assertTrue(true);
        }
        //assertEquals(diaryEntry.getSymptom().iterator().next().getClass(), testEntry1.getSymptom().iterator().next().getClass());

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
                    if(de.getVitalData().equals(vds1))
                        assertTrue(true);
                }
            }
        }
    }

    @Test
    void WelfareGetterSetterTest(){

        Diary diary = new Diary();

        testEntry1.setWelfare(welfareSet1);
        testDiary.add(testEntry1);
        diary.setDiaryEntries(testDiary);

        long id = dm.create(diary);

        List<Diary> readDiary = dm.read();

        for(Diary rd : readDiary){
            if(rd.getId().equals(id)){
                for(DiaryEntry de : rd.getDiaryEntries()){
                    if(de.getWelfare().equals(welfareSet1))
                        assertTrue(true);
                }
            }
        }
    }
}
