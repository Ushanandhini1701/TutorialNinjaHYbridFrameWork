package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountSuccessPage  extends BasePage{
	
	public AccountSuccessPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	
	
	@FindBy(xpath="//*[contains(text(),'Congratulations! Your new account has been successfully created!')]")
	WebElement  AccountSuccessmsg;
	
	public String AccountSuccessMsg() {
		String accountsuccessmsg = AccountSuccessmsg.getText();
		return accountsuccessmsg;
	}
	
	

}
