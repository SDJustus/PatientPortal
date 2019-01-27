package de.tud.model.manager;

import de.tud.model.Person;

import org.hibernate.Session;

import javax.persistence.Query;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * in development
 */
public class PersonManager extends EntityManager<Person> {

    private final Logger LOGGER = Logger.getLogger(PersonManager.class.getSimpleName());

    /**
     * Returns all persistent Person objects as a list.
     * @return List of Person
     */
    public List<Person> read() {
        Session session = getSessionFactory().openSession();
        List<Person> persons = session.createQuery("FROM Person").list();
        session.close();
        LOGGER.log(Level.INFO,"Found " + persons.size() + " persons");
        return persons;
    }

    /**
     * Replaces a persistent Person object with a Person object which has the same ID.
     * @param p
     */
    public void update(Person p) {
        Session session = getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Person person = session.load(Person.class, p.getId());
            person.setGivenName(p.getGivenName());
            person.setFamilyName(p.getFamilyName());
            session.getTransaction().commit();
            LOGGER.log(Level.INFO,"Successfully updated " + p.toString());
        }
        catch(Exception e){
            if (session.getTransaction() != null)
            session.getTransaction().rollback();
            LOGGER.log(Level.INFO, "Upadate on " + p.toString() + " failed");
        }
        finally {
            session.close();
        }
    }

    /**
     * Deletes the persistent Person object matching the ID.
     * @param id
     */
    @Override
    public void delete(Long id) {

        Session session = getSessionFactory().openSession();

        try{
            session.beginTransaction();
            Person p = findByID(id);
            session.delete(p);
            session.getTransaction().commit();
            LOGGER.log(Level.INFO, "Successfully deleted " + p.toString());
        }
        catch (Exception e){
            if (session.getTransaction() != null){
                session.getTransaction().rollback();
                LOGGER.log(Level.INFO,"Deletion failed");
                }
        }
        finally {
            session.close();
        }
    }

    /**
     * Returns a persistent Person object matching the given ID.
     * @param id
     * @return instance of Person
     */
    @Override
    public Person findByID(Long id) {
        Session session = getSessionFactory().openSession();
        Person p = session.load(Person.class, id);
        session.close();
        return p;
    }


    /**
     * Deletes all persistent Person objects in the database.
     */
    public void deleteAll() {
        Session session = getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query = session.createQuery("DELETE FROM Person ");
            query.executeUpdate();
            session.getTransaction().commit();
            LOGGER.log(Level.INFO,"Successfully deleted all persons.");
        }
        catch (Exception e){
            session.getTransaction().rollback();
            LOGGER.log(Level.INFO,"Deletion of all persons failed");
        }
        finally {
            session.close();
        }
    }
}

