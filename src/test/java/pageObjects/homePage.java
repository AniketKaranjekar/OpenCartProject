package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class homePage extends basePage
{
	public homePage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//span[normalize-space()='My Account']") WebElement linkMyAccount;
	@FindBy(xpath="//a[normalize-space()='Register']") WebElement linkRegister;
	@FindBy(xpath="//a[normalize-space()='Login']") WebElement linkLogin;
	@FindBy(xpath="//input[@placeholder='Search']") WebElement searchField;
	@FindBy(xpath="//button[@class='btn btn-default btn-lg']") WebElement searchIcon;
	
	public void clickLinkMyAccount()
	{
		linkMyAccount.click();
	}
	
	public void clickLinkRegister()
	{
		linkRegister.click();
	}
	
	public void clickBtnLogin()
	{
		linkLogin.click();
	}
	
	public void txtSearchField(String productName)
	{
		searchField.sendKeys(productName);
	}
	
	public void clickSearchIcon()
	{
		searchIcon.click();
	}
}
