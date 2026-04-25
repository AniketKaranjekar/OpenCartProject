package pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage extends BasePage {

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "input-firstname")
    private WebElement txtFirstName;

    @FindBy(id = "input-lastname")
    private WebElement txtLastName;

    @FindBy(id = "input-email")
    private WebElement txtEmail;

    @FindBy(id = "input-telephone")
    private WebElement txtTelephone;

    @FindBy(id = "input-password")
    private WebElement txtPassword;

    @FindBy(id = "input-confirm")
    private WebElement txtConfirmPassword;

    @FindBy(name = "agree")
    private WebElement chkPolicy;

    @FindBy(xpath = "//input[@value='Continue']")
    private WebElement btnContinue;

    @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
    private WebElement msgConfirmation;


    private WebElement waitForVisible(WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(element));
    }

    private WebElement waitForClickable(WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", element);
    }

    private void safeSendKeys(WebElement element, String value) {
        WebElement el = waitForVisible(element);
        el.clear();
        el.sendKeys(value);
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


    public void setFirstName(String fName) {
        safeSendKeys(txtFirstName, fName);
    }

    public void setLatName(String lName) {
        safeSendKeys(txtLastName, lName);
    }

    public void setEmail(String email) {
        safeSendKeys(txtEmail, email);
    }

    public void setTelephone(String tNo) {
        safeSendKeys(txtTelephone, tNo);
    }

    public void setPassword(String pwd) {
        safeSendKeys(txtPassword, pwd);
    }

    public void setConfirmPassword(String confirmpwd) {
        safeSendKeys(txtConfirmPassword, confirmpwd);
    }

    public void setChkBox() {
        safeClick(chkPolicy);
    }

    public void clickbthContinue() {
        safeClick(btnContinue);
    }

    public String getConfirmationMsg() {
        try {
            WebElement el = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOf(msgConfirmation));
            return el.getText();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}