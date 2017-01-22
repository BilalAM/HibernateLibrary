package DataAccess;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BilalAM on 1/17/2017.
 */

public abstract class DataAccessor<T> implements IDataAccesser<T> {
    // protected static EntityManagerFactory sessionFactoryBuilder = Randomness.getManagerFactory();
    private List<T> innerTable = new ArrayList<T>();
    private Class<T> entityClass;

    protected static SessionFactory sessionFactoryBuilder = new Configuration().configure().buildSessionFactory();

    public DataAccessor() {
        this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public void add(T entity) {

        try (Session session = sessionFactoryBuilder.openSession()) {

            session.getTransaction().begin();

            session.persist(entity);
            session.getTransaction().commit();

        } catch (HibernateException hb) {
            hb.printStackTrace();
        }
    }

    @Override
    public void addRange(T[] entites) {
        try (Session session = sessionFactoryBuilder.openSession()) {
            session.getTransaction().begin();
            for (T entity : entites) {
                session.persist(entity);
            }
            session.getTransaction().commit();
        } catch (HibernateException hb) {
            hb.printStackTrace();
        }
    }

    @Override
    public List<T> getAll() {
        try (Session session = sessionFactoryBuilder.openSession()) {
            session.getTransaction().begin();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
            Root<T> root = criteriaQuery.from(entityClass);
            innerTable = session.createQuery(criteriaQuery).getResultList();
            session.getTransaction().commit();

        } catch (HibernateException hb) {
            hb.printStackTrace();
        }
        return innerTable;
    }

    @Override
    public T getOne(Integer ID) {
        T entity;
        try (Session session = sessionFactoryBuilder.openSession()) {
            session.getTransaction().begin();
            if (ID == null) {
                throw new NullPointerException("null");
            } else if (ID < 0) {
                throw new IllegalArgumentException("cannot be less than 0");
            } else {
                entity = session.find(entityClass, ID);
                session.getTransaction().commit();

                return entity;
            }
        } catch (HibernateException hb) {
            hb.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(Integer ID) {
        T entityToDelete = getOne(ID);
        try (Session session = sessionFactoryBuilder.openSession()) {
            if (ID == null) {
                throw new NullPointerException("null");
            } else if (ID < 0) {
                throw new IllegalArgumentException("cannot be less than 0");
            } else {

                session.getTransaction().begin();
                session.remove(entityToDelete);
                session.getTransaction().commit();

            }
        } catch (HibernateException hb) {
            hb.printStackTrace();
        }

    }


}
