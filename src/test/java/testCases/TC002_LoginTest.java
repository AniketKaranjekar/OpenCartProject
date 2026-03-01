package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.MyAccountPage;
import pageObjects.homePage;
import pageObjects.loginPage;

public class TC002_LoginTest extends baseClass
{
	@Test
	public void login()
	{
		logger.info("*****Starting TC_002_LoginTest*****");
		
		try
		{
			homePage hp= new homePage(driver);
			hp.clickLinkMyAccount();
			hp.clickBtnLogin();
			
			loginPage lp=new loginPage(driver);
			lp.setEmail(p.getProperty("email"));
			lp.setPassword(p.getProperty("password"));
			lp.clickLogin();
			
			MyAccountPage macc=new MyAccountPage(driver);
			boolean targetPage=macc.isMyAccountPageExist();
			Assert.assertTrue(targetPage);
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("*****Finished TC_002_LoginTest*****");
	}
}