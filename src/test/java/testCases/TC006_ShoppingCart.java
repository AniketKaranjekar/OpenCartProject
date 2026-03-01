package testCases;

import org.testng.annotations.Test;

import pageObjects.homePage;
import pageObjects.loginPage;
import pageObjects.searchPage;

public class TC006_ShoppingCart extends baseClass
{
	@Test
	public void cart()
	{
		homePage hp=new homePage(driver);
		hp.clickLinkMyAccount();
		hp.clickBtnLogin();
		
		loginPage lp=new loginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		
		hp.txtSearchField(p.getProperty("searchProductName"));
		hp.clickSearchIcon();
		
		searchPage sp=new searchPage(driver);
		sp.clickAddToCart();
		sp.clickShoppingCart();
	}
}