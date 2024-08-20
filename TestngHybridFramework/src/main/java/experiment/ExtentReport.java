package experiment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport {
	

	public static ExtentReports generateReporter() throws IOException {
		
		ExtentReports extentreports = new ExtentReports();
		File extentreportfile = new File("C:\\Users\\Admin\\java workspace\\TestngHybridFramework\\test-output\\ExtentReport\\ExtentReports.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentreportfile);
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("TutorialsNJAAutomationResult");
		sparkReporter.config().setDocumentTitle("TN Automation Report");
		sparkReporter.config().setTimeStampFormat("dd/MM/yyy hh:mm:ss");
		extentreports.attachReporter(sparkReporter);  // format will apply to extend report
		
		Properties config = new Properties();
		File configfile = new File("src/test/resources/config.properties");
		FileInputStream fileip;
		try {
			fileip = new FileInputStream(configfile);
			config.load(fileip);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}

		extentreports.setSystemInfo("Application URL",config.getProperty("url"));
		extentreports.setSystemInfo("Browser Name", config.getProperty("browser"));
		extentreports.setSystemInfo("Email", config.getProperty("validEmail"));
		extentreports.setSystemInfo("Email", config.getProperty("vaidPassword"));
		extentreports.setSystemInfo("Operating System", System.getProperty("os.name"));
		extentreports.setSystemInfo("Username", System.getProperty("os.name"));
		extentreports.setSystemInfo("JavaVersion", System.getProperty("java.version"));
		
		return extentreports;
		
	
	}

}
