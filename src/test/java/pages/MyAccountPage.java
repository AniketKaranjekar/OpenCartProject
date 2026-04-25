package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h2[normalize-space()='My Account']")
    private WebElement msgHeading;

    @FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Logout']")
    private WebElement btnLogout;

    public boolean isMyAccountPageExist() {
        try {
            waitVisible(msgHeading, Duration.ofSeconds(5));
            return msgHeading.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickLogout() {
        btnLogout.click();
    }
}

