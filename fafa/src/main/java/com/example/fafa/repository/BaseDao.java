package com.example.fafa.repository;

import com.example.fafa.model.Users;
import com.example.fafa.model.ExerciseGroup;
import com.example.fafa.model.Record;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.example.fafa.util.HibernateSessionFactoryUtil;
import java.util.List;

public abstract class BaseDao<T> {
    private Class<T> clazz;

    public BaseDao(Class<T> clazz) {
        this.clazz = clazz;
    }

    protected Session getCurrentSession() {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession();
    }

    public void save(final T entity) {
        Session session = getCurrentSession();
        Transaction tx1 = session.beginTransaction();
        session.persist(entity);
        tx1.commit();
        session.close();
    }

    public void update(final T entity) {
        Session session = getCurrentSession();
        Transaction tx1 = session.beginTransaction();
        session.merge(entity);
        tx1.commit();
        session.close();
    }

    public void delete(final T entity) {
        Session session = getCurrentSession();
        Transaction tx1 = session.beginTransaction();
        session.remove(entity);
        tx1.commit();
        session.close();
    }

    public void deleteById(final long entityId) {
        final T entity = findOne(entityId);
        delete(entity);
    }

    public T findOne(final long id) {
        Session session = getCurrentSession();
        session.beginTransaction();
        T item = session.get(clazz, id);
        session.getTransaction().commit();
        session.close();
        return item;
    }

    public List<T> findAll() {
        Session session = getCurrentSession();
        session.beginTransaction();
        List<T> items = session.createQuery("from " + clazz.getName(), clazz).list();
        session.getTransaction().commit();
        session.close();
        return items;
    }
}