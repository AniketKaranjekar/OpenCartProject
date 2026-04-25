package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;
import pages.LoginPage;
import pages.MyAccountPage;
import utilities.DataProviders;

public class LoginDDTTest extends BaseTest {

    @Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class)
    public void verify_loginDDT(String email, String pwd, String exp) {
        logger.info("*****Starting LoginDDTTest*****");

        try {
            HomePage hp = new HomePage(driver);
            hp.clickLinkMyAccount();
            hp.clickBtnLogin();

            LoginPage lp = new LoginPage(driver);
            lp.setEmail(email);
            lp.setPassword(pwd);
            lp.clickLogin();

            MyAccountPage macc = new MyAccountPage(driver);
            boolean targetPage = macc.isMyAccountPageExist();

            boolean expectedValid;
            if (exp.equalsIgnoreCase("Valid")) {
                expectedValid = true;
            } else if (exp.equalsIgnoreCase("Invalid")) {
                expectedValid = false;
            } else {
                Assert.fail("Unexpected expected-result value in test data: " + exp);
                return;
            }

            Assert.assertEquals(targetPage, expectedValid,
                    "Login expectation mismatch for email='" + email + "'. Expected=" + exp + " but actual MyAccountPage=" + targetPage);

            if (targetPage) {
                macc.clickLogout();
            }

        } catch (Exception e) {
            Assert.fail("Login DDT test failed due to exception: " + e.getMessage(), e);
        }

        logger.info("*****Finished LoginDDTTest*****");
    }
}

