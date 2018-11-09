package de.tud.model.manager;

import org.hibernate.SessionFactory;

import java.util.List;

public interface EntityManager<T> {

    public SessionFactory getSessionFactory();
    Long create(T entity);
    List<T> read();
    void delete(Long id);
    T findByID(Long id);
}
