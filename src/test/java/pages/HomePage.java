package pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[normalize-space()='My Account']")
    private WebElement linkMyAccount;

    @FindBy(xpath = "//a[normalize-space()='Register']")
    private WebElement linkRegister;

    @FindBy(xpath = "//a[normalize-space()='Login']")
    private WebElement linkLogin;

    @FindBy(xpath = "//input[@placeholder='Search']")
    private WebElement searchField;

    @FindBy(xpath = "//button[@class='btn btn-default btn-lg']")
    private WebElement searchIcon;

    private WebElement waitForElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    private WebElement waitForClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void clickLinkMyAccount() {
        WebElement element = waitForClickable(linkMyAccount);
        scrollToElement(element);
        element.click();
    }

    public void clickLinkRegister() {
        WebElement element = waitForClickable(linkRegister);
        scrollToElement(element);
        element.click();
    }

    public void clickBtnLogin() {
        WebElement element = waitForClickable(linkLogin);
        scrollToElement(element);
        element.click();
    }

    public void txtSearchField(String productName) {
        WebElement element = waitForElement(searchField);
        element.clear();
        element.sendKeys(productName);
    }

    public void clickSearchIcon() {
        WebElement element = waitForClickable(searchIcon);
        element.click();
    }
}