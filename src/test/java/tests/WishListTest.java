package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;
import pages.LoginPage;
import pages.SearchPage;
import pages.WishListPage;
import utilities.ConfigReader;

public class WishListTest extends BaseTest {

    @Test
    public void wishList() throws Exception {
        logger.info("*****Starting WishListTest*****");

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

            HomePage hp1 = new HomePage(driver);
            hp1.txtSearchField(productName);
            hp1.clickSearchIcon();

            SearchPage sp = new SearchPage(driver);
            sp.clickWishList();

            String successMsg = sp.waitForSuccessAlertText();
            Assert.assertTrue(successMsg.toLowerCase().contains("success"),
                    "Expected success message after adding to wish list, but got: " + successMsg);
            Assert.assertTrue(successMsg.toLowerCase().contains("wish list"),
                    "Expected wish list success message, but got: " + successMsg);

            sp.clickLinkWishList();

            WishListPage wl = new WishListPage(driver);
            Assert.assertTrue(wl.getHeadingText().toLowerCase().contains("wish list"),
                    "Wish List page should be opened after clicking Wish List link.");
            Assert.assertTrue(wl.isProductPresent(productName), "Expected product not found in wish list: " + productName);
        } catch (Exception e) {
            Assert.fail("Wish list test failed due to exception: " + e.getMessage(), e);
        }

        logger.info("*****Ending WishListTest*****");
    }
}

