package de.tud.model.manager;

import de.tud.model.Diary;
import de.tud.model.EntityObject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;

public abstract class EntityManager<T extends EntityObject> {

    public SessionFactory getSessionFactory(){
        Configuration configuration = new Configuration().configure();
        //TODO: Test if the config with pachages work...
        configuration.addPackage("de.tud.model").addPackage("de.tud.model.symptom");
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration
                .buildSessionFactory(builder.build());
        return sessionFactory;
    }
    public Long create(T entity){
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
