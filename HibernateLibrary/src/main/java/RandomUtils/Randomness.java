package RandomUtils;

import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManagerFactory;

/**
 * Created by BilalAM on 1/19/2017.
 */
public class Randomness {

    private static EntityManagerFactory managerFactory;

    private static void createFactory() {
        managerFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    public static EntityManagerFactory getManagerFactory() {
        createFactory();
        return managerFactory;
    }
    public static void close() throws InterruptedException {
        Thread.sleep(100);
        managerFactory.close();
    }
}
