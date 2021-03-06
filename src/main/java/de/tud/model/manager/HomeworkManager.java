package de.tud.model.manager;

import de.tud.model.Homework;
import org.hibernate.Session;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HomeworkManager extends EntityManager<Homework> {

    /**
     * Holds the logger of the HomeworkManager.
     */
    private static final Logger LOGGER = Logger.getLogger(HomeworkManager.class.getName());

    private static class HomeworkManagerInstance {

        private static final HomeworkManager INSTANCE
                = new HomeworkManager();
    }

    public static HomeworkManager getInstance() {
        return HomeworkManagerInstance.INSTANCE;
    }

    private HomeworkManager(){

    }
    /**
     * Returns all persistent Homework objects as a list.
     * @return List of Homework
     */
    @Override
    public List<Homework> read() {
        Session session = getSessionFactory().openSession();
        List<Homework> homework = session.createQuery("FROM Homework").list();
        session.close();
        LOGGER.log(Level.INFO, "Read " + homework.size() + " from the database!");
        return homework;
    }

    /**
     * Deletes the persistent Homework object matching the given ID.
     * @param id
     */
    @Override
    public void delete(Long id) {
        if (id == null|| id <= 0) throw new IllegalArgumentException("Expect HomeworkID to be not null!");
        Session session = getSessionFactory().openSession();

        try{
            session.beginTransaction();
            Homework homework = findByID(id);
            session.delete(homework);
            session.getTransaction().commit();
            LOGGER.log(Level.INFO, "Successfully deleted " + homework.toString());
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
     * Returns a persistent Homework object matching the given ID.
     * @param id
     * @return instance of Homework
     */
    @Override
    public Homework findByID(Long id) {
        if (id == null|| id <= 0) throw new IllegalArgumentException("Expect HomeworkID to be not null!");
        Session session = getSessionFactory().openSession();
        Homework homework = session.get(Homework.class, id);
        session.close();
        if (homework == null) throw new IllegalArgumentException("There was no Homework with the given ID!");
        return homework;
    }

    public void deleteAll() {
        Session session = getSessionFactory().openSession();
        session.getTransaction().begin();
        List<Homework> homeworkList = read();
        for(Homework hw : homeworkList){
            session.delete(hw);
        }
        session.getTransaction().commit();
        session.close();
    }
    /**
     * Sets the state attribute of the Homework object matching to the given ID.
     * @param homeworkID
     * @param bool
     */

    public void setHomeworkStatus(Long homeworkID, Boolean bool){
        if(homeworkID == null) throw new IllegalArgumentException("Expect HomeworkID to be not null!");
        if(bool == null) throw new IllegalArgumentException("Expect done state to be not null!");
        Session session = getSessionFactory().openSession();
        try{
            session.beginTransaction();
            Homework databaseHomework = findByID(homeworkID);
            databaseHomework.setStatus(bool);
            session.update(databaseHomework);
            session.getTransaction().commit();
            LOGGER.log(Level.INFO, "Successfully updated homework");
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
     * Replaces the persistent Homework object matching the ID with an other given Homework object.
     * @param homeworkID
     * @param homework
     */
    public void updateHomework(Long homeworkID, Homework homework){
        if(homeworkID == null) throw new IllegalArgumentException("Expect HomeworkID to be not null!");
        Session session = getSessionFactory().openSession();
        try{
            session.beginTransaction();
            Homework databaseHomework = findByID(homeworkID);
            if(homework.isStatus()!=null)
                databaseHomework.setStatus(homework.isStatus());
            if (homework.getDescription()!=null)
                databaseHomework.setDescription(homework.getDescription());
            if(homework.getName()!= null)
                databaseHomework.setName(homework.getName());
            if(homework.getType()!= null)
                databaseHomework.setType(homework.getType());
            session.update(databaseHomework);
            session.getTransaction().commit();
            LOGGER.log(Level.INFO, "Successfully updated homework");
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
}
