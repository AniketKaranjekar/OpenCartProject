package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;
import pages.LoginPage;
import pages.MyAccountPage;
import pages.SearchPage;
import pages.ShoppingCartPage;
import utilities.ConfigReader;

public class ShoppingCartTest extends BaseTest {

    @Test
    public void cart() throws Exception {

        logger.info("*****Starting ShoppingCartTest*****");

        ConfigReader.loadConfig();
        String productName = ConfigReader.get("searchProductName");

        try {
            HomePage hp = new HomePage(driver);

            hp.clickLinkMyAccount();
            hp.clickBtnLogin();

            LoginPage lp = new LoginPage(driver);
            lp.setEmail(ConfigReader.get("email"));
            lp.setPassword(ConfigReader.get("password"));
            lp.clickLogin();

            MyAccountPage macc = new MyAccountPage(driver);
            Assert.assertTrue(macc.isMyAccountPageExist(), "Login failed before cart test.");

            hp.txtSearchField(productName);
            hp.clickSearchIcon();

            SearchPage sp = new SearchPage(driver);

            sp.clickAddToCart(productName);

            String successMsg = sp.waitForSuccessAlertText();

            Assert.assertTrue(successMsg.toLowerCase().contains("success"),
                    "Expected success message but got: " + successMsg);

            Assert.assertTrue(successMsg.contains(productName),
                    "Wrong product added to cart. Message: " + successMsg);
            
            sp.clickShoppingCart();

         String currentUrl = driver.getCurrentUrl();
         logger.info("Current URL after cart click: " + currentUrl);

         Assert.assertTrue(currentUrl.contains("route=checkout/cart"),
                 "Did not navigate to cart page. URL: " + currentUrl);

            ShoppingCartPage cart = new ShoppingCartPage(driver);

            String heading = cart.getHeadingText();

            Assert.assertTrue(heading.toLowerCase().contains("shopping cart"),
                    "Shopping Cart page not loaded. Heading: " + heading);

            Assert.assertTrue(cart.isProductPresent(productName),
                    "Expected product not found in shopping cart: " + productName);

        } catch (Exception e) {
            logger.error("Shopping cart test failed", e);
            Assert.fail("Shopping cart test failed due to exception: " + e.getMessage(), e);
        }

        logger.info("*****Ending ShoppingCartTest*****");
    }
}