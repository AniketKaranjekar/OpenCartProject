package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;
import pages.LoginPage;
import pages.MyAccountPage;
import utilities.ConfigReader;

public class LoginTest extends BaseTest {

    @Test
    public void login() throws Exception {

        logger.info("*****Starting LoginTest*****");

        ConfigReader.loadConfig();

        try {
            HomePage hp = new HomePage(driver);
            hp.clickLinkMyAccount();
            hp.clickBtnLogin();

            LoginPage lp = new LoginPage(driver);
            lp.setEmail(ConfigReader.get("email"));
            lp.setPassword(ConfigReader.get("password"));
            lp.clickLogin();

            MyAccountPage macc = new MyAccountPage(driver);

            boolean isLoggedIn = macc.isMyAccountPageExist();

            if (!isLoggedIn) {
                logger.error("Login failed. My Account page not displayed.");
            }

            Assert.assertTrue(isLoggedIn, "Login failed: My Account page not displayed.");

        } catch (Exception e) {
            logger.error("Exception during login test", e);
            Assert.fail("Login test failed due to exception: " + e.getMessage(), e);
        }

        logger.info("*****Finished LoginTest*****");
    }
}