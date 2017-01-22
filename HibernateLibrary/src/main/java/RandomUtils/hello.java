package RandomUtils;

/**
 * Created by BilalAM on 1/20/2017.
 */
public class hello {
    private static hello ourInstance = new hello();

    public static hello getInstance() {
        return ourInstance;
    }

    private hello() {
    }
}
