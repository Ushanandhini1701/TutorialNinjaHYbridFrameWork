package com.testng.testcase;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.testng.base.Initialisebase;
import com.testng.utils.ExcelUtils;
import com.testng.utils.ExcelUtilss;
import com.testng.utils.UtilsClass;

import PageObjects.AccountSuccessPage;
import PageObjects.HomePage;
import PageObjects.RegisterPage;

public class RegisterTest extends Initialisebase {
	
	String path = "C:\\Users\\Admin\\java workspace\\TestngHybridFramework\\src\\test\\java\\com\\testng\\testdata\\TutorialsTestdata.xlsx";
		
	
	public RegisterTest(){
		super();
	}
	public WebDriver driver;
	HomePage hp = new HomePage(driver);
	RegisterPage rp;
	AccountSuccessPage acc = new AccountSuccessPage(driver);


	@BeforeMethod(groups= {"sanity","Master","Regression"})
	public void setup() {
		driver = initialiseBrowserAndOpenApplication(pro.getProperty("broswer"));
		HomePage hp = new HomePage(driver);
		 rp= hp.navigatoRegisterpage();

	}
	
	@AfterMethod(groups= {"sanity","Master","Regression"})
	public void teardown() {
		if (driver != null) {
            driver.quit();
        }
	}
	
	@Test(priority=1,groups="Regression")
	public void validmandatoryfields() {
	try {
		logger.info("***** Starting AccountSuccessPage  Test Case****");
		AccountSuccessPage acc = new AccountSuccessPage(driver);
		logger.info("*****Registering With Mandatory Fields *****");
		rp.RegisterWithMandatoryFields(ExcelUtilss.getCellData(path, "Register", 0, 1), datapro.getProperty("lname"), UtilsClass.getEmailTimeStampString(), datapro.getProperty("telephone"), pro.getProperty("vaidPassword"));
		Assert.assertEquals(acc.AccountSuccessMsg(), "Congratulations! Your new account has been successfully created!");
		
	}
	catch(Exception e) {
		logger.error("Test Failed");
		logger.debug("Debug logs");
		Assert.fail();
	}
	
	}
	@Test(priority=2)
	public void validselectingallfields() {
	try {
		AccountSuccessPage acc = new AccountSuccessPage(driver);
		logger.info("*****Registering With All Fields *****");
		rp.RegisterWithAllFields(ExcelUtils.getCellData(path, "Register", 1, 0), datapro.getProperty("lname"), UtilsClass.getEmailTimeStampString(), datapro.getProperty("telephone"), pro.getProperty("vaidPassword"));
		Assert.assertEquals(acc.AccountSuccessMsg(), "Congratulations! Your new account has been successfully created!");
	}
	catch(Exception e) {
		logger.error("Test Failed");
		logger.debug("Debug logs");
		Assert.fail();
	}
	}
	
	
	
	@Test(priority=3)
	public void validexistingemailaddressfields() {
	try {
		AccountSuccessPage acc = new AccountSuccessPage(driver);
		logger.info("*****Registering With Existing Fields *****");
		rp.enterfnametxt(datapro.getProperty("fname"));
		rp.enterlnametxt(datapro.getProperty("lname"));
		rp.enteremailtxt(pro.getProperty("validEmail"));
		rp.entertelephonetxt(datapro.getProperty("telephone"));
		rp.enterfpswdtxt(pro.getProperty("vaidPassword"));
		rp.enterconfirmpswdtxt(pro.getProperty("vaidPassword"));
		rp.clickonPrivacyPolicy();
		rp.clickOnYesNewsLetterOption();
		rp.clickOnContinueBtn();
		String warningmsg= rp.existingemailwarningmsg();
		Assert.assertTrue(warningmsg.contains("Warning: E-Mail Address is already registered!"), warningmsg);
	}
	catch(Exception e) {
		logger.error("Test Failed");
		logger.debug("Debug logs");
		Assert.fail();
	}
}
	
	@Test(priority=4)
	public void validwithoutfillinganyfield() {
		try {
		AccountSuccessPage acc = new AccountSuccessPage(driver);
		logger.info("*****Registering Without Filling All Mandatory Fields *****");
		rp.clickOnContinueBtn();
		Assert.assertTrue(rp.displayStatusOfWarningMessage("Warning: You must agree to the Privacy Policy!", "First Name must be between 1 and 32 characters!", "Last Name must be between 1 and 32 characters!", "E-Mail Address does not appear to be valid!", "Telephone must be between 3 and 32 characters!", "Password must be between 4 and 20 characters!"));
		
		
	
	}
		catch(Exception e) {
			logger.error("Test Failed");
			logger.debug("Debug logs");
			Assert.fail();
		}
	}
	
	

}
