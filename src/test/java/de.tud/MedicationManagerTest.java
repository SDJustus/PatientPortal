package de.tud;

import de.tud.model.manager.MedicationPlanManager;
import de.tud.model.medication.DummyMedication;
import de.tud.model.medication.Medication;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ejb.AfterCompletion;
import java.util.List;

public class MedicationManagerTest {

    private MedicationPlanManager medicationPlanManager;
    @BeforeEach
    public void setUp(){
        medicationPlanManager = MedicationPlanManager.getInstance();
    }
    @AfterCompletion
    public void tearDown(){
        medicationPlanManager.deleteAll();
    }

    @Test
    public void testGetAllDummyMedications(){
        List<DummyMedication> dummyMedications = medicationPlanManager.getAllDummyMedication();
        Assertions.assertFalse(dummyMedications.isEmpty());
        Assertions.assertTrue(dummyMedications.size()==5);
    }
    @Test
    public void testIsIncompatibleWith(){
        List<DummyMedication> dummyMedications = medicationPlanManager.getAllDummyMedication();
        Medication med1 = new Medication();
        Medication med2 = new Medication();
        med1.setAmount(33.1);
        med1.setDmId(1);
        med1.setDosage("morgens");
        med2.setAmount(33.1);
        med2.setDmId(5);
        med2.setDosage("morgens");

        long id1 = medicationPlanManager.create(med1);
        long id2 = medicationPlanManager.create(med2);

        Assertions.assertTrue(medicationPlanManager.isIncompatibleWith(4L));


    }
}
