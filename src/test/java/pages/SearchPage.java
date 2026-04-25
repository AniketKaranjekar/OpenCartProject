package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    public void clickAddToCart() {
        btnAddToCart.click();
    }

    public void clickWishList() {
        btnWishList.click();
    }

    public void clickShoppingCart() {
        linkShoppingCart.click();
    }

    public void clickLinkWishList() {
        linkWishList.click();
    }

    public String getHeadingText() {
        try {
            waitVisible(heading, Duration.ofSeconds(5));
            return heading.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public String waitForSuccessAlertText() {
        waitVisible(alertSuccess, Duration.ofSeconds(10));
        return alertSuccess.getText();
    }

    public boolean isProductListed(String productName) {
        String safe = productName.replace("\"", "\\\"");
        List<WebElement> products = driver.findElements(By.xpath("//div[contains(@class,'product-thumb')]//a[normalize-space()=\"" + safe + "\"]"));
        return !products.isEmpty();
    }
}

