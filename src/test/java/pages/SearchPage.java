package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage extends BasePage {

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[normalize-space()='Add to Cart']")
    private WebElement btnAddToCart;

    @FindBy(xpath = "//button[@type='button']//i[@class='fa fa-heart']")
    private WebElement btnWishList;

    @FindBy(xpath = "//span[normalize-space()='Shopping Cart']")
    private WebElement linkShoppingCart;

    @FindBy(xpath = "//span[normalize-space()='Wish List (1)']")
    private WebElement linkWishList;

    @FindBy(css = "div.alert.alert-success")
    private WebElement alertSuccess;

    @FindBy(xpath = "//div[@id='content']//h1")
    private WebElement heading;

    private WebElement waitForClickable(WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    private WebElement waitForVisible(WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(element));
    }

    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", element);
    }

    private void safeClick(WebElement element) {
        WebElement el = waitForClickable(element);
        scrollToElement(el);
        try {
            el.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", el);
        }
    }

    // ---------- actions ----------

    public void clickAddToCart() {
        safeClick(btnAddToCart);

        // wait for success alert after click
        waitForVisible(alertSuccess);
    }

    public void clickWishList() {
        safeClick(btnWishList);
        waitForVisible(alertSuccess);
    }

    public void clickShoppingCart() {
        safeClick(linkShoppingCart);
    }

    public void clickLinkWishList() {
        safeClick(linkWishList);
    }

    public String getHeadingText() {
        try {
            return waitForVisible(heading).getText();
        } catch (Exception e) {
            return "";
        }
    }

    public String waitForSuccessAlertText() {
        return waitForVisible(alertSuccess).getText();
    }
    public boolean isProductListed(String productName) {

        String safe = productName.replace("\"", "\\\"");

        By productLocator = By.xpath(
            "//div[contains(@class,'product-thumb')]//a[normalize-space()=\"" + safe + "\"]"
        );

        try {
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.presenceOfElementLocated(productLocator));

            return !driver.findElements(productLocator).isEmpty();

        } catch (Exception e) {
            return false;
        }
    }
    public void clickAddToCart(String productName) {
        String safe = productName.replace("\"", "\\\"");
        
        By locator = By.xpath(
            "//a[normalize-space()=\"" + safe + "\"]/ancestor::div[contains(@class,'product-thumb')]//span[text()='Add to Cart']"
        );

        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(locator));

        element.click();

        waitForSuccessAlertText();
    }
    public void clickWishList(String productName) {
        String safe = productName.replace("\"", "\\\"");

        By locator = By.xpath(
            "//a[normalize-space()=\"" + safe + "\"]/ancestor::div[contains(@class,'product-thumb')]//button[contains(@onclick,'wishlist')]"
        );

        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(locator));

        element.click();

        waitForSuccessAlertText();
    }
}