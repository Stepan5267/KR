package com.example.fafa.util;

import com.example.fafa.model.ExerciseGroup;
import com.example.fafa.model.Users;
import com.example.fafa.model.Record;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                configuration.configure("hibernate.cfg.xml");

                configuration.addAnnotatedClass(ExerciseGroup.class);
                configuration.addAnnotatedClass(Users.class);
                configuration.addAnnotatedClass(Record.class);

                sessionFactory = configuration.buildSessionFactory();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Ошибка при создании SessionFactory: " + e.getMessage(), e);
            }
        }
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}