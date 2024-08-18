package com.testng.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;

import com.testng.utils.UtilsClass;

public class Initialisebase {
	

	public WebDriver driver;
	public Properties pro;
	public Properties datapro;
	
	public Initialisebase() {
		pro=new Properties();
		File profile = new File("src/test/resources/config.properties");
		datapro=new Properties();
		File datafile = new File("src/test/resources/testdata.properties");
		
		
		try {
			FileInputStream file = new FileInputStream(profile);
			pro.load(file);
		}
		catch(Exception e){
			e.printStackTrace();
			e.getMessage();
			
		}
		try {
			FileInputStream filedata = new FileInputStream(datafile);
			datapro.load(filedata);
		}
		catch(Exception e){
			e.printStackTrace();
			e.getMessage();
			
		}
		
		
		
	}
	
	public WebDriver initialiseBrowserAndOpenApplication(String browser) {
		switch(browser.toLowerCase()) {
		case "chrome":
			driver= new ChromeDriver();
			break;
			
		case "firefox":
			driver = new FirefoxDriver();
			break;
			
		case "edge":
			driver = new EdgeDriver();
			break;
			
			default:
				
				break;
			}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(UtilsClass.Implicit_Wait));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(UtilsClass.PageLoadTimeout));
		driver.get(pro.getProperty("url"));
		return driver;

	}
}
