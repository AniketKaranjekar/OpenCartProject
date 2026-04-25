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
            Assert.assertTrue(sp.getHeadingText().toLowerCase().contains("search"),
                    "Search page should be opened after clicking search icon.");
            Assert.assertTrue(sp.isProductListed(productName), "Expected product not found in search results: " + productName);
        } catch (Exception e) {
            Assert.fail("Search product test failed due to exception: " + e.getMessage(), e);
        }

        logger.info("*****Ending SearchProductTest*****");
    }
}

