package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage extends BasePage {
	
	public AccountPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	WebDriver driver;
	@FindBy(linkText="Edit your account information")
	WebElement editaccountsuccessinfo;
	
	public boolean getDisplayStatusEditAccountInfo() {
		return editaccountsuccessinfo.isDisplayed();
	}

}
