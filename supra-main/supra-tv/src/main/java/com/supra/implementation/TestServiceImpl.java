package com.supra.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.supra.common.implementation.CommonDAO;
import com.supra.common.implementation.CommonService;
import com.supra.dto.TestDTO;
import com.supra.model.SupraLogEntity;
import com.supra.model.SupraLogEntityDefault;
import com.supra.model.TestEntity;
import com.supra.service.TestService;
import com.supra.task.TvAsyncTask;


@Service("testService")
@Transactional
public class TestServiceImpl implements TestService {

	@Autowired
	TestDAO testDAO;
	
	@Autowired
	CommonService commonService;
	
	@Autowired
	TvAsyncTask tvAsyncTask;
	
	@Override
	public Long saveTest(TestDTO dto) throws Exception {
		
		
		SupraLogEntityDefault supraLogDefault=new SupraLogEntityDefault();
		supraLogDefault.setLog("This default is log");
		commonService.saveSupraLogWithDefaultTransaction(supraLogDefault);
		
		TestEntity testEntity=new TestEntity();
		testEntity.setName("Tester45");
		
		SupraLogEntity supraLog=new SupraLogEntity();
		supraLog.setLog("This is log");
		commonService.saveSupraLog(supraLog);
		System.out.println("supra log entry entity saved");
		
		testDAO.saveTest(testEntity,dto);
		System.out.println("Test entity saved");
		tvAsyncTask.doTvAsyncTask();
		return testEntity.getId();
	}
	
	@Async("specificTaskExecutor")
	public void doAsyc() throws InterruptedException{
		Thread.sleep(15000l);
	}

}