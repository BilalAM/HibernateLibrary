package DataAccess;


import Models.Customer;
import Models.Employee;
import Models.Product;

import java.util.List;

/**
 * Created by BilalAM on 1/17/2017.
 */
public class Program {
    public static void main(String[] args){
        ProductDataAccess productDataAccess = new ProductDataAccess();

        System.out.println(productDataAccess.getAverageStockPrice());



    }
}
