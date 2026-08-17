package utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import constants.FrameworkConstants;

public class ExcelUtil {
	
	public static Object[][] getTestData(String sheetName) {
	String excelFilePath = FrameworkConstants.test_data;
	Object[][] data = null;
	try (FileInputStream fis = new FileInputStream(new File(excelFilePath));
		Workbook workbook = WorkbookFactory.create(fis)) {
		Sheet sheet = workbook.getSheet(sheetName);
		int rowCount = sheet.getPhysicalNumberOfRows()-1 ;
		int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
		data = new Object[rowCount][colCount];
		DataFormatter formatter = new DataFormatter();
		for (int i=1; i<=rowCount; i++) {
			Row row = sheet.getRow(i);
			for (int j=0; j<colCount; j++) {
				Cell cell = row.getCell(j);
				data[i-1][j] = formatter.formatCellValue(cell);
			}
		}		
	} catch (IOException e) {
		e.printStackTrace();
	}
	return data;	
	}
}
