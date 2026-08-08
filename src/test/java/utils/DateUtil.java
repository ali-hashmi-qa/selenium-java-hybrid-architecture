package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public static String getTimeStamp() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
		
		return sdf.format(new Date());
	}

}
