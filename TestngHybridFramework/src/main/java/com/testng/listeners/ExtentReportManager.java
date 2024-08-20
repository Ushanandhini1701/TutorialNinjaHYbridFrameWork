package com.testng.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.testng.utils.UtilsClass;
import experiment.ExtentReport;

public class ExtentReportManager  implements ITestListener{
	
	public ExtentSparkReporter sparkReport;
	public ExtentReports extentreports;
	public ExtentTest extentTest;
	String repName;
	@Override
	public void onStart(ITestContext context) {
//		SimpleDateFormat df = new SimpleDateFormat();
//		Date dt = new Date();
//		String CurrentDateformat=df.format(dt);
		
		String timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName="Test=Report-"+	timestamp + ".html";
		sparkReport= new ExtentSparkReporter(".\\reports\\"+repName);
		sparkReport.config().setDocumentTitle("Tutorials");
		sparkReport.config().setReportName("tutorials testing");
		sparkReport.config().setTheme(Theme.DARK);
		extentreports = new ExtentReports();
		Properties config = new Properties();
		extentreports.setSystemInfo("Application URL",config.getProperty("url"));
		extentreports.setSystemInfo("Browser Name", config.getProperty("browser"));
		extentreports.setSystemInfo("Email", config.getProperty("validEmail"));
		extentreports.setSystemInfo("Email", config.getProperty("vaidPassword"));
		extentreports.setSystemInfo("Operating System", System.getProperty("os.name"));
		extentreports.setSystemInfo("Username", System.getProperty("os.name"));
		extentreports.setSystemInfo("JavaVersion", System.getProperty("java.version"));
		
		
	}

	@Override
	public void onTestStart(ITestResult result) {
		 extentTest = extentreports.createTest(result.getTestClass().getName());
		
		extentTest.log(Status.INFO,result.getName() + "Started Executing" );
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		 extentTest = extentreports.createTest(result.getTestClass().getName());
		extentTest.log(Status.PASS, result.getName() + "got Successfully Executed");

			}
	
	

	@Override
	public void onTestFailure(ITestResult result) {
		 extentTest = extentreports.createTest(result.getTestClass().getName());
		 extentTest.log(Status.FAIL, result.getName() + "its got failed");
		 extentTest.log(Status.INFO, result.getThrowable());
		 try {
			 String imgPath= new UtilsClass().capturescreen(result.getName());
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		extentTest = extentreports.createTest(result.getTestClass().getName());
		extentTest.log(Status.SKIP, result.getName() + "got Skipped");
		extentTest.log(Status.INFO, result.getThrowable());
		}

	

	@Override
	public void onFinish(ITestContext context) {
		
		extentreports.flush();
       
		String pathExtent = "C:\\Users\\Admin\\testng\\TESTNG\\test-output\\ExtentReport\\ExtentReports.html";
		File extentReport = new File(pathExtent);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
