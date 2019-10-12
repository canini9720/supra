package com.supra.service;

import java.util.List;

import com.supra.model.SMSInternalAlertEntity;
import com.supra.model.SMSTextMsgEntity;

public interface CommonService {

	public SMSInternalAlertEntity smsAlert(long serviceId, String msgCode) throws Exception;
	
	public List<SMSTextMsgEntity> getAllSMSText() throws Exception;
}
