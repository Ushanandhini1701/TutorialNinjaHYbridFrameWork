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
	
	//commented

	SearchPage sp;
	HomePage hp;
	public SearchTest() {
		super();
	}
	
	public WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		driver = initialiseBrowserAndOpenApplication(pro.getProperty("broswer"));
		hp = new HomePage(driver);
	}
	
	
	@AfterMethod
	public void tear() {
		if (driver != null) {
            driver.quit();
        }
	}
		
	@Test(priority=1)
	public void verifySearchWithValidProduct() {
		sp=sp=hp.searchforProduct("HP");
		Assert.assertTrue(sp.searchfieldfound(), "product is available");
	}
	
	@Test(priority=2)
	public void verifySearchWithInValidProduct() {
		sp=hp.searchforProduct("Honda");
		Assert.assertEquals(sp.invalidproductfound(), "There is no product that matches the search criteria.");
	}
	
	@Test(priority=3,dependsOnMethods= {"verifySearchWithInValidProduct","verifySearchWithValidProduct"})
	public void verifySearchWithoutAnyProduct() {
		sp=hp.clickOnSearch();
		Assert.assertEquals(sp.invalidproductfound(), "There is no product that matches the search criteria.");
	}
	
	
	
	

}
