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
	
	
	@BeforeMethod
	public void setup() {
		
		driver = initialiseBrowserAndOpenApplication(pro.getProperty("broswer"));
		hp = new HomePage(driver);
		ap = new AccountPage(driver);
		lp = hp.navigatologionpage();
	}
	
	@AfterMethod
	public void teardown() {
		if (driver != null) {
            driver.quit();
        }
	}
	
	@Test(priority=1,dataProvider="supplydata") // if we are give data provider name then we have to give same or else we can give method name as parameter
	public void verifylogincredentials(String email,String pswd) {
	
	
		lp.entervalidlogindetails(email, pswd);
		Assert.assertTrue(ap.getDisplayStatusEditAccountInfo());
		}
	
	@DataProvider(name="supplydata") 
	public Object[][] supplydata(){
		
		Object[][] data = UtilsClass.getTestDataFormat();
		return data;
		}
				
				
		
	
	
	@Test(priority=2)
	public void verifyInvalidlogincredentials() {
		
		lp.entervalidlogindetails(UtilsClass.generatetimestamp(), datapro.getProperty("invalidPassword"));
		Assert.assertTrue(lp.retrivewarningmsg().contains(datapro.getProperty("emailpasswordwarning")));
		
	}
	
	@Test(priority=3)
	public void verifyinvalidemailandvalidpassword() {
		
		
		lp.entervalidlogindetails(UtilsClass.generatetimestamp(), datapro.getProperty("validpassword"));
		Assert.assertTrue(lp.retrivewarningmsg().contains(datapro.getProperty("emailpasswordwarning")));
		
	}
	
	@Test(priority=4)
	public void verifyinvalidemailandinvalidpassword() {
		lp.entervalidlogindetails(UtilsClass.generatetimestamp(), datapro.getProperty("invalidPassword"));
		String actualwarningtxt=lp.retrivewarningmsg();
		String expectedwarningtxt=datapro.getProperty("emailpasswordwarning");
		Assert.assertTrue(actualwarningtxt.contains(expectedwarningtxt));
		
		
	}
	
	@Test(priority=5)
	public void verifyemptytxt() {
	
		lp.clickloginbtn();
		String actualwarningtxt=lp.retrivewarningmsg();
		String expectedwarningtxt=datapro.getProperty("emailpasswordwarning");
		Assert.assertTrue(actualwarningtxt.contains(expectedwarningtxt));
		
	
		
	}
	
	

}
