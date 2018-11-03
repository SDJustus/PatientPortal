package de.tud.Model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.List;

public class PersonManager {
    public static void main(String[] args) {

        Person person1 = new Person("Max", "Mustermann", Person.Gender.MALE,
                "max.mustermann@musterprovider.de", null, "01234567890", null );
        Person person2 = new Person("Maxi", "Musterfrau", Person.Gender.FEMALE,
                "maxi.musterfrau@musterprovider.de", null, "01234567890", null);
        System.out.println(" =======CREATE =======");
        create(person1);
        create(person2);
        /*
        System.out.println(" =======READ =======");
        List<Person> personList = read();
        for(Person p: personList) {
            System.out.println(p.toString());
        }
        System.out.println(" =======UPDATE =======");
        person1.setFamilyName("newName");
        person1.setGivenName("Mary Rose");
        update(person1);
        System.out.println(" =======READ =======");
        List<Person> personList2 = read();
        for(Person p: personList2) {
            System.out.println(p.toString());
        }
        System.out.println(" =======DELETE ======= ");
        delete(person2.getId());
        System.out.println(" =======READ =======");
        List<Person> personList3 = read();
        for(Person p: personList3) {
            System.out.println(p.toString());
        }
        System.out.println(" =======DELETE ALL ======= ");
        deleteAll();
        System.out.println(" =======READ =======");
        List<Person> personList4 = read();
        for(Person p: personList4) {
            System.out.println(p.toString());
        }
        System.exit(0);*/
    }

    public static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(de.tud.Model.Person.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration
                .buildSessionFactory(builder.build());
        return sessionFactory;
    }

    public static Integer create(Person p) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        session.save(p);
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully created " + p.toString());
        return p.getId();
    }

    public static List<Person> read() {
        Session session = getSessionFactory().openSession();
        List<Person> persons = session.createQuery("FROM Person").list();
        session.close();
        System.out.println("Found " + persons.size() + " persons");
        return persons;
    }

    public static void update(Person p) {
        Session session = getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Person person = (Person) session.load(Person.class, p.getId());
            person.setGivenName(p.getGivenName());
            person.setFamilyName(p.getFamilyName());
            session.getTransaction().commit();
            System.out.println("Successfully updated " + p.toString());
        }
        catch(Exception e){
            if (session.getTransaction() != null)
            session.getTransaction().rollback();
            System.out.println("Upadate on " + p.toString() + " failed");
        }
        finally {
            session.close();
        }
    }

    public static void delete(Integer id) {

        Session session = getSessionFactory().openSession();

        try{
            session.beginTransaction();
            Person p = findByID(id);
            session.delete(p);
            session.getTransaction().commit();
            System.out.println("Successfully deleted " + p.toString());
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

    public static Person findByID(Integer id) {
        Session session = getSessionFactory().openSession();
        Person p = (Person) session.load(Person.class, id);
        session.close();
        return p;
    }

    public static void deleteAll() {
        Session session = getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Query query = session.createQuery("DELETE FROM Person ");
            query.executeUpdate();
            session.getTransaction().commit();
            System.out.println("Successfully deleted all persons.");
        }
        catch (Exception e){
            session.getTransaction().rollback();
            System.out.println("Deletion of all persons failed");
        }
        finally {
            session.close();
        }
    }
}

