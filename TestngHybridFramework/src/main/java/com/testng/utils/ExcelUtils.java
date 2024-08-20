package com.testng.utils;



import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {
	public static  FileInputStream fis = null;
    public static  XSSFWorkbook workbook = null;
    public static  XSSFSheet sheet = null;
    public static  XSSFRow row = null;
    public static  XSSFCell cell = null;
   public static  String cellData = null;

    public static String getCellData(String fileName, String sheetName, int rowNum, int colNum) {
       
        try {
            // Open the Excel file
            fis = new FileInputStream(fileName);
            workbook = new XSSFWorkbook(fis);

            // Get the desired sheet from the workbook
            sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet '" + sheetName + "' does not exist in " + fileName);
            }

            // Get the desired row from the sheet
            row = sheet.getRow(rowNum);
            if (row == null) {
                throw new IllegalArgumentException("Row " + rowNum + " does not exist in sheet " + sheetName);
            }

            // Get the desired cell from the row
         cell = row.getCell(colNum);
            if (cell == null) {
                throw new IllegalArgumentException("Cell at row " + rowNum + " and column " + colNum + " does not exist");
            }

            // Get the cell data based on the cell type
            switch (cell.getCellType()) {
                case STRING:
                    cellData = cell.getStringCellValue();
                    break;
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        cellData = cell.getDateCellValue().toString();
                    } else {
                        cellData = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                case BOOLEAN:
                    cellData = String.valueOf(cell.getBooleanCellValue());
                    break;
                case FORMULA:
                    cellData = cell.getCellFormula();
                    break;
                case BLANK:
                    cellData = "";
                    break;
                default:
                    cellData = "Unsupported cell type";
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (workbook != null) {
                    workbook.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        return cellData;
    }

    public static void main(String[] args) {
   
        String fileName = "C:\\Users\\Admin\\java workspace\\TestngHybridFramework\\src\\test\\java\\com\\testng\\testdata\\TutorialsTestdata.xlsx";
        String sheetName = "Register";
        int rowNum = 1;
        int colNum = 0;

        String data = getCellData(fileName, sheetName, rowNum, colNum);
        System.out.println("Cell Data: " + data);
    }
}

