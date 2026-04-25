package base;

import java.io.IOException;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import factory.DriverFactory;

public class BaseTest {

    protected WebDriver driver;
    protected Logger logger;

    @BeforeClass
    @Parameters({ "browser" })
    public void setUp(@Optional("chrome") String browser) throws IOException {

        logger = LogManager.getLogger(this.getClass());

        DriverFactory.initDriver(browser);
        driver = DriverFactory.getDriver();
    }

    @AfterClass
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    protected String randomString() {
        return RandomStringUtils.randomAlphabetic(5);
    }

    protected String randomNumber() {
        return RandomStringUtils.randomNumeric(10);
    }

    protected String randomAlphanumeric() {
        return RandomStringUtils.randomAlphanumeric(5);
    }
}