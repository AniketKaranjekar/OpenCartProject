package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShoppingCartPage extends BasePage {

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@id='content']//h1")
    private WebElement heading;

    private WebDriverWait getWait() {
        return new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getHeadingText() {
        try {
            return getWait()
                    .until(ExpectedConditions.visibilityOf(heading))
                    .getText();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isProductPresent(String productName) {

        String safe = productName.replace("\"", "\\\"");

        By productLocator = By.xpath(
                "//div[@id='content']//table//a[normalize-space()=\"" + safe + "\"]"
        );

        try {
            getWait().until(ExpectedConditions.presenceOfElementLocated(productLocator));
            List<WebElement> products = driver.findElements(productLocator);
            return !products.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }
}