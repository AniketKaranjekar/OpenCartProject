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

            sp.clickAddToCart(productName);

            String successMsg = sp.waitForSuccessAlertText();

            Assert.assertTrue(successMsg.toLowerCase().contains("success"),
                    "Expected success message but got: " + successMsg);

            Assert.assertTrue(successMsg.contains(productName),
                    "Expected product name in message but got: " + successMsg);

        } catch (Exception e) {
            logger.error("Add to cart test failed", e);
            Assert.fail("Add to cart test failed due to exception: " + e.getMessage(), e);
        }

        logger.info("*****Ending AddToCartTest*****");
    }
}