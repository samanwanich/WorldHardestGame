import java.io.*;
import java.util.*;

public class PropLoader {

    public static String loadProperty(String property, String filename) {
        Properties prop = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream(filename);
        try {
            prop.load(stream);
            return prop.getProperty(property);
        } catch (IOException e) {}
        return null;
    }
}
