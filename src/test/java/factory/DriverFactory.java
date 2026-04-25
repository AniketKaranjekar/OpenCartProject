package factory;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverFactory {

    private static WebDriver driver;
    private static Properties properties;

    public static void initConfig() throws IOException {
        if (properties == null) {
            FileReader file = new FileReader("./src/test/resources/config.properties");
            properties = new Properties();
            properties.load(file);
        }
    }

    public static WebDriver initDriver(String browser) throws IOException {
        initConfig();

        if (driver == null) {
            switch (browser.toLowerCase()) {
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }

            driver.manage().deleteAllCookies();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();
            driver.get(getProperty("appUrl"));
        }

        return driver;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public static String getProperty(String key) {
        if (properties == null) {
            throw new IllegalStateException("Config properties not initialized");
        }
        return properties.getProperty(key);
    }
}
