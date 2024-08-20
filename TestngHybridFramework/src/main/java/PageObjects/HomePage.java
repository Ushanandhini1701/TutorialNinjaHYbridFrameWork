package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {
	
	public HomePage(WebDriver driver) {
		super(driver);
		
	}
	
	
	//objects
	@FindBy(xpath="//span[text()='My Account']")
	private WebElement myAccountdrpdown;
	
	@FindBy(xpath="//*[text()='Login']")
	private WebElement loginOption;
	
	@FindBy(linkText="Register")
	WebElement registerdrpdown;
	
	@FindBy(name="search")
	WebElement searchtxt;
	
	@FindBy(xpath="//button[@class='btn btn-default btn-lg']")
	WebElement searchbtn;
	
	public SearchPage clickOnSearch() {
		searchbtn.click();
		return new SearchPage(driver);
	}
	
	public void entersearchtxt(String search) {
		searchtxt.sendKeys(search);
		
	}
	
	
	public void clickOnMyAccount() {
		myAccountdrpdown.click();
	}
	
	public RegisterPage clickOnregisterdrpdown() {
		registerdrpdown.click();
		return new RegisterPage(driver);
	}
	
	public LoginPage clickOnloginOption() {
		loginOption.click();
		return new LoginPage(driver);
	}
	 public LoginPage navigatologionpage() {
		 myAccountdrpdown.click();
		 loginOption.click();
			return new LoginPage(driver);
		 
	 }
	
	 public RegisterPage navigatoRegisterpage() {
		 myAccountdrpdown.click();
		 registerdrpdown.click();
		 return new RegisterPage(driver);
		 
	 }
	 
	 public SearchPage searchforProduct(String search) {
			searchtxt.sendKeys(search);
			searchbtn.click();
			return new SearchPage(driver);
		 
	 }
	

}
