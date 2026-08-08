package constants;

public class FrameworkConstants {
	
	public static final String User_Dir = System.getProperty("user.dir");
	
	public static final String Config_File_Path = User_Dir + 
			"\\src\\test\\resources\\config\\config.properties";
	
	public static final String Test_Output_Folder = User_Dir + "\\test-output\\";
	
	public static final String Extent_Report_Folder = Test_Output_Folder + "extent-reports\\";
	
	public static final String Extent_Report_Name = "extent-report";
	
	public static final String Report_Extension = ".html";
	
	public static final String Screenshots_Folder = Test_Output_Folder + "screenshots\\";
	
	public static final String Logs_Folder = Test_Output_Folder + "logs\\";
	
}
