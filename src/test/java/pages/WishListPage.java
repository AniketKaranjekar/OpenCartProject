package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WishListPage extends BasePage {

    public WishListPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@id='content']//h1")
    private WebElement heading;

    public String getHeadingText() {
        try {
            waitVisible(heading, Duration.ofSeconds(5));
            return heading.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isProductPresent(String productName) {
        String safe = productName.replace("\"", "\\\"");
        List<WebElement> products = driver.findElements(By.xpath("//div[@id='content']//table//a[normalize-space()=\"" + safe + "\"]"));
        return !products.isEmpty();
    }
}

