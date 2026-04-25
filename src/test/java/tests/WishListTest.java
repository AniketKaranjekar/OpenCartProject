package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;
import pages.LoginPage;
import pages.MyAccountPage;
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

            MyAccountPage macc = new MyAccountPage(driver);
            Assert.assertTrue(macc.isMyAccountPageExist(), 
                    "Login failed before wishlist test.");

            hp.txtSearchField(productName);
            hp.clickSearchIcon();

            SearchPage sp = new SearchPage(driver);

            sp.clickWishList(productName);

            String successMsg = sp.waitForSuccessAlertText();

            Assert.assertTrue(successMsg.toLowerCase().contains("success"),
                    "Expected success message but got: " + successMsg);

            Assert.assertTrue(successMsg.toLowerCase().contains("wish list"),
                    "Expected wishlist success message but got: " + successMsg);

            Assert.assertTrue(successMsg.contains(productName),
                    "Wrong product added to wishlist. Message: " + successMsg);

            Thread.sleep(2000);

            String baseUrl = ConfigReader.get("appUrl");
            driver.get(baseUrl + "index.php?route=account/wishlist");

            String currentUrl = driver.getCurrentUrl();
            logger.info("Navigated URL: " + currentUrl);

            Assert.assertTrue(currentUrl.contains("route=account/wishlist"),
                    "Did not navigate to wishlist page. Current URL: " + currentUrl);

            WishListPage wl = new WishListPage(driver);

            String heading = wl.getHeadingText();
            logger.info("Wishlist page heading: " + heading);

            boolean hasProduct = wl.isProductPresent(productName);

            Assert.assertTrue(hasProduct,
                    "Product not found in wishlist and page seems empty. Possible app or locator issue.");
            Assert.assertTrue(heading.toLowerCase().contains("wish list"),
                    "Unexpected page. Heading: " + heading);

            Assert.assertTrue(wl.isProductPresent(productName),
                    "Expected product not found in wishlist: " + productName);

        } catch (Exception e) {
            logger.error("Wishlist test failed", e);
            Assert.fail("Wish list test failed due to exception: " + e.getMessage(), e);
        }

        logger.info("*****Ending WishListTest*****");
    }
}