package com.supra.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.supra.common.testconfig.CommonTestConfig;
import com.supra.dto.TestDTO;
import com.supra.service.TestService;

import junit.framework.Assert;




@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CommonTestConfig.class, SwatTestConfig.class})
//@TestPropertySource("classpath:dpservices.properties")
//@PropertySource("classpath*:/WEB-INF/props/dpservices.properties")
//@PropertySource(value={"file://D:/app/dpservices.properties"})
@Transactional
@DependsOn("ApplicationProperties")
public class SwatTest {
	
	
	
	
	@Autowired
	private TestService testService;
	
	
	
	

	@Value("${service.ashraf}")
	private Integer serviceId;
	
	
	@Test
	public void saveTest() throws Exception {
		
		
		System.out.println("testService="+testService+",prop="+serviceId);
		

		Long refid=testService.saveTest(initialze());
		//TestDTO testDTO = new TestDTO();
		//Long udcont=testService.saveTest(testDTO);
		System.out.println("refid="+refid);
		 
		 Assert.assertNotNull(refid);
		//Assert.assertEquals(true, true);
		
	}
	
	private TestDTO initialze(){
		TestDTO dto=new TestDTO();
		
		
		return dto;
	}

}

