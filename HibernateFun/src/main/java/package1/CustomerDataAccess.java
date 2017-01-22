package package1;

import package2.QuickUtilis;

import java.io.IOException;
import java.sql.Connection;
import java.sql.*;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.*;

/**
 * Created by BilalAM on 12/20/2016.
 */
public class CustomerDataAccess extends AbstractEntity<Customer>{



    @Override
    public void close() throws IOException {

    }
}
