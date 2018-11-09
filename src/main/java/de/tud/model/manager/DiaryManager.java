package de.tud.model.manager;

import de.tud.model.Diary;
import de.tud.model.DiaryEntry;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.List;

public class DiaryManager implements EntityManager<Diary> {

    @Override
    public SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(Diary.class)
                .addAnnotatedClass(de.tud.model.DiaryEntry.class)
                .addAnnotatedClass(de.tud.model.symptom.Symptom.class)
                .addAnnotatedClass(de.tud.model.symptom.Depression.class);

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration
                .buildSessionFactory(builder.build());
        return sessionFactory;
    }

    @Override
    public Long create(Diary entity) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully created " + entity.toString());
        return entity.getId();
    }

    @Override
    public List<Diary> read() {
        Session session = getSessionFactory().openSession();
        List<Diary> diary = session.createQuery("FROM Diary").list();
        session.close();
        System.out.println("Found " + diary.size() + " diaries");
        return diary;
    }


    public void update(Diary entity) {

    }



    public void addDiaryEntry(DiaryEntry diaryEntry, long id){
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        session.get(Diary.class, id).getDiaryEntries().add(diaryEntry);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Long id) {
        Session session = getSessionFactory().openSession();

        try{
            session.beginTransaction();
            Diary diary = findByID(id);
            session.delete(diary);
            session.getTransaction().commit();
            System.out.println("Successfully deleted " + diary.toString());
        }
        catch (Exception e){
            if (session.getTransaction() != null){
                session.getTransaction().rollback();
                System.out.println("Deletion failed");
            }
        }
        finally {
            session.close();
        }
    }

    @Override
    public Diary findByID(Long id) {
        Session session = getSessionFactory().openSession();
        Diary diary = (Diary) session.load(Diary.class, id);
        session.close();
        return diary;
    }

    public void deleteAll() {
        Session session = getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query = session.createQuery("DELETE FROM Diary ");
            query.executeUpdate();
            session.getTransaction().commit();
            System.out.println("Successfully deleted all diaries.");
        }
        catch (Exception e){
            session.getTransaction().rollback();
            System.out.println("Deletion of all diaries failed");
        }
        finally {
            session.close();
        }
    }
}