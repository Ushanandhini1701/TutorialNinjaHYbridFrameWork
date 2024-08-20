package com.testng.testcase;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.testng.base.Initialisebase;
import com.testng.utils.UtilsClass;

import PageObjects.AccountPage;
import PageObjects.HomePage;
import PageObjects.LoginPage;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginTest extends Initialisebase {
	
	
	LoginPage lp;
	AccountPage ap;
	HomePage hp;
	
	public WebDriver driver;
	
	public LoginTest() {
		super();
	}
	
	
	@BeforeMethod(groups= {"sanity","Master","Regression"})  // eventhough we extend baseclass we need to mention grouping here also are else it won't invoke the browser
	public void setup() {
		
		driver = initialiseBrowserAndOpenApplication(pro.getProperty("broswer"));
		logger.info("***** Starting  Home Test Case");
		hp = new HomePage(driver);
		logger.info("***** Starting  Account Test Case");
		ap = new AccountPage(driver);
		logger.info("***** Navigating to Login Page");
		lp = hp.navigatologionpage();
	}
	
	@AfterMethod(groups= {"sanity","Master","Regression"})
	public void teardown() {
		if (driver != null) {
            driver.quit();
        }
	}
	
	@Test(priority=1,dataProvider="supplydata") // if we are give data provider name then we have to give same or else we can give method name as parameter
	public void verifylogincredentials(String email,String pswd) {
	try {
		logger.info("***** Providing valid login Details");
		lp.entervalidlogindetails(email, pswd);
		Assert.assertTrue(ap.getDisplayStatusEditAccountInfo());
	}
	catch(Exception e) {
		logger.error("Test Failed");
		logger.debug("Debug logs");
		Assert.fail();
	}
		}
	
	
	@DataProvider(name="supplydata") 
	public Object[][] supplydata(){
		
		Object[][] data = UtilsClass.getTestDataFormat();
		return data;
		}
				
				
		
	
	
	@Test(priority=2,groups="sanity")
	public void verifyInvalidlogincredentials() {
		try {
		logger.info("***** Providing Invalid login Details Valid Email And Invalid Password");
		lp.entervalidlogindetails(UtilsClass.generatetimestamp(), datapro.getProperty("invalidPassword"));
		Assert.assertTrue(lp.retrivewarningmsg().contains(datapro.getProperty("emailpasswordwarning")));
		}
		catch(Exception e) {
			logger.error("Test Failed");
			logger.debug("Debug logs");
			Assert.fail();
		}
	}
	
	@Test(priority=3)
	public void verifyinvalidemailandvalidpassword() {
		try {
		logger.info("***** Providing invalid login Details invalid email and valid password");
		lp.entervalidlogindetails(UtilsClass.generatetimestamp(), datapro.getProperty("validpassword"));
		Assert.assertTrue(lp.retrivewarningmsg().contains(datapro.getProperty("emailpasswordwarning")));
		}
		catch(Exception e) {
			logger.error("Test Failed");
			logger.debug("Debug logs");
			Assert.fail();
		}
		
	}
	
	@Test(priority=4)
	public void verifyinvalidemailandinvalidpassword() {
		try {
			logger.info("***** Providing valid login Details with invalidemail and invalidpassword");	
		lp.entervalidlogindetails(UtilsClass.generatetimestamp(), datapro.getProperty("invalidPassword"));
		String actualwarningtxt=lp.retrivewarningmsg();
		String expectedwarningtxt=datapro.getProperty("emailpasswordwarning");
		Assert.assertTrue(actualwarningtxt.contains(expectedwarningtxt));
		}catch(Exception e) {
			logger.error("Test Failed");
			logger.debug("Debug logs");
			Assert.fail();
		}
		
	}
	
	@Test(priority=5)
	public void verifyemptytxt() {
	try {
		logger.info("*****Clicking Login Button without enterinh any Fields *****");
		lp.clickloginbtn();
		String actualwarningtxt=lp.retrivewarningmsg();
		String expectedwarningtxt=datapro.getProperty("emailpasswordwarning");
		Assert.assertTrue(actualwarningtxt.contains(expectedwarningtxt));
		
	}
	catch(Exception e) {
		logger.error("Test Failed");
		logger.debug("Debug logs");
		Assert.fail();
	}
		
	}
	
	

}
