package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;
import pages.LoginPage;
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

            hp.txtSearchField(productName);
            hp.clickSearchIcon();

            SearchPage sp = new SearchPage(driver);
            sp.clickAddToCart();

            String successMsg = sp.waitForSuccessAlertText();
            Assert.assertTrue(successMsg.toLowerCase().contains("success"),
                    "Expected success message after adding to cart, but got: " + successMsg);

            sp.clickShoppingCart();

            ShoppingCartPage cart = new ShoppingCartPage(driver);
            Assert.assertTrue(cart.getHeadingText().toLowerCase().contains("shopping cart"),
                    "Shopping Cart page should be opened after clicking Shopping Cart link.");
            Assert.assertTrue(cart.isProductPresent(productName), "Expected product not found in shopping cart: " + productName);
        } catch (Exception e) {
            Assert.fail("Shopping cart test failed due to exception: " + e.getMessage(), e);
        }

        logger.info("*****Ending ShoppingCartTest*****");
    }
}

