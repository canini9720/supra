package com.supra.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.axis.message.SOAPEnvelope;
import org.apache.log4j.Logger;
import org.apache.tika.Tika;
import org.hibernate.JDBCException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.google.common.net.InetAddresses;
import com.supra.common.CommonSupraConstants;
import com.supra.common.ExceptionSupraValidationsConstants;
import com.supra.common.dto.AttachmentSoapRequestDTO;
import org.apache.commons.codec.binary.Base64;
@Component
@DependsOn("ApplicationProperties")
public class CommonSupraUtil {
	
	private static final Logger logger = Logger.getLogger("DPServices");
	
	public static  String keyText = "";
	
	@PostConstruct
	public void initialize(){
		
		keyText = SystemProperties.props.get("encodeKey");
	}

    /**
     * to get Value of given tag of given Element
     * */
    public static String getTextValue(Element ele, String tagName) {
        String textVal = null;
        
        NodeList nl = null;
        
        if (ele.getElementsByTagNameNS("*",tagName) != null) {
        	
            nl = ele.getElementsByTagNameNS("*",tagName);
            
            if (nl != null && nl.getLength() > 0) {
            	
                Element el = (Element)nl.item(0);
                
                if (el.getFirstChild() != null){
                	
                    textVal = el.getFirstChild().getNodeValue();
                    
                }
                
            }
        }
        return textVal;
    }
    
    public static String getStringValue(String fieldName) {
		String result = "";
    	if(fieldName != null && !fieldName.equalsIgnoreCase("-1")){
    		return fieldName;
    	}
    	return result;
    }
    
    public static String getFormattedTrafficDate(String trafficDate){
    	
    	if(trafficDate != null && trafficDate.indexOf("T") > 0){
			
    		trafficDate = trafficDate.substring(0,trafficDate.indexOf("T"));
    		trafficDate = CommonSupraUtil.getFormattedDate(trafficDate);
    		
    		return trafficDate;
        }
    	
    	return trafficDate;
    }
    
    /**
     * Method to format license and vehicle relevant dates
     * @param dateToFormat
     * @return
     */
    public static String getFormattedDate(Object dateToFormat) {
    	
        String strDate = null;
        
        try {
        	
        	if(dateToFormat != null){
        		
	            SimpleDateFormat format2 = new SimpleDateFormat(CommonSupraConstants.DATE_yyyy_MM_dd,Locale.ENGLISH);
	            SimpleDateFormat format1 = new SimpleDateFormat(CommonSupraConstants.DATE_ddMMyyyy,Locale.ENGLISH);
	            
	            strDate = format1.format(format2.parse(dateToFormat.toString()));
        	}
            
        } catch (Exception e) {
        	
            
            
        }
        
        return strDate;
    }
    
    public static String getFormattedDateByPattern(Object dateToFormat, String fromPattern, String toPattern) {
    	
        String strDate = null;
        
        try {
        	
        	if(dateToFormat != null){
        		
	            SimpleDateFormat format1 = new SimpleDateFormat(toPattern,Locale.ENGLISH);
	            SimpleDateFormat format2 = new SimpleDateFormat(fromPattern,Locale.ENGLISH);
	            
	            strDate = format1.format(format2.parse(dateToFormat.toString()));
        	}
            
        } catch (Exception e) {
        	
            
            
        }
        
        return strDate;
    }
    
    /**
     * change date format base on the given pattern and date 
     * paramter
     * 
     * @param dateToFormat
     * @param pattern
     * @return
     */
 public static String getFormattedDatePattern(Object dateToFormat,String pattern) {
    	
        String strDate = null;
        
        try {
        	
        	if(dateToFormat != null){
        		
        		if(pattern.equalsIgnoreCase(CommonSupraConstants.DATE_yyyy_MM_dd)){
        			SimpleDateFormat format2 = new SimpleDateFormat(CommonSupraConstants.DATE_yyyy_MM_dd,Locale.ENGLISH);
     	            SimpleDateFormat format1 = new SimpleDateFormat(CommonSupraConstants.DATE_ddMMyyyy,Locale.ENGLISH);
     	            strDate = format1.format(format2.parse(dateToFormat.toString()));
        		}if(pattern.equalsIgnoreCase(CommonSupraConstants.DATE_ddMMyyyy)){
     	            SimpleDateFormat format1 = new SimpleDateFormat(CommonSupraConstants.DATE_ddMMyyyy,Locale.ENGLISH);
        			SimpleDateFormat format2 = new SimpleDateFormat(CommonSupraConstants.DATE_yyyy_MM_dd,Locale.ENGLISH);
     	            strDate = format1.format(format2.parse(dateToFormat.toString()));
        		}if(pattern.equalsIgnoreCase(CommonSupraConstants.DATE_ddMMyyyy_HH_MM_SS)){
        			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY hh:mm:ss");
				    strDate = sdf.format(dateToFormat);	
				   
        		}if(pattern.equalsIgnoreCase(CommonSupraConstants.DATEyyyyMMdd)){
        			SimpleDateFormat sdf = new SimpleDateFormat(CommonSupraConstants.DATEyyyyMMdd);
				    strDate = sdf.format(dateToFormat);	
				   
        		}if(pattern.equalsIgnoreCase(CommonSupraConstants.DATE_yyyyMMddHHmmss)){
        			SimpleDateFormat sdf = new SimpleDateFormat(CommonSupraConstants.DATE_yyyyMMddHHmmss);
				    strDate = sdf.format(dateToFormat);	
				   
        		}if(pattern.equalsIgnoreCase(CommonSupraConstants.DATE_ddMMyyyy+" "+CommonSupraConstants.DATE_HHMMSS)){
        			SimpleDateFormat sdf = new SimpleDateFormat(CommonSupraConstants.DATE_ddMMyyyy+" "+CommonSupraConstants.DATE_HHMMSS);
				    strDate = sdf.format(dateToFormat);	
				   
        		}if(pattern.equalsIgnoreCase(CommonSupraConstants.DATE_MMddyyyy_HH_MM)){
        			SimpleDateFormat sdf = new SimpleDateFormat(CommonSupraConstants.DATE_MMddyyyy_HH_MM);
				    strDate = sdf.format(dateToFormat);	
        		}
        		if(pattern.equalsIgnoreCase(CommonSupraConstants.DATE_ddMMyyyy_HH_MM_SS_SSS)){
        			SimpleDateFormat sdf = new SimpleDateFormat(CommonSupraConstants.DATE_ddMMyyyy_HH_MM_SS_SSS);
				    strDate = sdf.format(dateToFormat);	
        		}
        		
	           
        	}
            
        } catch (Exception e) {
        	
            
            
        }
        
        return strDate;
    }
    
    
  //to get the system date in specified format 
  	public static String getSystemDateFromFormat(String format){
  		
		SimpleDateFormat formatter = null;
		long sysdate = System.currentTimeMillis();
		Date sys_date = new Date(sysdate);
		String system_date ="";
		if(format != null){
			
			formatter = new SimpleDateFormat(format);
			system_date = formatter.format(sys_date);
			
		}else{
			
			return "";
		}
   
		return system_date;
  	}
  	
  	public static Date getCurrentDate(int daysToAdd){
  		
  		Calendar calNow = Calendar.getInstance();
  		
  		calNow.add(Calendar.DAY_OF_MONTH, daysToAdd);
  		
  		return calNow.getTime();
  	}
  	
  	  public static Integer getIntegerFromString(String text){
      	Integer intText = null;
      	if(text != null){
      		intText=Integer.parseInt(text);
      	}
      	return intText;
      	
      }
  	  
    
    /**
     * to get Date Object of given String date with given pattern
     * */
    public static Date getDatefromString(String paramVal,String datePattern){
    	
    	if(paramVal != null && !paramVal.isEmpty()){
    	
	        SimpleDateFormat sdf = new SimpleDateFormat(datePattern,Locale.ENGLISH);
	        
	        try{
	        	
	            if(paramVal != null && paramVal.trim().length() > 0 && !(paramVal.equalsIgnoreCase("null"))){
	            	
	                return sdf.parse(paramVal);
	                
	            }else{
	            	
	                return null;
	                
	            }
	        }
	        catch(Exception e){
	            return null;
	        }
    	}
    	
    	return null;
    }
    
    public static String formatSysDate(String dateStr) throws ParseException{
    	
    	if(dateStr != null && !dateStr.isEmpty()){
    	
	    	SimpleDateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy",Locale.ENGLISH);
	    	
	    	Date date = (Date)formatter.parse(dateStr);
	    	
	    	return getStringDatefromDate(date);
	    	
    	}else{
    		
    		return dateStr;
    	}
    }
    
    public static String getStringDatefromDate(Date date){
    	String datePattern = "dd/MM/yyyy";
    	
        SimpleDateFormat sdf = new SimpleDateFormat(datePattern,Locale.ENGLISH);
        
        try{
        	
            if(date != null && !(date.toString().equalsIgnoreCase("null"))){
            	
                return sdf.format(date);
                
            }else{
            	
                return null;
                
            }
        }
        catch(Exception e){
            return null;
        }
    }
    
    public static String getStringTimestampfromDate(Date date){
    	String datePattern = "dd/MM/yyyy HH:mm:ss";
    	
        SimpleDateFormat sdf = new SimpleDateFormat(datePattern,Locale.ENGLISH);
        
        try{
        	
            if(date != null && !(date.toString().equalsIgnoreCase("null"))){
            	
                return sdf.format(date);
                
            }else{
            	
                return null;
                
            }
        }
        catch(Exception e){
            return null;
        }
    }
    
    public static String getApplePayTimestampfromDate(Date date){
    	String datePattern = "dd-MM-yyyy HH:mm:ss";
    	
        SimpleDateFormat sdf = new SimpleDateFormat(datePattern,Locale.ENGLISH);
        
        try{
        	
            if(date != null && !(date.toString().equalsIgnoreCase("null"))){
            	
                return sdf.format(date);
                
            }else{
            	
                return null;
                
            }
        }
        catch(Exception e){
            return null;
        }
    }
    
    /**
     * to get value as given default value if value is null
     * */
    public static String getDefaultIfNull(String paramVal, String defaultVal){
    	
        if((paramVal != null) && (paramVal.trim().length() > 0) && !(paramVal.equalsIgnoreCase("null"))){
        	
            return paramVal;
            
        }else{
        	
            return defaultVal;
        }
    }
    
    /**
     * to get string value 0 and 1 for boolean flags
     * */
    public static String getIntValuesFromBoolean(String booleanValue){
    	
        if((booleanValue != null) && (booleanValue.trim().length() > 0) && (booleanValue.equalsIgnoreCase("false"))){
        	
            return "0";
            
        }else{
        	
            return "1";
        }
    }
    
    /**
     * to compare dates and get result in Integer as 0(Date1 > = Date2) or 1 (Date1 < Date2)
     * @throws ParseException 
     * */
    public static int compareDates(String endDate, String startDate) throws ParseException
    {

    try{		
        int result = 0;
        
        SimpleDateFormat formatter = new SimpleDateFormat(CommonSupraConstants.DATE_ddMMyyyy);
        
        Date date1 = formatter.parse(endDate);
        Date date2 = formatter.parse(startDate);
        result = date1.compareTo(date2);
        
        if(result < 0)
            return 1;
        
    }catch(Exception e){
    }
        
        return 0;
    }
    
    private static Calendar getCalendarInstanceWithoutTime(){
    	
    	Calendar calNow = Calendar.getInstance();
    	
    	calNow.set(Calendar.HOUR, 0);
    	calNow.set(Calendar.HOUR_OF_DAY, 0);
    	calNow.set(Calendar.MINUTE, 0);
    	calNow.set(Calendar.SECOND, 0);
    	calNow.set(Calendar.MILLISECOND, 0);
    	
    	return calNow;
    	
    }
    
    public static boolean isExpiredWithTime(Date dateTime){
    	
    	boolean isExpired = false;
    	
    	if(dateTime != null ){
    	
	    	Calendar calGiven = Calendar.getInstance();
	    	Calendar calNow = Calendar.getInstance();
	    	
	    	calGiven.setTime(dateTime);
	    	calGiven.add(Calendar.MINUTE, 15);
	    	
	    	if(calGiven.before(calNow)){
	    		
	    		isExpired = true;
	    	}
	    	
    	}else{
    		
    		isExpired = true;
    	}
    	
    	return isExpired;
    }
    
    public static boolean isExpired(String date, String datePattern) throws ParseException{
    	boolean isExpired = false;
    	
    	if(date != null && !date.isEmpty() && !date.equalsIgnoreCase("null")){
    	
	    	Calendar calNow = getCalendarInstanceWithoutTime();
	    	Calendar calGiven = getCalendarInstanceWithoutTime();
	    	
	    	SimpleDateFormat formatter = new SimpleDateFormat(datePattern);
	    	
	    	calGiven.setTime(formatter.parse(date));
	    	
	    	if(calGiven.before(calNow)){
	    		
	    		isExpired = true;
	    	}
    	}else{
    		
    		isExpired = true;
    	}
    	
    	return isExpired;
    }
    
    /**
     * to set response code and exception into reference object
     * */
    
    public static JSONObject setExceptionAndStatus(JSONObject referenceObject, Exception e){
    	
    	referenceObject.put(CommonSupraConstants.RESPONSE_CODE,CommonSupraConstants.STR_ZERO);
        referenceObject.put(CommonSupraConstants.RESPONSE_DESC,e.getMessage());
        
        return referenceObject;
    }
    
    /**
     * to close Connection, Statement, ResultSet
     * */
    public static void closeConn(ResultSet rs, Statement cst, Connection conn){
		try {
			if(rs != null){
					rs.close();
			}
			
			if(cst != null){
				cst.close();
			}
			
			if(conn != null){
				conn.close();
			}
		
		} catch (SQLException e) {
			
		}
	}
    
    /**
     * to get Node Value from SOAPEnvelope with the given node name
     * */
    public static String getNodeValue(SOAPEnvelope response,String tagName){
    	
        try{
        	
            NodeList tagValueL = response.getElementsByTagName(tagName);
            
            return (tagValueL != null && tagValueL.getLength() > 0) ? tagValueL.item(0).getFirstChild().getNodeValue() : null;
            
        }catch(Exception e){
        	return null;
        }
    }
    
    public static String getSuccessOrFailureMessageWithId(int result){
    	
    	if(result == CommonSupraConstants.SUCCESS_CODE){
    		
    		return CommonSupraConstants.SUCCESS_DESC;
    		
    	}else{
    		
    		return CommonSupraConstants.FAILURE_DESC;
    	}
    }
    
    public static Long getLongValofObject(Object objVal){
    	
    	if(objVal != null){
    		
    		try{
    		
    			return Long.parseLong(objVal.toString());
    			
    		}catch(NumberFormatException e){
    			
    			return 0L;
    		}
    		
    	}else{
    		
    		return 0L;
    	}
    	
    }
    
    public static Double getDoubleValofObject(Object objVal){
    	
    	if(objVal != null){
    		
    		try{
    		
    			return Double.parseDouble(objVal.toString());
    		
    		}catch(NumberFormatException e){
    			
    			return 0.0;
    		}
    		
    	}else{
    		
    		return 0.0;
    	}
    	
    }
    
    public static String getStringValofObject(Object objVal){
    	
    	if(objVal != null){
    		
    		return objVal.toString();
    		
    	}else{
    		
    		return "";
    	}
    	
    }
    
    public static BigInteger getBigIntValofObject(Object objVal){
    	
    	if(objVal != null){
    		
    		try{
    		
    			return BigInteger.valueOf(CommonSupraUtil.getLongValofObject(objVal));
    			
    		}catch(NumberFormatException e){
    			
    			return BigInteger.valueOf(0);
    		}
    		
    	}else{
    		
    		return BigInteger.valueOf(0);
    	}
    	
    }
    
    public static Integer getIntValofObject(Object objVal){
    	
    	if(objVal != null){
    		
    		try{
    		
    			return Integer.parseInt(objVal.toString());
    			
    		}catch(NumberFormatException e){
    			
    			return 0;
    		}
    		
    	}else{
    		
    		return 0;
    	}
    	
    }
    
    public static boolean getBooleanValofObject(Object objVal){
    	
    	if(objVal != null){
    		
    		try{
    		
    			return (Integer.parseInt(objVal.toString()) == 1 ?true:false);
    			
    		}catch(NumberFormatException e){
    			
    			return false;
    		}	
    		
    	}else{
    		
    		return false;
    	}
    	
    }
    
public static Float getFloatValofObject(Object objVal){
    	
    	if(objVal != null){
    		
    		try{
    		
    			return Float.parseFloat(objVal.toString());
    		
    		}catch(NumberFormatException e){
    			
    			return 0.0F;
    		}
    		
    	}else{
    		
    		return 0.0F;
    	}
    	
    }
    
    public static boolean checkDateWithGivenDays(String startDateStr, String endDateStr, int days) throws ParseException{
    	
    	 SimpleDateFormat formatter = new SimpleDateFormat(CommonSupraConstants.DATE_ddMMyyyy);
         Date endDate = formatter.parse(endDateStr);
         
         Calendar currentDate = getCalendarInstanceWithoutTime();
         
         if(startDateStr != null && !startDateStr.isEmpty()){
        	 
             Date startDate = formatter.parse(startDateStr);
        	 
        	 currentDate.setTimeInMillis(startDate.getTime());
         }
         
         currentDate.add(Calendar.DATE, days);
         
         return currentDate.getTime().before(endDate) || currentDate.getTime().equals(endDate);
    }
    
    public static Long getProperMobileNo(String mobileNo){
    	
    	Long mobile = 0L;
    	
    	if(mobileNo != null && !mobileNo.equals("null") && CommonSupraUtil.getLongValofObject(mobileNo) > 0){
    		
    		if(mobileNo.startsWith("05") && (mobileNo.length() >= 9 && mobileNo.length() < 12)) {
    			
        		if(mobileNo.startsWith("05")){
    				
    				mobileNo = mobileNo.replaceFirst("05", "5");
    				
    			}
        		
	    		if(!mobileNo.startsWith("971")) {
	    			
	    			mobileNo = "971"+mobileNo;
	    		}
	    		
	    		mobile = CommonSupraUtil.getLongValofObject(mobileNo);
	    		
    		}else {
    			
    			if(mobileNo.startsWith("9715") && (mobileNo.length() == 12)) {
    				
    				mobile = CommonSupraUtil.getLongValofObject(mobileNo);
    			}
    		}
		}
    	
    	return mobile;
    }
    
	public static int isGCCPlate(String plateSrcCode) {
		int gccPlate = 0;
		
		try {
			
			if (Integer.parseInt(plateSrcCode) > 0) {
				
				gccPlate = 1;
			}
			
		} catch (Exception e) {
			
			gccPlate = 0;
		}
		
		return gccPlate;
	}
	
	public static String getCommaSepFromArray(Object[] objArr){
		
		String value = "";
		
		if(objArr != null && objArr.length > 0){
			
			for (int i = 0; i < objArr.length; i++) {
				Object object = objArr[i];
				
				value += object+",";
			}
			
			value = value.substring(0, value.length()-1);
			
		}
		
		return value;
	}
	
	public static String getExcStackFromException(Exception e){
        StringWriter sw = new StringWriter();
        PrintWriter pW = new PrintWriter(sw);
       
        String excMsg = sw.toString();
        return (excMsg != null && excMsg.length() > 3950) ? excMsg.substring(0,3950) : excMsg;
    }
	
	public static AttachmentSoapRequestDTO[] getAttachmentsArrFromMultipartArr(MultipartFile[] files) throws IOException{
		
		
		if(files != null && files.length > 0){
			
			AttachmentSoapRequestDTO[] attachments = new AttachmentSoapRequestDTO[files.length];
			AttachmentSoapRequestDTO attachment = null;
			
			for (int i = 0; i < files.length; i++) {
				MultipartFile file = files[i];
				
				attachment = new AttachmentSoapRequestDTO();
				
				attachment.setAttachName(file.getOriginalFilename());
				attachment.setAttachment(file.getBytes());
				
				attachments[i] = attachment;
			}
			
			return attachments;
			
		}else{
			
			return null;
		}
		
	}
	
	public static AttachmentSoapRequestDTO[] getAttachmentsArrFromMultipartList(List<MultipartFile> files) throws IOException{
		
		
		if(files != null && !files.isEmpty()){
			
			AttachmentSoapRequestDTO[] attachments = new AttachmentSoapRequestDTO[files.size()];
			AttachmentSoapRequestDTO attachment = null;
			
			int i = 0;
			
			for (Iterator iterator = files.iterator(); iterator.hasNext();) {
				MultipartFile file = (MultipartFile) iterator.next();
				
				attachment = new AttachmentSoapRequestDTO();
				
				attachment.setAttachName(file.getOriginalFilename());
				attachment.setAttachment(file.getBytes());
				
				attachments[i++] = attachment;
			}
			
			return attachments;
			
		}else{
			
			return null;
		}
		
	}
	
	public static String getStringValFromJSONString(String jsonStr, String key){
		String strVal = "";
		JSONObject obj = null;
		
		try {
			
			obj = (JSONObject)new JSONParser().parse(jsonStr);
			
		} catch (org.json.simple.parser.ParseException e) {
			
			obj = null;
		}
		
		if(obj != null) {
			
			Object objVal = obj.get(key);
			
			strVal = objVal == null?"":objVal.toString();
		}
		
		return strVal;
	}
	
	public static Long getLongValFromJSONString(String jsonStr, String key) throws org.json.simple.parser.ParseException{
		Long longVal = 0L;
		
		String strVal =  getStringValFromJSONString(jsonStr, key);
		
		if(ValidationSupraUtil.isStringValueNotNullAndNotEmpty(strVal)){
			
			longVal = Long.parseLong(strVal);
		}
		
		return longVal;
	}
	
	public static int getIntValFromJSONString(String jsonStr, String key) throws org.json.simple.parser.ParseException{
		int longVal = 0;
		
		String strVal =  getStringValFromJSONString(jsonStr, key);
		
		if(ValidationSupraUtil.isStringValueNotNullAndNotEmpty(strVal)){
			
			longVal = Integer.parseInt((strVal));
		}
		
		return longVal;
	}
	
	
	
	public static void printDuration(Date start, Date end, String serviceName, Logger logger){
		
		//in milliseconds
		long diff = end.getTime() - start.getTime();
		
		long diffSeconds = diff / 1000 % 60;
		long diffMinutes = diff / (60 * 1000) % 60;
		long diffHours = diff / (60 * 60 * 1000) % 24;
		
		logger.info(serviceName+" - DURATION - "+diffHours+" : "+diffMinutes+" : "+diffSeconds);
	}
	
	public static void logServiceInvocation(String serviceName,String operationName,String httpStatus,Date msgInTime,Date msgOutTime,Long millSecs,String stacktrace, Logger serviceInvokeLog){
		
		serviceInvokeLog.info("ServiceName ="+serviceName+",OperationName="+operationName+",HTTPStatus="+httpStatus+","
				+ "MessageInTime="+msgInTime+",MessageOutTime="+msgOutTime+",ResponseTime="+millSecs+",Exception="+stacktrace+"");
		
	}
	
	

	
	public static boolean validIpAddress(String ipaddress){
		
		return InetAddresses.isInetAddress(ipaddress);
	}
	
	
	

	 public static String convertInValofString(int val){
	    	
	    		if(val ==1){
	    			return String.valueOf("true");
	    		}else{
	    			return String.valueOf("false");
	    		}
	  
	 }
	 
	 /**
	  * getting resource location path
	  * @param clsPath
	  * @return
	  */
	 public static String getValidPath(String clsPath){
	        
	        if(clsPath != null){
	            clsPath = clsPath.substring(0, clsPath.indexOf("WEB-INF")+7);
	            //testing server path
//	            clsPath=clsPath+"//CertificateAssets/";
//	         
	            // local host path
	            clsPath=clsPath+File.separator+"CertificateAssets"+File.separator;
	        }
	    
	        return clsPath;
	}
    
	public static void printJavaHomeAndTrustStore(){
		
		String javaHome = System.getProperty("JAVA_HOME");
    	
    	
    	String trustStore = System.getProperty("javax.net.ssl.trustStore");
    	
		
		
	}
	
	public static void deleteSignatureFile(String path) {

		try {
			if(path !=null){
			File file = new File(path);
			if (file.delete()) {
				
			} else {
				
			}
		}
		} catch (Exception e) {
			
		}
		
	}
	
	private static String getCurrPath(String fileName){
		
		String clsPath = null;
        
        URL url = CommonSupraUtil.class.getProtectionDomain().getCodeSource().getLocation();
        File propPath = null;
		try {
			
			
			if(!url.equals("rsrc:./")){
			
				propPath = new File(url.toURI()).getParentFile();
			}
		} catch (URISyntaxException e) {
			
		}
		
		if(propPath != null){
		
			clsPath = propPath.toString();
			
		}else{
			
			clsPath = "./";
		}
		
		return clsPath+"/"+fileName;
	}
	
	public static void writeObjectToFile(Object obj, String fileName){
		
		fileName = getCurrPath(fileName);
		

		ObjectOutputStream oos = null;
		
		try {
			FileOutputStream fout = new FileOutputStream(fileName);
			
			oos = new ObjectOutputStream(fout);
			oos.writeObject(obj);
			
		} catch (IOException e) {
			
		}finally{
			
			if(oos != null){
			
				try {
					oos.close();
				} catch (IOException e) {
					
				}
			}
		}
	}
	
	public static Object readObjectFromFile(String fileName){
		
		fileName = getCurrPath(fileName);
		
		
		FileInputStream fin = null;
		ObjectInputStream ois = null;
		Object obj = null;
		try {
			fin = new FileInputStream(fileName);

			ois = new ObjectInputStream(fin);
			obj = ois.readObject();
			
		} catch (FileNotFoundException e) {
			return null;
		} catch (IOException e) {
			
		} catch (ClassNotFoundException e) {
			
		}finally{
			
			try{
			
				if(ois != null){
					
					ois.close();
				}
				
				if(fin != null){
					
					fin.close();
				}
			}catch(Exception e){
				
			}
		}
		
		return obj;
	}
	
	
	
	public static String getFormattedDateTimeArToEn(String dateTimeInStr,String parseFormat){
        
        try{
        	
        	if(dateTimeInStr != null){
        	
	            SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a",Locale.ENGLISH);
	            SimpleDateFormat sdfP = new SimpleDateFormat(parseFormat,new Locale("ar"));
	            Calendar cal = Calendar.getInstance();
	            cal.setTime(sdfP.parse(dateTimeInStr));
	            
	            return sdf1.format(cal.getTime());
	            
        	}else{
        		
        		return dateTimeInStr;
        	}
            
        }catch(Exception e){
        	
        	return dateTimeInStr;
        }
    }
	
	
	public static String generateStackTraceFromDTO(Object dtoObj,String serviceMethodName)  {
     
		try
		{
			Map<String,String> map = org.apache.commons.beanutils.BeanUtils.describe(dtoObj);
			return map.toString();
		}
		catch(Exception ex)
		{
			
			return null;
		}

	}
	
	 public static  Map<String, String> getGCEmployeeSignature(String policeStationId,String realPath, int langId)
	    {
	    	Map<String, String> empDtls= new HashMap<String,String>();
	     /*  	EMSEmpServiceImpl impl= new EMSEmpServiceImpl();
	    	byte[] img=null;
	        InputStream is =null;
	        ObjectInputStream oip = null;
	        InputStream isr =null;
	        FileOutputStream f =null;
	        String manager_emp_no= "";
	        String manager_occ = "";
	        String manager_name = "";
	        String ems_psId=policeStationId;
	        try{
	           if(ems_psId!=null){
	    	List<Emp> list =impl.getActivePosByPS(Integer.valueOf(policeStationId));
					for (Emp emp : list) {
						if (emp.getOccupation().getValue() != null) {
							manager_occ = emp.getOccupation().getValue();
						}
						
						
						if (langId ==1 && emp.getEmpNameAr().getValue() != null) {
							manager_name = emp.getEmpNameAr().getValue();
						}else if(emp.getEmpName().getValue() !=null) {
							manager_name = emp.getEmpName().getValue();
						}
						if (emp.getEmployeeID().getValue() != null) {
							manager_emp_no = emp.getEmployeeID().getValue();
							
						}
					}
	        empDtls.put("manager_emp_no", manager_emp_no);
	        empDtls.put("manager_occ", manager_occ);
	        empDtls.put("manager_name", manager_name);

				}
	        } catch (Exception e) {
	        empDtls.put("manager_emp_no", manager_emp_no);
	        empDtls.put("manager_occ", manager_occ);
	        empDtls.put("manager_name", manager_name);
			
		}finally{
			try{
	       if(oip!=null){ oip.close();}
	       if(is!=null){  is.close();}
	       if(f!=null){  f.close();}
	       if(isr!=null){ isr.close();}
			}catch(IOException e){
				
			}
		}*/
	        return empDtls;
	           }
	 
	 
	 public static String getExpireDate(int expirePeriod)throws Exception{
		 
		 
		 DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Calendar cal = Calendar.getInstance();
	        cal.setTime(df.parse(getCurrentDate()));
	        
	        cal.add(Calendar.MONTH, expirePeriod);  // number of months to add\
	        
	        String expiry_date = df.format(cal.getTime()).toString();
	        
	        return expiry_date;
	 }
	 
	 
	 public static String getCurrentDate(){
			
			String s="";
			
			try{
			
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				Date date = new Date();
				Calendar cal = Calendar.getInstance();
			    s =dateFormat.format(cal.getTime());
			  
			}catch(Exception e){
				logger.info("error " + e.getStackTrace());
			}
		  return s;
		}
		
	 
	 public static String getFormattedAppDate(String date){
			
			String formattedDate="";
			
			try{
				if(date!=null && !date.isEmpty()){
					String[] splitedDate = date.split("\\s+");
					if(splitedDate!=null){
						formattedDate =  splitedDate[0];
					}
				}
			  
			}catch(Exception e){
				logger.info("error " + e.getStackTrace());
			}
		  return formattedDate;
		}
	 
	 public static String readClob(Clob clob) throws SQLException, IOException { 
		 
		 StringBuilder sb = new StringBuilder((int) clob.length());
		 Reader r = clob.getCharacterStream();
		 char[] cbuf = new char[2048];
		 int n; 
		 while ((n = r.read(cbuf, 0, cbuf.length)) != -1) {
			 sb.append(cbuf, 0, n); } 
		 return sb.toString();
		}

	 public static String getTrimQuestionAnswers(String questionCopy){
			int even=0;
			int odd=1;
			String textBreif="";
			try{
			if(questionCopy !=null){
			String splitQuestionAnswer[]=questionCopy.trim().split("\\r?\\n");
			
			for (int i = 0; i < splitQuestionAnswer.length; i++) {
				if(splitQuestionAnswer[i].trim().length()>0){
					textBreif +=splitQuestionAnswer[i].trim()+"\n";
				}
			}
				return textBreif;
			}
			
			}catch (Exception e) {
				// TODO: handle exception
			}
				return "";
		}
	
	private static void readFileAndSearch(String filePath,	String transactionId) {
		
		File f = new File(filePath);
		
		if(f != null){
			
			File[] fileNames = f.listFiles();
			
			for (int i = 0; i < fileNames.length; i++) {
				File file = fileNames[i];	
				System.out.println(file.getName());
					Scanner scan = null;
					
					try {
						
						scan = new Scanner(file);
						
					} catch (FileNotFoundException e) {
						
					}
					
					if (scan != null) {
						
						int count = 0;
						
						while (scan.hasNextLine()) {
							
							String line = scan.nextLine();
							
							if (line.contains(transactionId)) {
								
								count++;
							}
							
							if (count > 0 && count < 10) {
								count++;
								System.out.println(line);
								
							}else{
								
								count = 0;
							}
						}
						
						count = 0;
					}
				}
		}
		
		/**/
	}
	 public static String newEncrypt(String plainText) throws Exception {
		 byte[] keyBytes = keyText.getBytes("UTF-8");
	     byte[] plainTextBytes = plainText.getBytes("UTF-8");
	     SecretKeySpec newKey = new SecretKeySpec(keyBytes, "AES");
	     Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	     cipher.init(Cipher.ENCRYPT_MODE, newKey,new IvParameterSpec(new byte[16]));
	     byte[] cipherBytes = cipher.doFinal(plainTextBytes);
	     //return new sun.misc.BASE64Encoder().encode(cipherBytes);
	     return Base64.encodeBase64String(cipherBytes);
	 }
	 
	 public static String newDecrypt(String value) throws Exception {
		 SecretKey key = new SecretKeySpec(keyText.getBytes("UTF-8"), "AES");
	     Cipher dcipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	     dcipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(new byte[16]));
	    // byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(value);
	     byte[] dec = Base64.decodeBase64(value);
	     byte[] utf8 = dcipher.doFinal(dec);
	     return new String(utf8, "UTF8");
	 }  
	
	
	
	public static void validateAttachment(byte[] attachment) throws Exception
	{
			
		String contentType = new Tika().detect(attachment);
		if(!Arrays.asList(CommonSupraConstants.ATTACHMENT_IMAGE_TYPES).contains(contentType.toLowerCase()))
			ExceptionSupraUtil.throwException(ExceptionSupraValidationsConstants.INVALID_ATTACHMENT_TYPE, ExceptionSupraUtil.EXCEPTION_VALIDATION);
		
		long size = attachment.length;
		size = size/1024;
		if(size> CommonSupraConstants.MAX_IMAGE_SIZE*1024)
			ExceptionSupraUtil.throwException(ExceptionSupraValidationsConstants.SIZE_CHECK, ExceptionSupraUtil.EXCEPTION_VALIDATION);
	}
	
	public static String getContentTypeOfAttachment(byte[] byteVal){
		String contType=null;
		
		contType=new Tika().detect(byteVal);
		
		return contType;
	}    

	public static boolean validateField(String fieldValue) {
	    return (CommonSupraConstants.EMPTY_STR.equals(fieldValue) || fieldValue == null)? 
	           false : true;
	}   

	public static boolean compareContentType(String fileContentType,String[] allowedContents){
		boolean content=false;
		if(fileContentType!=null && allowedContents!=null && allowedContents.length>0){
			for(String str:allowedContents){
				
				if(fileContentType.contains(str)){
					content= true;
				}
			}
			
		}
		return content;
	}

	 
public static String formatDateForTraffic(String dateStr)throws Exception{
		
		DateFormat readFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		DateFormat writeFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S'Z'");
		Date d = readFormat.parse(dateStr);
		String output = writeFormat.format(d);
		
		return output;
	}


public static boolean isContainArabic(String s) {
    for (int i = 0; i < s.length();) {
        int c = s.codePointAt(i);
        if (c >= 0x0600 && c <= 0x06E0)
            return true;
        i += Character.charCount(c);            
    }
    return false;
  }

public static boolean compareDateTime(String date) throws ParseException{
	
	
	 SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss") ;
	 long yourmilliseconds = System.currentTimeMillis();
	 Date currentDate = new Date(yourmilliseconds);
    Date endDate = dateFormat.parse(date);
	     if(endDate.before(currentDate)){
	    	 return true;
		}
	   
	     return false;
	  		
}
	public static Date addHoursToJavaUtilDate(Date date, int hours) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    calendar.add(Calendar.HOUR_OF_DAY, hours);
	    return calendar.getTime();
	}
	
    public static String maskMobNumber(Long mobileNum){
        
        String val = "";
        
        String mobileNumber = String.valueOf(mobileNum);
        
        if(mobileNumber != null && mobileNumber.length() >= 9){
                        
                        if (mobileNumber.length() == 12) {
                                                        val = "971" + mobileNumber.substring(3, 5) + "XXXX" + mobileNumber.substring(9, 12);
                                        } else if (mobileNumber.length() == 10) {
                                                        val = "971" + mobileNumber.substring(1, 3) + "XXXX" + mobileNumber.substring(7, 10);
                                        } else {
                                                        val = "971" + mobileNumber.substring(0, 2) + "XXXX" + mobileNumber.substring(6, 9);
                                        }
        }
        return val;
    }
    
    public static int getAgeInYears(String dob){
    	
    	final int MILLIS_IN_SECOND = 1000;
    	final int SECONDS_IN_MINUTE = 60;
    	final int MINUTES_IN_HOUR = 60;
    	final int HOURS_IN_DAY = 24;
    	final int DAYS_IN_YEAR = 365;
    	
    	if(dob != null){
    		
    		Date birthday = CommonSupraUtil.getDatefromString(dob, CommonSupraConstants.DATE_ddMMyyyy);
    		Calendar todayCal = CommonSupraUtil.getCalendarInstanceWithoutTime();
    		
    		Long age = (todayCal.getTimeInMillis() - birthday.getTime())/MILLIS_IN_SECOND/SECONDS_IN_MINUTE/MINUTES_IN_HOUR/HOURS_IN_DAY/DAYS_IN_YEAR;
    		
    		return age.intValue();
    	}
    	
    	return 0;
    }

   
    
    
    public static String getStringValue(String fieldName,String defaultValue) {
		String result = defaultValue;
    	if(fieldName != null && !fieldName.equalsIgnoreCase("-1")){
    		return fieldName;
    	}
    	return result;
    }
    
    public static boolean isCurrentDate(String strDate) throws ParseException {
    	
    	SimpleDateFormat formatter = new SimpleDateFormat(CommonSupraConstants.DATE_ddMMyyyy);
        Date endDate = formatter.parse(strDate);
        
        Calendar currentDate = getCalendarInstanceWithoutTime();
       	Date startDate = formatter.parse(CommonSupraUtil.getCurrentDate());
       	currentDate.setTimeInMillis(startDate.getTime());
        
        return currentDate.getTime().equals(endDate);
    }
    
    public static BigDecimal getBigDecimalFromObject(Object objVal){
    	
    	if(objVal != null){
    		
    		try{
    		
    			return new BigDecimal(objVal.toString());
    			
    		}catch(NumberFormatException e){
    			
    			return new BigDecimal(0);
    		}
    		
    	}else{
    		
    		return new BigDecimal(0);
    	}
    	
    }
 
  public static String getStringValofObjecIsZeroReturnEmpty(Object objVal){
    	
    	if(objVal != null){
    		if(!objVal.toString().equals("0")) {
    			return objVal.toString();
    		}else{
        		
        		return "";
        	}
    	}
    	return "";
    	
    }
  public static long generateRandomLong() {
	  return ThreadLocalRandom.current().nextLong(1,Long.MAX_VALUE);  
  }
  
  public static String getMd5(String input){ 
      try { 
          MessageDigest md = MessageDigest.getInstance("MD5"); 
          byte[] messageDigest = md.digest(input.getBytes()); 
          BigInteger no = new BigInteger(1, messageDigest); 
          String hashtext = no.toString(16); 
          while (hashtext.length() < 32) { 
              hashtext = "0" + hashtext; 
          } 
          return hashtext; 
      }  
      catch (NoSuchAlgorithmException e) { 
          throw new RuntimeException(e); 
      } 
  } 
  
  public static Clob createClob(String string,Connection connection) {
	  try {
	    final Clob clob = connection.createClob();
	    clob.setString( 1, string );
	    return clob;
	  }
	  catch ( SQLException e ) {
	    throw new JDBCException( "Unable to set CLOB string after creation", e );
	  }
	
}
  
  public static String getTrimQuestionAnswersForJasper(String questionCopy){
		int even=0;
		int odd=1;
		String textBreif="";
		try{
		if(questionCopy !=null){
		String splitQuestionAnswer[]=questionCopy.trim().split("\\r?\\n");
		
		for (int i = 0; i < splitQuestionAnswer.length; i++) {
			if(splitQuestionAnswer[i].trim().length()>0){
				splitQuestionAnswer[i]=splitQuestionAnswer[i].trim().replace("\\."+(i+1), "٣");
				textBreif +=splitQuestionAnswer[i].trim()+"\n";
			}
		}
			return textBreif;
		}
		
		}catch (Exception e) {
			// TODO: handle exception
		}
			return "";
	}

  public static String getTrueAndFalseVal(String trueFalse){
	  String result="";
	  if(trueFalse.equalsIgnoreCase("true")) {
		  result="1";
	  }else {
		  result="0";
	  }
	  return result;
  }
	public static void main(String[] args) throws Exception {
//		CommonUtil.getAgeInYears("24/09/2014");
		 /*for (int i = 1; i < 4; i++) {

			CommonUtil.readFileAndSearch("C:/Users/DES.YNASIR/Desktop/logs/scheduler","80078163");
		}*/
		
		//isContainArabic("test"));
		
		String transIds[] = {"218010328419","218010328397"};
		for (int i = 1; i < 2; i++) {
			for (int j = 0; j < transIds.length; j++) {
				String string = transIds[j];

				CommonSupraUtil.readFileAndSearch("C:/Users/DES.YNASIR/Desktop/logs/1/",string);
			}
		}
		
//		CommonUtil.getEmployeeSignature("1000", "/u01/app/dpservices_resources/jasper/images");
		
//		String transDate = CommonUtil.getFormattedDateByPattern("14/01/2018", CommonConstants.DATE_ddMMyyyy, CommonConstants.DATE_yyyy_MM_dd);
		
//		transDate);
		
	}
	
}

