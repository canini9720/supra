package com.supra.common.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.supra.model.SupraLogEntity;

@Service("commonService")
@Transactional
public class CommonServiceImpl implements CommonService {
	
	@Autowired
	CommonDAO commonDAO;
	

	public CommonServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void saveSupraLog(SupraLogEntity entity) throws Exception {
		commonDAO.saveSupraLog(entity);

	}

}
