package utilities;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    public static void loadConfig() throws IOException {
        if (properties == null) {
            FileReader file = new FileReader("./src/test/resources/config.properties");
            properties = new Properties();
            properties.load(file);
        }
    }

    public static String get(String key) {
        if (properties == null) {
            throw new IllegalStateException("Config not loaded. Call loadConfig() first.");
        }
        return properties.getProperty(key);
    }
}

