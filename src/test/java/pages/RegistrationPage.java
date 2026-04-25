package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage {

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@id='input-firstname']")
    private WebElement txtFirstName;

    @FindBy(xpath = "//input[@id='input-lastname']")
    private WebElement txtLastName;

    @FindBy(xpath = "//input[@id='input-email']")
    private WebElement txtEmail;

    @FindBy(xpath = "//input[@id='input-telephone']")
    private WebElement txtTelephone;

    @FindBy(xpath = "//input[@id='input-password']")
    private WebElement txtPassword;

    @FindBy(xpath = "//input[@id='input-confirm']")
    private WebElement txtConfirmPassword;

    @FindBy(xpath = "//input[@name='agree']")
    private WebElement chkPolicy;

    @FindBy(xpath = "//input[@value='Continue']")
    private WebElement btnContinue;

    @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
    private WebElement msgConfirmation;

    public void setFirstName(String fName) {
        txtFirstName.sendKeys(fName);
    }

    public void setLatName(String lName) {
        txtLastName.sendKeys(lName);
    }

    public void setEmail(String email) {
        txtEmail.sendKeys(email);
    }

    public void setTelephone(String tNo) {
        txtTelephone.sendKeys(tNo);
    }

    public void setPassword(String pwd) {
        txtPassword.sendKeys(pwd);
    }

    public void setConfirmPassword(String confirmpwd) {
        txtConfirmPassword.sendKeys(confirmpwd);
    }

    public void setChkBox() {
        chkPolicy.click();
    }

    public void clickbthContinue() {
        btnContinue.click();
    }

    public String getConfirmationMsg() {
        try {
            waitVisible(msgConfirmation, Duration.ofSeconds(10));
            return msgConfirmation.getText();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}

