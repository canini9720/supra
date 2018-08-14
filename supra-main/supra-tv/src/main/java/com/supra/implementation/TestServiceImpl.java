package com.supra.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.supra.dto.TestDTO;
import com.supra.model.TestEntity;
import com.supra.service.TestService;


@Service("testService")
@Transactional
public class TestServiceImpl implements TestService {

	@Autowired
	TestDAO testDAO;
	
	@Override
	public Long saveTest(TestDTO dto) throws Exception {
		/*Random r = new Random();
		int Low = 10;
		int High = 100;
		int Result = r.nextInt(High-Low) + Low;*/
		TestEntity testEntity=new TestEntity();
		//testEntity.setId(1l);
		testEntity.setName("Tester45");
		testDAO.saveTest(testEntity);
		System.out.println("cussesfuly inserted");
		return testEntity.getId();
	}

}