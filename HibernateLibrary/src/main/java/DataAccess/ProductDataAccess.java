package DataAccess;

import Models.Product_;
import Models.Product;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BilalAM on 1/19/2017.
 */
public class ProductDataAccess extends DataAccessor<Product> {


    // filter by category
    public List<Product> filterByCategory(String category){
        List<Product> innerProducts = new ArrayList<>();
        try {
            Session session = sessionFactoryBuilder.openSession();
            session.getTransaction().begin();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Product> criteriaQuery = builder.createQuery(Product.class);
            Root<Product> root = criteriaQuery.from(Product.class);

            criteriaQuery.select(root);
            criteriaQuery.where(builder.equal(root.get(Product_.productCategory), category));

            innerProducts = session.createQuery(criteriaQuery).getResultList();
            return innerProducts;

        }catch(HibernateException hb){
            hb.printStackTrace();
        }
        return innerProducts;
    }


    //Hibernate: select avg(product0_.productPrice) as col_0_0_ from Product product0_

    public Double getAverageStockPrice(){
        Double price;
        try(Session session = sessionFactoryBuilder.openSession()){
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Double> criteriaQuery = builder.createQuery(Double.class);

            Root<Product> productRoot = criteriaQuery.from(Product.class);

            criteriaQuery.select(builder.avg(productRoot.get(Product_.productPrice)));
            price = session.createQuery(criteriaQuery).getSingleResult();
            return price;

        }catch(HibernateException hb){
            hb.printStackTrace();
        }
        return 0.0;
    }

}
