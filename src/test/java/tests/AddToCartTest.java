package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;
import pages.SearchPage;
import utilities.ConfigReader;

public class AddToCartTest extends BaseTest {

    @Test
    public void addToCart() throws Exception {
        logger.info("*****Starting AddToCartTest*****");

        ConfigReader.loadConfig();
        String productName = ConfigReader.get("searchProductName");

        try {
            HomePage hp = new HomePage(driver);
            hp.txtSearchField(productName);
            hp.clickSearchIcon();

            SearchPage sp = new SearchPage(driver);
            sp.clickAddToCart();

            String successMsg = sp.waitForSuccessAlertText();
            Assert.assertTrue(successMsg.toLowerCase().contains("success"),
                    "Expected success message after adding to cart, but got: " + successMsg);
            Assert.assertTrue(successMsg.contains(productName),
                    "Success message should contain product name '" + productName + "', but got: " + successMsg);
        } catch (Exception e) {
            Assert.fail("Add to cart test failed due to exception: " + e.getMessage(), e);
        }

        logger.info("*****Ending AddToCartTest*****");
    }
}

