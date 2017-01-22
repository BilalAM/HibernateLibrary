package package2;

import org.omg.CORBA.Environment;
import package1.AbstractEntity;
import package1.Customer;
import package1.CustomerDataAccess;
import package1.Utilites;

import java.sql.*;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import package2.*;
/**
 * Created by BilalAM on 12/17/2016.
 */
public class Program {
    public static void main(String[] args) throws Exception {



        CustomerDataAccess customerDataAccess = new CustomerDataAccess();
       List<Customer> cust =  customerDataAccess.getAll();



    }

}
