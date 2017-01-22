package package1;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by BilalAM on 1/8/2017.
 */
public class Utilites {
    public static String getDatabaseConnectionString() throws IOException{
        String connectionString;

        Properties prop = new Properties();
        prop.load(new FileInputStream("application.config.xml"));

        connectionString = prop.getProperty("connectionString");

        return connectionString;
    }

}
