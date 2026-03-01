package testCases;

import org.testng.annotations.Test;

import pageObjects.homePage;
import pageObjects.loginPage;
import pageObjects.searchPage;

public class TC005_WishList extends baseClass
{
	@Test
	public void wishList()	
	{
		homePage hp=new homePage(driver);
		hp.clickLinkMyAccount();
		hp.clickBtnLogin();
		
		loginPage lp=new loginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		
		homePage hp1=new homePage(driver);
		hp1.txtSearchField(p.getProperty("searchProductName"));
		hp1.clickSearchIcon();
		
		searchPage sp=new searchPage(driver);
		sp.clickWishList();
		sp.clickLinkWishList();
		
		
		
	}
}
