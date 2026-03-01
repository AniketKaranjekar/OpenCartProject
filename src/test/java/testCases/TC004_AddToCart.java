package testCases;

import org.testng.annotations.Test;

import pageObjects.searchPage;
import pageObjects.homePage;

public class TC004_AddToCart extends baseClass 
{
	@Test
	public void addTocart()
	{
		homePage hp=new homePage(driver);
		hp.txtSearchField(p.getProperty("searchProductName"));
		hp.clickSearchIcon();
		searchPage sp=new searchPage(driver);
		sp.clickAddToCart();
	}
}