package testCases;

import org.testng.annotations.Test;

import pageObjects.homePage;

public class TC003_SearchProduct extends baseClass 
{
	@Test
	public void search()
	{
		homePage hp=new homePage(driver);
		hp.txtSearchField(p.getProperty("searchProductName"));
		hp.clickSearchIcon();
	}
}