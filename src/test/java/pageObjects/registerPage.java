package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class registerPage extends basePage
{
	public registerPage(WebDriver driver) 
	{
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-firstname']") WebElement txtFirstName;
	@FindBy(xpath="//input[@id='input-lastname']") WebElement txtLastName;
	@FindBy(xpath="//input[@id='input-email']") WebElement txtEmail;
	@FindBy(xpath="//input[@id='input-telephone']") WebElement txtTelephone;
	@FindBy(xpath="//input[@id='input-password']") WebElement txtPassword;
	@FindBy(xpath="//input[@id='input-confirm']") WebElement txtConfirmPassword;
	@FindBy(xpath="//input[@name='agree']") WebElement chkPolicy;
	@FindBy(xpath="//input[@value='Continue']") WebElement bthContinue;
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")WebElement msgConfirmatoin;
	
	public void setFirstName(String fName)
	{
		txtFirstName.sendKeys(fName);
	}
	public void setLatName(String lName)
	{
		txtLastName.sendKeys(lName);
	}
	public void setEmail(String email)
	{
		txtEmail.sendKeys(email);
	}
	public void setTelephone(String tNo)
	{
		txtTelephone.sendKeys(tNo);
	}
	public void setPassword(String pwd) 	
	{
		txtPassword.sendKeys(pwd);
	}
	public void setConfirmPassword(String confirmpwd)
	{
		txtConfirmPassword.sendKeys(confirmpwd);
	}
	public void setChkBox()
	{
		chkPolicy.click();
	}
	public void clickbthContinue()
	{
		bthContinue.click();
	}
	public String getConfirmationMsg()
	{
		try
		{
			return(msgConfirmatoin.getText());
		}
		catch (Exception e)
		{
			return(e.getMessage());
		}
	}
}