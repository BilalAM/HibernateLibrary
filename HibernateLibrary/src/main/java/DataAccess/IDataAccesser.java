package DataAccess;

import java.util.List;

/**
 * Created by BilalAM on 1/17/2017.
 */
public interface IDataAccesser<T> {
    void add(T entity);
    void addRange(T[] entites);

    List<T> getAll();
    T getOne(Integer ID);

    void delete(Integer ID);
}
