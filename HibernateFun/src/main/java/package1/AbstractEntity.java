package package1;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import java.lang.reflect.ParameterizedType;
import java.sql.*;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by BilalAM on 12/20/2016.
 */

public abstract class AbstractEntity<T> implements IDataAccess<T> {


    private List<T> innerTable = new ArrayList<>();
    private static SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    private Class<T> entityClass;
    private static EntityManagerFactory entityManagerFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

    public AbstractEntity(){
        this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public List<T> getAll() {
        try (Session session1 = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session1.getCriteriaBuilder();
            CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
            Root<T> rootQuery = criteriaQuery.from(entityClass);
            TypedQuery<T> resultantQuery = session1.createQuery(criteriaQuery);
            innerTable = resultantQuery.getResultList();


        } catch (HibernateException hb) {
            hb.printStackTrace();


        }
        return innerTable;
    }

    @Override
    public T getOne(Integer id) {
        T entity;
        if (id == null) {
            throw new NullPointerException();
        } else if (id < 0) {
            throw new IllegalArgumentException();
        } else {
            try(Session session = sessionFactory.openSession()) {
               entity = session.get(entityClass,id);
                return entity;
            }catch(HibernateException hb){
                hb.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void add(T entity){

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();
        } catch (HibernateException hib) {
            hib.printStackTrace();
        }
    }


}
