package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;
import pages.RegistrationPage;
public class RegistrationTest extends BaseTest {

    @Test
    public void accountRegister() {
        logger.info("*****Starting RegistrationTest*****");
        try {
            HomePage hp = new HomePage(driver);
            hp.clickLinkMyAccount();
            logger.info("Click on my account");

            hp.clickLinkRegister();
            logger.info("Click on register");

            RegistrationPage regpage = new RegistrationPage(driver);
            regpage.setFirstName(randomString().toUpperCase());
            regpage.setLatName(randomString().toUpperCase());
            regpage.setEmail(randomString() + "@gmail.com");
            regpage.setTelephone(randomNumber());

            String password = randomAlphanumeric();
            regpage.setPassword(password);
            regpage.setConfirmPassword(password);
            regpage.setChkBox();
            regpage.clickbthContinue();

            String confmsg = regpage.getConfirmationMsg();
            Assert.assertEquals(confmsg, "Your Account Has Been Created!", "Registration success message mismatch.");
        } catch (Exception e) {
            logger.error("Test Failed...", e);
            Assert.fail("Registration test failed due to exception: " + e.getMessage(), e);
        }
        logger.info("*****Finished RegistrationTest*****");
    }
}

