package package2;
import java.io.*;
import java.util.Properties;

/**
 * Created by BilalAM on 1/10/2017.
 */
public class QuickUtilis {
    private static Properties prop = new Properties();
    private static String connectionString;
    private static String user;
    private static String password;

    private static void loadProperties() {
        try (BufferedReader reader = new BufferedReader(new FileReader("config.properties"))) {
            prop.load(reader);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
    public static String getConnectionFromProperty() {

        if(prop.isEmpty()){
            loadProperties();
        }
        connectionString = prop.getProperty("dbString");
        return connectionString;
    }
    public static String getUserFromProperty(){
        if(prop.isEmpty()){
            loadProperties();
        }
        user = prop.getProperty("user");
        return user;
    }
    public static String getPasswordFromProperty(){
        if(prop.isEmpty()){
            loadProperties();
        }
        password = prop.getProperty("password");
        return password;
    }

    private static void createPropertyFile() {
        Properties property = new Properties();
        property.setProperty("dbString", "jdbc:mysql://localhost:3306/my_scheme");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("config.properties"))) {
            property.store(writer, "My Properties");

        } catch (IOException io) {
            io.printStackTrace();
        }
    }

}