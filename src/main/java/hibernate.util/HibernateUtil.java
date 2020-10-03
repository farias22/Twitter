package hibernate.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {


    private static HibernateUtil instance;
    private final EntityManagerFactory factory = Persistence.createEntityManagerFactory("myDatabase");
    private final EntityManager manager = factory.createEntityManager();


    private HibernateUtil() {

    }

    public static HibernateUtil getInstance() {
        if (instance == null) {
            instance = new HibernateUtil();
        }
        return instance;
    }


    public void save(Object object) {
        manager.getTransaction().begin();
        if (!manager.contains(object)) {
            manager.persist(object);
            manager.flush();
        }
        manager.getTransaction().commit();
    }

    public void delete(Class clazz, Long id) {
        manager.getTransaction().begin();
        Object o = manager.find(clazz, id);
        if (null != o) {
            manager.remove(o);
        }
        manager.flush();
        manager.getTransaction().commit();
    }


    public EntityManager getManager() {
        return manager;
    }
}
