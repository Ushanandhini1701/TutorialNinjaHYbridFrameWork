package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage  {
	
	
	
	public LoginPage(WebDriver driver) {
		super(driver);
		
	}
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement emailtxt;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement pswdtxt;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement loginbtn;
	
	@FindBy(xpath="//div[contains(@class,'alert alert-danger alert-dismissible')]")
	WebElement emailPswdDoNotMatching;
	
	public void enteremailtxt(String email) {
		emailtxt.sendKeys(email);
	}
	
	public void enterpswdtxt(String pswd) {
		pswdtxt.sendKeys(pswd);
	}
	
	public void clickloginbtn() {
		loginbtn.click();
	}
	
	public String retrivewarningmsg() {
		String warningtxt=emailPswdDoNotMatching.getText();
		return warningtxt;
		
	}
	
	public void entervalidlogindetails(String email,String pswd) {
		emailtxt.sendKeys(email);
		pswdtxt.sendKeys(pswd);
		loginbtn.click();
		
	}
	
	

}
