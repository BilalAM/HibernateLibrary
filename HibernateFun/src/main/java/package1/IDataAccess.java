package package1;

import java.io.Closeable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by BilalAM on 12/18/2016.
 */
interface IDataAccess<T> extends Closeable {
        void add(T entity);
        List<T> getAll();
        T getOne(Integer id);

}
