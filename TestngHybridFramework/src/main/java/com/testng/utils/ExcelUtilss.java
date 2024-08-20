package com.testng.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilss {
	
	public static FileInputStream fi;
	public FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public  static XSSFCell cell;
	public static CellStyle style;
	static String path;
	
	
	public ExcelUtilss(String path) {
		this.path=path;
	}
	
	
public static void main(String[] args) throws IOException {
	String filePath = "C:\\Users\\Admin\\java workspace\\TestngHybridFramework\\src\\test\\java\\com\\testng\\testdata\\TutorialsTestdata.xlsx";
	 int rowcount=ExcelUtilss.getRowCount(filePath);
	 System.out.println("File Path :"  + " " + filePath);
	 System.out.println(rowcount);
}
	public static int getRowCount(String filePath) throws IOException {
		
		if (filePath == null || filePath.isEmpty()) {
		    throw new IllegalArgumentException("File path is null or empty");
		}
		fi= new FileInputStream(filePath);
		wb = new XSSFWorkbook();
		sheet = wb.getSheetAt(1);
		int rowcount = sheet.getLastRowNum();
		wb.close();
		fi.close();
		return rowcount;
		
	}
	
	public int gellcellCount(String sheetName,int rownum) throws IOException {
		fi= new FileInputStream(path);
		wb = new XSSFWorkbook();
		sheet = wb.getSheet(sheetName);
		row = sheet.getRow(rownum);
		int cellCount = row.getLastCellNum();
		wb.close();
		fi.close();
		return cellCount;
		
	}
	
	public static String getCellData(String excelfile,String sheetName,int rownum,int colnum) throws IOException {
		fi= new FileInputStream(excelfile);
		wb = new XSSFWorkbook();
		sheet = wb.getSheet(sheetName);
		row = sheet.getRow(rownum);
		cell = row.getCell(colnum);
		String data;
		try {
			
			DataFormatter formatter = new DataFormatter();
			data= formatter.formatCellValue(cell);
			
		
		}
		catch(Exception e) {
			data="";
		}
		
		wb.close();
		fi.close();
		return data;
	}
	

}
