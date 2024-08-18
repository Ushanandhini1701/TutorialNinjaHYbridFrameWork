package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends BasePage{
	
	public SearchPage(WebDriver driver) {
		super(driver);
		
	}
	
	@FindBy(linkText="HP LP3065")
	WebElement searchfieldfound;
	
	@FindBy(xpath="//div[@id='content']//child::p[2]")
	WebElement invalidProduct;
	
	public boolean searchfieldfound() {
		return searchfieldfound.isDisplayed();
		
	}
	
	public String invalidproductfound() {
		String invalidwarning = invalidProduct.getText();
		return invalidwarning;
		
	}
	
	

}
