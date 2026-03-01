package testCases;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.homePage;
import pageObjects.registerPage;

public class TC001_AccountRegistrationTest extends baseClass
{	
	@Test
	public void accountRegister()
	{
		logger.info(" *****Starting TC001_AccountRegistrationTest***** ");
		try
		{
			homePage hp=new homePage(driver);
			hp.clickLinkMyAccount();
			logger.info(" *****Click on my account***** ");

			hp.clickLinkRegister();
			logger.info(" *****Click on register***** ");

			registerPage regpage= new registerPage(driver);
			regpage.setFirstName(randomString().toUpperCase());
			regpage.setLatName(randomString().toUpperCase());
			regpage.setEmail(randomString()+"@gmail.com");
			regpage.setTelephone(randomNumber());
		
			String password=randomAlphanumeric();
			regpage.setPassword(password);
			regpage.setConfirmPassword(password);
			regpage.setChkBox();
			regpage.clickbthContinue();
		
			String confmsg=regpage.getConfirmationMsg();
			Assert.assertEquals(confmsg,"Your Account Has Been Created!");
		}
		catch(Exception e)
		{
			logger.error("Test Failed...");
			logger.debug("Debug Log...");
			Assert.fail();
		}
		logger.info(" *****Finished TC001_AccountRegistrationTest***** ");
	}
}
