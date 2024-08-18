package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage  extends BasePage{
	
	public RegisterPage(WebDriver driver) {
		super(driver);
		
	}
	
	@FindBy(id="input-firstname")
	WebElement fnametxt;
	
	@FindBy(id="input-lastname")
	WebElement lnametxt;
	
	@FindBy(id="input-email")
	WebElement emailtxt;
	
	@FindBy(id="input-telephone")
	WebElement telephonetxt;
	
	@FindBy(id="input-password")
	WebElement pswdtxt;
	
	@FindBy(id="input-confirm")
	WebElement confirmpswdtxt;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement privacypolicy;
	
	@FindBy(xpath="//input[@name='newsletter'][@value='1']")
	WebElement YesNewsLetterOption;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement ContinueBtn;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	WebElement DuplicateWarningMsg;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	WebElement WarningPrivacyPolicyMsg;
	
	@FindBy(xpath="//input[@id='input-firstname']//following-sibling::div")
	WebElement FnameWarningMsg;
	
	@FindBy(xpath="//input[@id='input-lastname']//following-sibling::div")
	WebElement LnameWarningMsg;
	
	@FindBy(xpath="//input[@id='input-email']//following-sibling::div")
	WebElement EmailWarningMsg;
	
	@FindBy(xpath="//input[@id='input-telephone']//following-sibling::div")
	WebElement telephoneWarningMsg;
	
	@FindBy(xpath="//input[@id='input-password']//following-sibling::div")
	WebElement PasswordWarningMsg;
	
	@FindBy(xpath="//*[contains(text(),'Warning: E-Mail Address is already registered!')]")
	WebElement Existingemailwarning;
	
	public String existingemailwarningmsg() {
		
		String existingemailwarning =Existingemailwarning.getText();
		return existingemailwarning;
		
	}
	
	public void enterfnametxt(String fname) {
		fnametxt.sendKeys(fname);
		
	}
	
	public void enterlnametxt(String lname) {
		lnametxt.sendKeys(lname);
		
	}
	
	public void enteremailtxt(String email) {
		emailtxt.sendKeys(email);
		
	}
	
	public void entertelephonetxt(String telephone) {
		telephonetxt.sendKeys(telephone);
		
	}
	
	public void enterfpswdtxt(String pswd) {
		pswdtxt.sendKeys(pswd);
		
	}
	
	public void enterconfirmpswdtxt(String pswd) {
		confirmpswdtxt.sendKeys(pswd);
		
	}
	
	public void clickonPrivacyPolicy() {
		privacypolicy.click();
		
	}
	
	public void clickOnContinueBtn(){
		ContinueBtn.click();
		
	}
	
	public void clickOnYesNewsLetterOption(){
		YesNewsLetterOption.click();
		
	}
	
	public String duplicateWarningMsg() {
		String duplicatmsg=DuplicateWarningMsg.getText();
		return duplicatmsg;
	}
	
	public String PrivacyPolicyWarningMsg() {
		String PrivacyPolicyWarning=WarningPrivacyPolicyMsg.getText();
		return PrivacyPolicyWarning;
	}
	
	public String FnameWarningMsg() {
		String Fnamewarning=FnameWarningMsg.getText();
		return Fnamewarning;
	}
	
	public String LnameWarningMsg() {
		String Lnamewarning=LnameWarningMsg.getText();
		return Lnamewarning;
	}
	
	public String EmailWarningMsg() {
		String Emailwarning=EmailWarningMsg.getText();
		return Emailwarning;
	}
	
	public String telephoneWarningMsg() {
		String telephonewarning=telephoneWarningMsg.getText();
		return telephonewarning;
	}
	
	public String PasswordWarningMsg() {
		String Pswdwarning=PasswordWarningMsg.getText();
		return Pswdwarning;
	}
	
	public void RegisterWithMandatoryFields(String fname,String lname,String email,String telephone,String pswd) {
		fnametxt.sendKeys(fname);
		lnametxt.sendKeys(lname);
		emailtxt.sendKeys(email);
		telephonetxt.sendKeys(telephone);
		pswdtxt.sendKeys(pswd);
		confirmpswdtxt.sendKeys(pswd);
		privacypolicy.click();
		ContinueBtn.click();
		
	}
	
	public void RegisterWithAllFields(String fname,String lname,String email,String telephone,String pswd) {
		fnametxt.sendKeys(fname);
		lnametxt.sendKeys(lname);
		emailtxt.sendKeys(email);
		telephonetxt.sendKeys(telephone);
		pswdtxt.sendKeys(pswd);
		confirmpswdtxt.sendKeys(pswd);
		YesNewsLetterOption.click();
		privacypolicy.click();
		ContinueBtn.click();
		
	}
	
	public boolean displayStatusOfWarningMessage(String expectedPrivacyPolicyWarning,String expectedFitstnameWarning,String expectedLastnameWarning,String expectedEmailWarning,String expectedTelephoneWarning,String expectedPasswordWarning ) {

		boolean pricavypolicywarningstatus = WarningPrivacyPolicyMsg.getText().contains(expectedPrivacyPolicyWarning);
		boolean FirstnameWarningstatus = FnameWarningMsg.getText().contains(expectedFitstnameWarning);
		boolean lastnamewarningstatus = LnameWarningMsg.getText().contains(expectedLastnameWarning);
		boolean emailwarningstatus= EmailWarningMsg.getText().contains(expectedEmailWarning);
		boolean telephonewarningstatus= telephoneWarningMsg.getText().contains(expectedTelephoneWarning);boolean passwordwarningstatus= PasswordWarningMsg.getText().contains(expectedPasswordWarning);
		return pricavypolicywarningstatus && FirstnameWarningstatus && lastnamewarningstatus && emailwarningstatus && telephonewarningstatus && passwordwarningstatus;
	}
	
	

}
