package com.supra.common.implementation;

import java.util.List;

import com.supra.model.SMSInternalAlertEntity;
import com.supra.model.SMSTextMsgEntity;

public interface CommonDAO {

	public SMSInternalAlertEntity smsAlert(long serviceId, String msgCode) throws Exception;
	
	public List<SMSTextMsgEntity> getAllSMSText() throws Exception;
}
