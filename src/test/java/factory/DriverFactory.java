package factory;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
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

        if (tlDriver.get() == null) {

            if (browser.equalsIgnoreCase("chrome")) {

                ChromeOptions options = new ChromeOptions();

                boolean isHeadless = Boolean.parseBoolean(getProperty("headless", "true"));

                if (isHeadless) {
                    options.addArguments("--headless=new");
                    options.addArguments("--no-sandbox");
                    options.addArguments("--disable-dev-shm-usage");
                    options.addArguments("--window-size=1920,1080");
                }

                tlDriver.set(new ChromeDriver(options));

            } else {
                throw new IllegalArgumentException("Only Chrome is supported");
            }

            getDriver().manage().deleteAllCookies();
            getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            String url = getProperty("appUrl", "https://demo.opencart.com/");
            System.out.println("Navigating to: " + url);
            getDriver().get(url);
        }

        return getDriver();
    }

    public static WebDriver getDriver() {
        return tlDriver.get();
    }

    public static void quitDriver() {
        if (tlDriver.get() != null) {
            tlDriver.get().quit();
            tlDriver.remove();
        }
    }

    public static String getProperty(String key, String defaultValue) {
        if (properties == null) {
            throw new IllegalStateException("Config properties not initialized");
        }
        return properties.getProperty(key, defaultValue);
    }

    public static String getProperty(String key) {
        return getProperty(key, null);
    }
}