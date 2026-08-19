package constants;

import java.io.File;

public class FrameworkConstants {
	
	public static final String User_Dir = System.getProperty("user.dir");
	
	public static final String Config_File_Path = User_Dir + File.separator + 
            "src" + File.separator + "test" + File.separator + "resources" + File.separator + "config" + File.separator + "config.properties";
    
    public static final String Test_Output_Folder = User_Dir + File.separator + "test-output" + File.separator;
    
    public static final String Extent_Report_Folder = Test_Output_Folder + "extent-reports" + File.separator;
    
    public static final String Extent_Report_Name = "extent-report";
    
    public static final String Report_Extension = ".html";
    
    public static final String Screenshots_Folder = Test_Output_Folder + "screenshots" + File.separator;
    
    public static final String Logs_Folder = Test_Output_Folder + "logs" + File.separator;
    
    public static final String test_data = User_Dir + File.separator + "src" + File.separator + 
            "test" + File.separator + "resources" + File.separator + "testdata" + File.separator + "LoginData.xlsx";
	
}
