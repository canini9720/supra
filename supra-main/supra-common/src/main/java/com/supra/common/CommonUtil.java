package com.supra.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {
	public static String getFormattedDatePattern(String dateStr, String fromFormat,String toFormat) throws ParseException{
		 Date fromDate=new SimpleDateFormat(fromFormat).parse(dateStr);
		 DateFormat dateFormat = new SimpleDateFormat(toFormat);  
		 String strDate = dateFormat.format(fromDate);  
		return strDate;  
	}
}
