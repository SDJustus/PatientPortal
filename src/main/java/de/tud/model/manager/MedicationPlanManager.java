package de.tud.model.manager;

import de.tud.model.medication.DummyMedication;
import de.tud.model.medication.Medication;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MedicationPlanManager extends EntityManager<Medication> {

    private static final Logger LOGGER = Logger.getLogger(MedicationPlanManager.class.getName());

    private MedicationPlanManager(){

    }
    private static class MedicationPlanManagerInstance {

        private static final MedicationPlanManager INSTANCE
                = new MedicationPlanManager();
    }
    public static MedicationPlanManager getInstance() {
        return MedicationPlanManagerInstance.INSTANCE;
    }

    @Override
    public List<Medication> read() {
        Session session = getSessionFactory().openSession();
        List<Medication> medications = session.createQuery("FROM Medication").list();
        if (medications.isEmpty()){
            throw new NullPointerException("There was no Medication in the database.");
        }
        session.close();
        LOGGER.log(Level.INFO, "Read " + medications.size() + " from the database!");
        return medications;
    }

    @Override
    public void delete(Long id) {
        if (id == null|| id <= 0) throw new IllegalArgumentException("Expect Medication ID to be not null!");
        Session session = getSessionFactory().openSession();

        try{
            session.beginTransaction();
            Medication medication = findByID(id);
            session.delete(medication);
            session.getTransaction().commit();
            LOGGER.log(Level.INFO, "Successfully deleted " + medication.toString());
        }
        catch (Exception e){
            if (session.getTransaction() != null){
                session.getTransaction().rollback();
                LOGGER.log(Level.INFO, "Deletion failed");
            }
        }
        finally {
            session.close();
        }
    }
    public void deleteAll() {
        Session session = getSessionFactory().openSession();
        session.getTransaction().begin();
        List<Medication> medications = read();
        for(Medication medication : medications){
            session.delete(medication);
        }
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Medication findByID(Long id) {

        if (id == null|| id <= 0) throw new IllegalArgumentException("Expect Medication ID to be not null!");
        Session session = getSessionFactory().openSession();
        Medication medication = session.get(Medication.class, id);
        session.close();
        if (medication == null) throw new IllegalArgumentException("There was no Medication with the given ID!");
        return medication;
    }

    public List<DummyMedication> getAllDummyMedication(){
        Session session = getSessionFactory().openSession();
        List<DummyMedication> dummyMedications = session.createQuery("FROM DummyMedication").list();
        if (dummyMedications.isEmpty()){
            throw new NullPointerException("There was no DummyMedication in the database.");
        }
        session.close();
        LOGGER.log(Level.INFO, "Read " + dummyMedications.size() + " from the database!");
        return dummyMedications;

    }
    public DummyMedication getDummyMedicationByDummyMedicationId(Long id){
        Session session = getSessionFactory().openSession();
        DummyMedication dummyMedication = session.get(DummyMedication.class, id);
        session.close();
        if (dummyMedication == null) throw new IllegalArgumentException("Please enter a valid medication id");
        return dummyMedication;
    }
    public DummyMedication getDummyMedicationByMedicationId(Long id){
        Session session = getSessionFactory().openSession();
        Medication medication = session.get(Medication.class, id);
        session.close();
        DummyMedication dummyMedication = getDummyMedicationByDummyMedicationId(medication.getDmId());
        return dummyMedication;
    }

    public boolean isIncompatibleWith(Long currentDummyMedId){
        List<Medication> medications = read();
        List<Long> dummyIds = new ArrayList<>();
        for (Medication medication : medications){
            dummyIds.add(medication.getDmId());
        }
        DummyMedication dummyMedication = getDummyMedicationByDummyMedicationId(currentDummyMedId);
        List<Long> incompatibleWithDMId = dummyMedication.getIncompatipleWith();
        for (Long l : dummyIds){
            if (incompatibleWithDMId.contains(l.longValue())){
                return true;
            }
            }

        return false;


    }
}
