package utils;

import org.testng.annotations.DataProvider;

public class TestDataProviders {
	
	
	@DataProvider(name = "loginData")
	public static Object[][] loginDataProvider() {
		try {
			return ExcelUtil.getTestData(ConfigReader.get("sheetName"));			
	    } catch (Exception e) {
	        System.err.println(">>> ERROR in DataProvider:");
	        e.printStackTrace();
	        return new Object[0][0];      
	    }
	}
}
