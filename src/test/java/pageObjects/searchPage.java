package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class searchPage extends basePage
{
	public searchPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//span[normalize-space()='Add to Cart']") WebElement btnAddToCart;
	@FindBy(xpath="//button[@type='button']//i[@class='fa fa-heart']") WebElement btnWishList;
	@FindBy(xpath="//span[normalize-space()='Shopping Cart']") WebElement linkShoppingCart;
	@FindBy(xpath="//span[normalize-space()='Wish List (1)']") WebElement linkWishList;
	
	public void clickAddToCart()
	{
		btnAddToCart.click();
	}
	
	public void clickWishList()
	{
		btnWishList.click();
	}
	
	public void clickShoppingCart()
	{
		linkShoppingCart.click();
	}
	
	public void clickLinkWishList()
	{
		linkWishList.click();
	}
}