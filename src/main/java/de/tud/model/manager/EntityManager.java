package de.tud.model.manager;

import de.tud.model.*;
import de.tud.model.symptom.*;
import de.tud.model.welfare.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;

public abstract class EntityManager<T extends EntityObject> {

    /**
     * Returns an instance of the Hibernate SessionFactory.
     * @return instance of SessionFactory
     */
    public SessionFactory getSessionFactory(){
        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(Diary.class)
                .addAnnotatedClass(DiaryEntry.class)
                .addAnnotatedClass(SymptomFactory.class)
                .addAnnotatedClass(Symptom.class)
                .addAnnotatedClass(Depression.class)
                .addAnnotatedClass(Ache.class)
                .addAnnotatedClass(BladderDisorder.class)
                .addAnnotatedClass(BowelDisorder.class)
                .addAnnotatedClass(CognitiveDisorder.class)
                .addAnnotatedClass(Fatigue.class)
                .addAnnotatedClass(GaitDisorder.class)
                .addAnnotatedClass(LeftArmSpasticity.class)
                .addAnnotatedClass(RightArmSpasticity.class)
                .addAnnotatedClass(LeftLegSpasticity.class)
                .addAnnotatedClass(RightLegSpasticity.class)
                .addAnnotatedClass(VitalData.class)
                .addAnnotatedClass(Welfare.class)
                .addAnnotatedClass(Sleep.class)
                .addAnnotatedClass(ConcentrationAbility.class)
                .addAnnotatedClass(PhysicalCondition.class)
                .addAnnotatedClass(WelfareFactory.class)
                .addAnnotatedClass(Homework.class);

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration
                .buildSessionFactory(builder.build());
        return sessionFactory;
    }

    /**
     * Saves an EntityObject to the database and returns its unique ID.
     * @param entity
     * @return value of id
     */
    public long create(T entity){
        if (entity == null) throw new NullPointerException("Expected entity not to be null.");
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully created " + entity.toString());
        return entity.getId();
    }

    /**
     * Returns all persistent objects of a generic EntityObject as a List.
     * @return
     */
    public abstract List<T> read();

    /**
     * Deletes the EntityObject matching the provided ID.
     * @param id
     */
    public abstract void delete(Long id);

    /**
     * Returns a single generic EntityObject matching the given ID.
     * @param id
     * @return
     */
    public abstract T findByID(Long id);
}
