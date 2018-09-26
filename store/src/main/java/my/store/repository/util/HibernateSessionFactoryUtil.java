package my.store.repository.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {

    private static SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration()
                    .configure()
                    .buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private HibernateSessionFactoryUtil() {
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
