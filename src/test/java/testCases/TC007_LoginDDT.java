package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.MyAccountPage;
import pageObjects.homePage;
import pageObjects.loginPage;
import utilities.DataProviders;


/*Data is valid  - login success - test pass  - logout
 					login failed - test fail

Data is invalid - login success - test fail  - logout
 					login failed - test pass
*/


public class TC007_LoginDDT extends baseClass {

	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class,groups="Datadriven")// getting data provider from different class
	public void verify_loginDDT(String email, String pwd, String exp) throws InterruptedException
	{
		logger.info("***** stating TC_003_LoginDDT ******");
		
		try
		{
		//HomePage
		homePage hp=new homePage(driver);
		hp.clickLinkMyAccount();
		hp.clickBtnLogin();
		
		//Login
		loginPage lp=new loginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLogin();
			
		//MyAccount
		MyAccountPage macc=new MyAccountPage(driver);
		boolean targetPage=macc.isMyAccountPageExist();
		
		
		if(exp.equalsIgnoreCase("Valid"))
		{
			if(targetPage==true)
			{			
				macc.clickLogout();
				Assert.assertTrue(true);
				
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		
		if(exp.equalsIgnoreCase("Invalid"))
		{
			if(targetPage==true)
			{
				macc.clickLogout();
				Assert.assertTrue(false);
				
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		
		}catch(Exception e)
		{
			Assert.fail();
		}
		Thread.sleep(3000);
		logger.info("***** Finished TC_003_LoginDDT ******");
		
	}
	
}