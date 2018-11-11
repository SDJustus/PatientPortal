package de.tud.Model;

import de.tud.View.Tagebuch;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TagebucheintragManager {

    public static void main(String[] args) {
       /* List<DataModelDiary> tagebucheintragList = new ArrayList<>();
                tagebucheintragList.add(new DataModelDiary(LocalDateTime.of(2018, 4, 3, 13, 42), new Depression(Symptom.Strength.SEVERE)));

       /Tagebucheintrag tagebucheintrag = new Tagebucheintrag( tagebucheintragList, LocalDateTime.of(2018, 3, 4, 12, 42));
        create(tagebucheintrag);

        //List<Tagebucheintrag> tagebucheintragList1 = read();
        //System.out.println(tagebucheintragList1); */

    }

    public static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(de.tud.Model.Tagebucheintrag.class)
        .addAnnotatedClass(de.tud.Model.DataModelDiary.class)
                .addAnnotatedClass(de.tud.Model.Symptom.class)
        .addAnnotatedClass(Depression.class);


        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration
                .buildSessionFactory(builder.build());
        return sessionFactory;
    }

    public static long create(Tagebucheintrag te) {                 //CREATE
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        /*
        for (DataModelDiary d: te.getSymptoms()){
            session.save(d.getSymptom());
            session.save(d);
        }*/
        session.save(te);
        session.getTransaction().commit();
        session.close();

        System.out.println("Successfully created " + te.toString());
        return te.getPersonId();
    }

    public static List<Tagebucheintrag> read() {     //READ AS LIST
        //List<Tagebucheintrag> relevantEntries = new ArrayList<>();

        Session session = getSessionFactory().openSession();
        //session.createQuery("from Tagebucheintrag ");

        List<Tagebucheintrag> entries = (List<Tagebucheintrag>) session.createQuery("FROM Tagebucheintrag").list();

        session.close();
        return entries;
    }



    public static void update(Tagebucheintrag updatedEntry) {           //UPDATE
        Session session = getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Tagebucheintrag entry = session.get(Tagebucheintrag.class, updatedEntry.getPersonId());
            entry.setSymptom(updatedEntry.getSymptoms());
            entry.setDate(updatedEntry.getDate());
            session.getTransaction().commit();
            System.out.println("Successfully updated " + updatedEntry.toString());
        }
        catch(Exception e){
            if (session.getTransaction() != null)
                session.getTransaction().rollback();
            System.out.println("Upadate on " + updatedEntry.toString() + " failed");
        }
        finally {
            session.close();
        }
    }
    public static Tagebucheintrag findByID(long id) {
        Session session = getSessionFactory().openSession();
        Tagebucheintrag tagebucheintrag = (Tagebucheintrag) session.load(Tagebucheintrag.class, id);
        session.close();
        return tagebucheintrag;
    }

    public static void delete(long id) {            //DELETE SINGLE
        Tagebucheintrag tagebucheintrag;

        Session session = getSessionFactory().openSession();

        try{
            session.beginTransaction();
            tagebucheintrag = findByID(id);
                session.delete(tagebucheintrag);
            session.getTransaction().commit();
            System.out.println("Successfully deleted ");
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

    /*
    public static void deleteAll() {                                   //DELETE ALL
        Session session = getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query = session.createQuery("DELETE FROM diary_entry ");
            query.executeUpdate();
            session.getTransaction().commit();
            System.out.println("Successfully deleted all diary entries.");
        }
        catch (Exception e){
            session.getTransaction().rollback();
            System.out.println("Deletion of all entries failed");
        }
        finally {
            session.close();
        }
    }
    */
}

