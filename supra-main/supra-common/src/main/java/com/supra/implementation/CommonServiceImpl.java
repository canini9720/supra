package com.supra.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.supra.common.implementation.CommonDAO;
import com.supra.model.SMSInternalAlertEntity;
import com.supra.model.SMSTextMsgEntity;
import com.supra.service.CommonService;

@Service("commonService")
@Transactional
public class CommonServiceImpl implements CommonService {
	
	@Autowired
	CommonDAO commonDAO;

	@Override
	public SMSInternalAlertEntity smsAlert(long serviceId, String msgCode) throws Exception {
		
		return commonDAO.smsAlert(serviceId, msgCode);
	}

	@Override
	public List<SMSTextMsgEntity> getAllSMSText() throws Exception {
		return commonDAO.getAllSMSText();
	}

}
