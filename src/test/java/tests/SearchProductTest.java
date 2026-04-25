package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;
import pages.SearchPage;
import utilities.ConfigReader;

public class SearchProductTest extends BaseTest {

    @Test
    public void search() throws Exception {

        logger.info("*****Starting SearchProductTest*****");

        ConfigReader.loadConfig();
        String productName = ConfigReader.get("searchProductName");

        try {
            HomePage hp = new HomePage(driver);
            hp.txtSearchField(productName);
            hp.clickSearchIcon();

            SearchPage sp = new SearchPage(driver);

            String heading = sp.getHeadingText();

            Assert.assertTrue(heading.toLowerCase().contains("search"),
                    "Search page not loaded properly. Heading: " + heading);

            boolean isProductFound = sp.isProductListed(productName);

            if (!isProductFound) {
                logger.error("Product not found in search results: " + productName);
            }

            Assert.assertTrue(isProductFound,
                    "Expected product not found in search results: " + productName);

        } catch (Exception e) {
            logger.error("Search test failed", e);
            Assert.fail("Search product test failed due to exception: " + e.getMessage(), e);
        }

        logger.info("*****Ending SearchProductTest*****");
    }
}