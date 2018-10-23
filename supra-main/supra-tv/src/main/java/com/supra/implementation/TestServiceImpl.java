package com.supra.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.supra.common.implementation.CommonDAO;
import com.supra.common.implementation.CommonService;
import com.supra.dto.TestDTO;
import com.supra.model.SupraLogEntity;
import com.supra.model.TestEntity;
import com.supra.service.TestService;


@Service("testService")
@Transactional
public class TestServiceImpl implements TestService {

	@Autowired
	TestDAO testDAO;
	
	@Autowired
	CommonService commonService;
	
	@Override
	public Long saveTest(TestDTO dto) throws Exception {
		TestEntity testEntity=new TestEntity();
		testEntity.setName("Tester45");
		
		SupraLogEntity supraLog=new SupraLogEntity();
		supraLog.setLog("This is log");
		commonService.saveSupraLog(supraLog);
		System.out.println("supra log entry entity saved");
		
		testDAO.saveTest(testEntity,dto);
		System.out.println("Test entity saved");
		return testEntity.getId();
	}

}