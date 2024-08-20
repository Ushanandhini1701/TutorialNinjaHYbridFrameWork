package com.testng.testcase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.testng.base.Initialisebase;

import PageObjects.HomePage;
import PageObjects.SearchPage;

public class SearchTest extends Initialisebase {
	
	

	SearchPage sp;
	HomePage hp;
	public SearchTest() {
		super();
	}
	
	public WebDriver driver;
	
	@BeforeMethod(groups= {"sanity","Master","Regression"})
	public void setup() {
		driver = initialiseBrowserAndOpenApplication(pro.getProperty("broswer"));
		hp = new HomePage(driver);
	}
	
	
	@AfterMethod(groups= {"sanity","Master","Regression"})
	public void tear() {
		if (driver != null) {
            driver.quit();
        }
	}
		
	@Test(priority=1,groups= {"sanity","Master"})
	public void verifySearchWithValidProduct() {
		try {
			logger.info("*****Searching With Valid Product *****");
		sp=sp=hp.searchforProduct("HP");
		Assert.assertTrue(sp.searchfieldfound(), "product is available");
	}
	catch(Exception e) {
		logger.error("Test Failed");
		logger.debug("Debug logs");
		Assert.fail();
	}
}
	
	@Test(priority=2)
	public void verifySearchWithInValidProduct() {
		try {
			logger.info("*****Searching With InValid Product *****");	
		sp=hp.searchforProduct("Honda");
		Assert.assertEquals(sp.invalidproductfound(), "There is no product that matches the search criteria..");
	}
		catch(Exception e) {
			logger.error("Test Failed");
			logger.debug("Debug logs");
			Assert.fail();
		}
	}
	
	@Test(priority=3,dependsOnMethods= "verifySearchWithInValidProduct")
	public void verifySearchWithoutAnyProduct() {
		try {
			logger.info("*****Searching Without Entering Any Product *****");
		sp=hp.clickOnSearch();
		Assert.assertEquals(sp.invalidproductfound(), "There is no product that matches the search criteria.");
	}
		catch(Exception e) {
			logger.error("Test Failed");
			logger.debug("Debug logs");
			Assert.fail();
		}
	}
	
	
	
	

}
