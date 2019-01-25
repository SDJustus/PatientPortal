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

        /**
         * Holds the singleton instance of the MedicationPlanManager.
         */
        private static final MedicationPlanManager INSTANCE
                = new MedicationPlanManager();
    }

    /**
     * Returns the singleton instance of the MedicationPlanManager.
     * @return
     */
    public static MedicationPlanManager getInstance() {
        return MedicationPlanManagerInstance.INSTANCE;
    }

    /**
     * Returns a list of all persistent Medication objects.
     * @return
     */
    @Override
    public List<Medication> read() {
        Session session = getSessionFactory().openSession();
        List<Medication> medications = session.createQuery("FROM Medication").list();
        session.close();
        LOGGER.log(Level.INFO, "Read " + medications.size() + " from the database!");
        return medications;
    }

    /**
     * Deletes the persistent Medication object matching the given id.
     * @param id
     */
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

    /**
     * Deletes all persistent Medication objects.
     */
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

    /**
     * Returns a Medication object matching the given id.
     * @param id
     * @return
     */
    @Override
    public Medication findByID(Long id) {

        if (id == null|| id <= 0) throw new IllegalArgumentException("Expect Medication ID to be not null!");
        Session session = getSessionFactory().openSession();
        Medication medication = session.get(Medication.class, id);
        session.close();
        if (medication == null) throw new IllegalArgumentException("There was no Medication with the given ID!");
        return medication;
    }

    /**
     * Returns a list of all persistent DummyMedication objects.
     * @return
     */
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
    /**
     * Returns a DummyMedication object matching the DummyMedication id.
     * @param id
     * @return
     */
    public DummyMedication getDummyMedicationByDummyMedicationId(Long id){
        Session session = getSessionFactory().openSession();
        DummyMedication dummyMedication = session.get(DummyMedication.class, id);
        session.close();
        if (dummyMedication == null) throw new IllegalArgumentException("Please enter a valid medication id");
        return dummyMedication;
    }

    /**
     * Returns a DummyMedication object matching the Medication id.
     * @param id
     * @return
     */
    public DummyMedication getDummyMedicationByMedicationId(Long id){
        Session session = getSessionFactory().openSession();
        Medication medication = session.get(Medication.class, id);
        session.close();
        DummyMedication dummyMedication = getDummyMedicationByDummyMedicationId(medication.getDmId());
        return dummyMedication;
    }

    /**
     * Searches for incompatibility between medications and returns a matching boolean value.
     * @param currentDummyMedId
     * @return
     */
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
