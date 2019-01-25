package de.tud;

import de.tud.model.manager.MedicationPlanManager;
import de.tud.model.medication.DummyMedication;
import de.tud.model.medication.Medication;
import de.tud.model.medication.Unit;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MedicationManagerTest {

    private MedicationPlanManager medicationPlanManager;
    private Medication med1;
    private Medication med2;
    @BeforeEach
    void setUp(){
        medicationPlanManager = MedicationPlanManager.getInstance();
        med1 = new Medication();
        med2 = new Medication();
        med1.setDmId(1);
        med1.setMorningDosage(331.11f);
        med1.setAfternoonDosage(3.4f);
        med1.setNightDosage(41f);
        med1.setNoonDosage(1f);
        med1.setHints("This is a hint");
        med1.setUnit(Unit.PIECES);
        med1.setReason("This is a reason");
        med2.setDmId(5);
        med2.setMorningDosage(1.11f);
        med2.setAfternoonDosage(5.4f);
        med2.setNightDosage(30f);
        med2.setNoonDosage(1.402f);
        med2.setHints("This is a second hint");
        med2.setUnit(Unit.MG);
        med2.setReason("This is a second reason");
    }

    @AfterAll
    void tearDown(){
        medicationPlanManager.deleteAll();
        medicationPlanManager.create(med1);
        medicationPlanManager.create(med2);
    }

    @Test
    void createDummyMedicationIfNotExisting(){
        Session session = MedicationPlanManager.getInstance().getSessionFactory().openSession();
        List<DummyMedication> dummyMedications = session.createQuery("FROM DummyMedication").list();
        session.close();
        if(dummyMedications.isEmpty()){
            DummyMedication dummyMedication1 = new DummyMedication(1,	"Metoprololsuccinat",
                    "Metoprololsuccinat 1A",	"95mg","Tablette","4");
            DummyMedication dummyMedication2 = new DummyMedication(2,	"Ramipril",
                    "Ramipril-ratiopharm",	"5mg","Tablette","1,3");
            DummyMedication dummyMedication3 = new DummyMedication(3,	"Insulin aspart",
                    "NovoRapid Penfill",	"100 E/ml","Lösung","1,2");
            DummyMedication dummyMedication4 = new DummyMedication(4,	"Simvastatin",
                    "Simva-Aristo",	"40 mg","Tablette","2,3,5");
            DummyMedication dummyMedication5 = new DummyMedication(5,	"Fentanyl",
                    "Fentanyl AbZ 75 µg/h",	"2.375 mg","Pflaster","2,4");
            Session session2 = MedicationPlanManager.getInstance().getSessionFactory().openSession();
            session2.beginTransaction();
            session2.save(dummyMedication1);
            session2.save(dummyMedication2);
            session2.save(dummyMedication3);
            session2.save(dummyMedication4);
            session2.save(dummyMedication5);
            session2.getTransaction().commit();
            dummyMedications = session2.createQuery("FROM DummyMedication").list();
            session.close();
        }

        Assertions.assertFalse(dummyMedications.isEmpty());

    }

    @Test
    void testCreate(){
        Long id = medicationPlanManager.create(med1);
        Session session = MedicationPlanManager.getInstance().getSessionFactory().openSession();
        Medication returnedMed = session.get(Medication.class, id);
        session.close();
        Assertions.assertEquals(med1.getMorningDosage(), returnedMed.getMorningDosage());
    }

    @Test
    void testDelete(){
        Long medId1 = medicationPlanManager.create(med1);
        Long medId2 = medicationPlanManager.create(med2);
        medicationPlanManager.delete(medId1);
        Assertions.assertEquals(medicationPlanManager.findByID(medId2).getClass(), Medication.class);
        Assertions.assertThrows(IllegalArgumentException.class, () -> medicationPlanManager.findByID(medId1),
                "There was no Medication with the given ID!");
    }

    @Test
    void testGetDummyMedicationsByMedicationId(){
        Long medId1 = medicationPlanManager.create(med1);
        Long medId2 = medicationPlanManager.create(med2);
        DummyMedication dummyMedication = medicationPlanManager.getDummyMedicationByMedicationId(medId1);
        Assertions.assertEquals(dummyMedication.getId(), new Long(1));
        Assertions.assertEquals(medicationPlanManager.getDummyMedicationByMedicationId(medId2).getId(), new Long(5));

    }
    @Test
    void testGetAllDummyMedications(){
        List<DummyMedication> dummyMedications = medicationPlanManager.getAllDummyMedication();
        Assertions.assertFalse(dummyMedications.isEmpty());
        Assertions.assertEquals(5, dummyMedications.size());
    }
    @Test
    void testIsIncompatibleWith(){
        medicationPlanManager.create(med1);
        medicationPlanManager.create(med2);

        Assertions.assertTrue(medicationPlanManager.isIncompatibleWith(4L));


    }
    @Test
    void testDeleteAll(){
        medicationPlanManager.create(med1);
        medicationPlanManager.deleteAll();
        Assertions.assertTrue(medicationPlanManager.read().isEmpty());
    }
}
