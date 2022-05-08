package com.supra.common.util;

import java.util.ResourceBundle;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;

import com.supra.common.CommonSupraConstants;
import com.supra.common.ExceptionSupraBusinessConstants;
import com.supra.common.ExceptionSupraValidationsConstants;
import com.supra.common.exception.BusinessSupraException;
import com.supra.common.exception.ValidationSupraException;



public class ExceptionSupraUtil {
	public static final int EXCEPTION_VALIDATION = 1;
	public static final int EXCEPTION_BUSINESS = 2;
	public static final int EXCEPTION_CUSTOM = 3;
	public static final int EXCEPTION_CUSTOM_CODE = 1000;
	public static final String PROPS_VALIDATIONS="exception_supra_validations";
	public static final String PROPS_BUSINESS="exception_supra_business";
	public static final String SUPRASERVICES_EXCEPTION = "SUPRASE";
	
	private static final Logger logger = Logger.getLogger(CommonSupraConstants.LOGGER_SUPRASERVICES_ERROR);
	private static final Logger loggerInfo = Logger.getLogger(CommonSupraConstants.LOGGER_SUPRASERVICES_INFO);
	private static final Logger loggerBre = Logger.getLogger(CommonSupraConstants.LOGGER_SUPRASERVICES_BRE);
	

	static ResourceBundle resource_validation = null;
	static ResourceBundle resource_business = null;
	
	public static void throwException(String message, int excType) throws BusinessSupraException{
		
		try{
			
			throwExceptionFinal("", message, excType);
			
		}catch(BusinessSupraException ex){
			loggerBre.info("ExceptionUtil-->throwException", ex);
			throw ex;
		}catch(ValidationSupraException ex){
			loggerInfo.info("ExceptionUtil-->throwException", ex);
			throw ex;
		}catch(RuntimeException ex){
			logger.error("ExceptionUtil-->throwException", ex);
			throw ex;
		}
	}
	
	public static void throwExceptionFinal(String fieldName, String message, int excType){
		
		if(excType == EXCEPTION_CUSTOM){
			
			message = EXCEPTION_CUSTOM_CODE+" - "+message;
			
		}else{
			
			String errorMessage = getErrorMessageFromProps(message, excType);
			
			if(fieldName != null && !fieldName.isEmpty()){
				errorMessage = errorMessage.replace("{fieldName}", fieldName);
			}
			
			fieldName = null;
				
			message += " - "+errorMessage;
		}
		
		message += " - "+SUPRASERVICES_EXCEPTION;
		
		if(excType == EXCEPTION_VALIDATION){
						
			throw new ValidationSupraException(message);
			
		}else if(excType == EXCEPTION_BUSINESS){
			
			throw new BusinessSupraException(message);
			
		}else if(excType == EXCEPTION_CUSTOM){
			
			throw new RuntimeException(message);
		}
		
	}
	
	public static void throwReferenceNumberZeroException(Long referenceNum, String excCode)throws Exception{
		
//		if(CommonConstants.STR_ZERO.equalsIgnoreCase(referenceNum))
//		{
//			throwExceptionFinal(null, excCode, ExceptionUtil.EXCEPTION_BUSINESS);
//		}
	}
	
	public static void throwStringValueZeroBusException(Object referenceId, String excCode) throws Exception{
		
		if(referenceId == null || CommonSupraConstants.STR_ZERO.equalsIgnoreCase(referenceId.toString())){
			throwExceptionFinal(null, excCode, ExceptionSupraUtil.EXCEPTION_BUSINESS);
		}
	}
	
	
	
	public static void throwInvalidEmailValException(String email, boolean isRequired) throws Exception{
		boolean isValid = true;
		
		if(isRequired){
			
			if(email == null || email.isEmpty()){
			
				ExceptionSupraUtil.throwNullOrEmptyValidationException("Email", email, true);
			}
			
			isValid = ValidationSupraUtil.validateEmail(email);
			
		}else{
			
			if(email != null && !email.isEmpty()){
				
				isValid = ValidationSupraUtil.validateEmail(email);
			}
			
		}
		
		if(!isValid){
			
			throwExceptionFinal(null, ExceptionSupraValidationsConstants.INVALID_EMAIL, ExceptionSupraUtil.EXCEPTION_VALIDATION);
		}
		
	}
	
	public static void throwInvalidPhoneNoException(Long phoneNo, boolean isRequired, String fieldName) throws Exception{
		boolean isValid = true;
		
		String phoneNoStr = String.valueOf(phoneNo);
		
		if(isRequired){
			
			if(phoneNoStr == null || phoneNoStr.isEmpty()){
				
				ExceptionSupraUtil.throwNullOrEmptyValidationException(fieldName, phoneNoStr, true);
			}
			
			isValid = ValidationSupraUtil.validatePhoneNo(phoneNoStr);
			
		}else{
			
			if(phoneNoStr != null && !phoneNoStr.isEmpty() && !phoneNoStr.equals("null") && !phoneNoStr.equals("0")){
				
				isValid = ValidationSupraUtil.validatePhoneNo(phoneNoStr);
			}
			
		}
		
		if(!isValid){
			throwExceptionFinal(fieldName, ExceptionSupraValidationsConstants.INVALID_PHONE, ExceptionSupraUtil.EXCEPTION_VALIDATION);
		}
		
	}
	
	public static void throwInvalidTimeException(String time, boolean isRequired) throws Exception{
		boolean isValid = true;
		
		if(isRequired){
			
			if(time == null || time.isEmpty()){
			
				ExceptionSupraUtil.throwNullOrEmptyValidationException("Time", time, true);
			}
			
			isValid = ValidationSupraUtil.validateTimeFormat_24HRS(time);
			
		}else{
			
			if(time != null && !time.isEmpty()){
				
				isValid = ValidationSupraUtil.validateTimeFormat_24HRS(time);
			}
			
		}
		
		if(!isValid){
			
			throwExceptionFinal(null, ExceptionSupraValidationsConstants.INVALID_TIME_FORMAT, ExceptionSupraUtil.EXCEPTION_VALIDATION);
		}
		
	}
	
	public static void throwInvalidDateException(String date, String fieldName, boolean isRequired) throws Exception{
		
		boolean isValid = true;
		
		if(isRequired){
			
			if(date == null || date.isEmpty()){
			
				ExceptionSupraUtil.throwNullOrEmptyValidationException(fieldName, date, true);
			}
			
			isValid = ValidationSupraUtil.validateDateFormat(date, CommonSupraConstants.DATE_ddMMyyyy);
			
			
		}else{
			
			if(date != null && !date.isEmpty()){
				
				isValid = ValidationSupraUtil.validateDateFormat(date, CommonSupraConstants.DATE_ddMMyyyy);
			}
			
		}
		
		if(!isValid){
			
			throwExceptionFinal(null, ExceptionSupraValidationsConstants.INVALID_DATE_FORMAT, ExceptionSupraUtil.EXCEPTION_VALIDATION);
		}
	}
	
	public static void throwInvalidDateException(String date, String pattern, String fieldName, boolean isRequired) throws Exception{
		
		boolean isValid = true;
		
		if(isRequired){
			
			if(date == null || date.isEmpty()){
			
				ExceptionSupraUtil.throwNullOrEmptyValidationException(fieldName, date, true);
			}
			
			isValid = ValidationSupraUtil.validateDateFormat(date, pattern);
			
			
		}else{
			
			if(date != null && !date.isEmpty()){
				
				isValid = ValidationSupraUtil.validateDateFormat(date, pattern);
			}
			
		}
		
		if(!isValid){
			
			throwExceptionFinal(null, ExceptionSupraValidationsConstants.INVALID_DATE_FORMAT, ExceptionSupraUtil.EXCEPTION_VALIDATION);
		}
	}
	
	public static void throwNullOrEmptyValidationException(String fieldName, Object value, boolean validateZero) throws Exception{
		boolean isValid = false;
		
		if(value != null && value.getClass().isArray()){
			
			isValid = ValidationSupraUtil.isArrayNotNullAndNotEmpty(value);
			
		}else{
			
			isValid = ValidationSupraUtil.isStringValueNotNullAndNotEmpty(value);
		}
		
		if(validateZero && (value != null && !value.toString().isEmpty())){
			
			isValid = !ValidationSupraUtil.isStringValueZero(value.toString());
		}
		
		if(!validateZero && !fieldName.isEmpty()){
			
			if(value != null){
			
				isValid = ValidationSupraUtil.validateNullString(value.toString());
			}
		}
		
		if(!isValid){
			
			throwExceptionFinal(fieldName, ExceptionSupraValidationsConstants.REQUIRED_FIELD_EMPTY, ExceptionSupraUtil.EXCEPTION_VALIDATION);
		}
		
	}
	
	public static void throwSizeExceedsException(String fieldName, String field, int fieldSizeExpected)throws Exception
	{
		if(field.length()>fieldSizeExpected)
		{
			String errorMessage = getErrorMessageFromProps(ExceptionSupraValidationsConstants.MAX_FIELD_LENGTH_CHECK, ExceptionSupraUtil.EXCEPTION_VALIDATION);
			errorMessage = errorMessage.replace("{fieldName}", fieldName);
			errorMessage = errorMessage.replace("{size}", fieldSizeExpected+"");
			String message = ExceptionSupraValidationsConstants.MAX_FIELD_LENGTH_CHECK; 
			message = message+=" - "+errorMessage;
			message += " - "+SUPRASERVICES_EXCEPTION;
			throw new ValidationSupraException(message);
		}
			
	}
	
	
	
	public static void throwNoDataException() throws BusinessSupraException{
		
		ExceptionSupraUtil.throwException(ExceptionSupraBusinessConstants.NO_DATA_FOUND, ExceptionSupraUtil.EXCEPTION_BUSINESS);
	}
	
	public static void throwInvalidYesNoException(int yesNo, boolean isRequired, String fieldName) throws Exception{
		boolean isValid = true;
		
		String yesNoStr = String.valueOf(yesNo);
		
		if(isRequired){
			
			if(yesNoStr == null || yesNoStr.isEmpty()){
				
				ExceptionSupraUtil.throwNullOrEmptyValidationException(fieldName, yesNoStr, true);
			}
			
			isValid = (yesNoStr.equalsIgnoreCase("0") || yesNoStr.equalsIgnoreCase("1"))?true:false;
			
		}else{
			
			if(yesNoStr != null && !yesNoStr.isEmpty() && !yesNoStr.equals("null") && !yesNoStr.equals("0")){
				
				isValid = (yesNoStr.equalsIgnoreCase("0") || yesNoStr.equalsIgnoreCase("1"))?true:false;
			}
			
		}
		
		if(!isValid){
			throwExceptionFinal(fieldName, ExceptionSupraValidationsConstants.INVALID_YES_NO, ExceptionSupraUtil.EXCEPTION_VALIDATION);
		}
		
	}
	
	public static String getFinalExcCause(PersistenceException ex){
		
		Throwable cause = ex.getCause();
		String message = cause.getMessage();
		
		while(cause != null){
			
			cause = cause.getCause();
			
			if(cause != null){
				
				message = cause.getMessage();
			}
		}
		
		return message;
	}
	
	public static String getErrorMessageFromProps(String errorCode, int excType){
		String errorMessage = "";
		
		if(resource_validation == null){
			
			resource_validation = ResourceBundle.getBundle(PROPS_VALIDATIONS);
		}
		
		if(resource_business == null){
			
			resource_business = ResourceBundle.getBundle(PROPS_BUSINESS);
		}
		
		if(excType == EXCEPTION_VALIDATION){
			
			errorMessage = resource_validation.getString(errorCode);
			
		}else if(excType == EXCEPTION_BUSINESS){
			
			errorMessage = resource_business.getString(errorCode);
		}
		
		return errorMessage;
	}
	
	public  static void logDTOStackTrace(String endpointServiceName, Exception ex, Object dtoObj) {
		
		if(ex instanceof BusinessSupraException){
			
			loggerInfo.info(CommonSupraUtil.generateStackTraceFromDTO(dtoObj, endpointServiceName), ex);	//throw ex;
			
		}else if(ex instanceof ValidationSupraException){
			
			loggerInfo.info(CommonSupraUtil.generateStackTraceFromDTO(dtoObj, endpointServiceName), ex);	//throw ex;
			
		}else{
			
			logger.error(CommonSupraUtil.generateStackTraceFromDTO(dtoObj, endpointServiceName), ex);	//throw ex;
		}
	}
	
	public  static void logParamStackTrace(String endpointServiceName, Exception ex, String param) {
		
		if(ex instanceof BusinessSupraException){
			
			loggerInfo.info(param,ex);
			
		}else if(ex instanceof ValidationSupraException){
			
			loggerInfo.info(param,ex);
			
		}else{
			
			logger.error(param,ex);
		}
	}
}
