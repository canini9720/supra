package com.supra.test;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.supra.implementation.TestDAO;
import com.supra.implementation.TestDAOImpl;




@Configuration
@ComponentScan("com.supra")
public class SwatTestConfig {

	
	@Autowired
	TestDAO testDAO;
	
	
    
	
	
	
	
	
	@Bean
    public TestDAO testDAO() {
        return new TestDAOImpl();
    }
	@Bean
    public VelocityEngine VelocityEngine() {
        return new VelocityEngine();
    }
	
  
	/*@Bean ApplicationProperties applicationProperties(){
		return new ApplicationProperties();
	}*/
    	
   
    
    
    
   
}