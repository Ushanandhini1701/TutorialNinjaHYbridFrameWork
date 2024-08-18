package com.testng.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.apache.commons.lang3.RandomStringUtils;



public class UtilsClass {
	
	public static final int Implicit_Wait=10;
	public static final int PageLoadTimeout=10;
	
	
	private static String datetimestamp;

public static  String generatetimestamp() {
		
		Date date = new Date();
		datetimestamp=  date.toString().replace(" ", " ").replace(":", " ");
		return datetimestamp + "@gmail.com";
	}

public static String getEmailTimeStampString() {
	 String randomutils= RandomStringUtils.randomAlphanumeric(8) + "@gmail.com";
	 return randomutils;
	 }


public static Object[][] getTestDataFormat() {
	File excelfile = new File("C:\\Users\\Admin\\java workspace\\TestngHybridFramework\\src\\test\\java\\com\\testng\\testdata\\TutorialsTestdata.xlsx");
	XSSFWorkbook workbook= null;
	  
	try {
		FileInputStream file = new  FileInputStream(excelfile);
		 workbook = new XSSFWorkbook(file);
	    
	} 
	catch (Throwable e) {
		
		e.printStackTrace();
	}
	XSSFSheet sheet = workbook.getSheetAt(0);
	int rows = sheet.getLastRowNum();
	int column = sheet.getRow(0).getLastCellNum();
	 Object[][] data = new Object[rows][column];
	 for(int i=0;i<rows;i++) {
		 XSSFRow row = sheet.getRow(i+1);
		 
		for(int j=0;j<column;j++) {
			
			XSSFCell  cell = row.getCell(j);
			CellType celltype= cell.getCellType();
			
			switch(celltype) {
			
			case STRING:
				data[i][j]= cell.getStringCellValue();
				break;
				
			case NUMERIC:
				data[i][j]=Integer.toString((int)cell.getNumericCellValue());
				break;
			case BOOLEAN:
				data[i][j]= cell.getBooleanCellValue();
				break;
				}
		}
	 }
	return data;
	
	
	
	
}

public static String capturedScreenshot(WebDriver driver,String testname) {
	String destination = null;
	 if (driver != null) {
		 File srcfile =	((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	destination = System.getProperty("user.dir") + "\\Screenshots\\" +testname + ".png";
   try {
	FileHandler.copy(srcfile, new File(destination));
} catch (IOException e) {
	
	e.printStackTrace();
}
   
}
	
	return destination;
}


public static String captureScreenshot(WebDriver driver, String testname) {
    String destination=null;
	if (driver != null) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        destination = System.getProperty("user.dir") + "\\Screenshots\\" + testname+ ".png";
        try {
        	FileHandler.copy(screenshot, new File("path/to/screenshots/" + testname + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    } else {
        System.err.println("Driver is null, cannot capture screenshot.");
    }
	return destination;
}
}
