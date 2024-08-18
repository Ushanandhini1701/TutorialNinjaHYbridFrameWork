package com.testng.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;

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
import com.testng.utils.UtilsClass;
import experiment.ExtentReport;

public class MyListeners  implements ITestListener{
	
	
	ExtentReports extentReport;
	ExtentTest extentTest;
	String testname;
	@Override
	public void onStart(ITestContext context) {
		
		
		try {
			 extentReport = ExtentReport.generateReporter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void onTestStart(ITestResult result) {
		 testname=result.getName();
		ExtentTest extentTest=extentReport.createTest(testname);
		extentTest.log(Status.INFO,testname + "Started Executing" );
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
	
		extentTest.log(Status.PASS, testname + "got Successfully Executed");

			}
	
	

	@Override
	public void onTestFailure(ITestResult result) {
		WebDriver driver = null;
		 if (extentTest != null) {
	            String screenshotPath = UtilsClass.captureScreenshot(driver, testname);
				extentTest.fail("Test Failed").addScreenCaptureFromPath(screenshotPath);
	        } else {
	            System.err.println("ExtentTest is null, cannot add screenshot.");
	        }
	
//		WebDriver driver = null;
//		try {
//			 driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
//		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
//			
//			e.printStackTrace();
//		}

		

	//		String destination = UtilsClass.capturedScreenshot(driver, testname);
	   //extentTest.addScreenCaptureFromPath(destination); //it will attach the captured screenshot
//	   extentTest.log(Status.INFO, result.getThrowable());
//	   extentTest.log(Status.FAIL, testname + "its got failed");
	
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, testname + "got Skipped");
		}

	

	@Override
	public void onFinish(ITestContext context) {
		if (extentReport != null) {
			extentReport.flush();
        }
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
