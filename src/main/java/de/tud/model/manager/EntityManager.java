package de.tud.model.manager;

import de.tud.model.Diary;
import de.tud.model.DiaryEntry;
import de.tud.model.EntityObject;
import de.tud.model.VitalData;
import de.tud.model.symptom.*;
import de.tud.model.welfare.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;

public abstract class EntityManager<T extends EntityObject> {

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
                .addAnnotatedClass(WelfareFactory.class);

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration
                .buildSessionFactory(builder.build());
        return sessionFactory;
    }
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
    public abstract List<T> read();
    public abstract void delete(Long id);
    public abstract T findByID(Long id);
}
