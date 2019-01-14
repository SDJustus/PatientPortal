package de.tud.model.manager;

import de.tud.model.DiaryEntry;
import de.tud.model.Person;

import org.hibernate.Session;

import javax.persistence.Query;
import java.util.List;

public class PersonManager extends EntityManager<Person> {

    /*
      public static void main(String[] args) {

          Address address = new Address("as", "1", 19287, "abc", "abc");
          Person person1 = new Person("Max", "Mustermann", Person.Gender.MALE,
                  "max.mustermann@musterprovider.de", null, "01234567890", address);
          Person person2 = new Person("Maxi", "Musterfrau", Person.Gender.FEMALE,
                  "maxi.musterfrau@musterprovider.de", LocalDate.of(2000,1,1), "01234567890", address);
          System.out.println(" =======CREATE =======");
          personManager.create(person1);

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
          System.exit(0);
      }
  */

    /**
     * Returns all persistent Person objects as a list.
     * @return List of Person
     */
    public List<Person> read() {
        Session session = getSessionFactory().openSession();
        List<Person> persons = session.createQuery("FROM Person").list();
        session.close();
        System.out.println("Found " + persons.size() + " persons");
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

