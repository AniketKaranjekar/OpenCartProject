package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    public void clickLinkMyAccount()
    {
        linkMyAccount.click();
    }

    public void clickLinkRegister() {
        linkRegister.click();
    }

    public void clickBtnLogin() {
        linkLogin.click();
    }

    public void txtSearchField(String productName) {
        searchField.sendKeys(productName);
    }

    public void clickSearchIcon() {
        searchIcon.click();
    }
}

