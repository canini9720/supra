package com.supra.common.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.supra.common.implementation.ApplicationPropertyDAO;
import com.supra.model.ApplicationSupraPropertyEntity;



@Component("ApplicationProperties")
public class SystemProperties {

	public static Map<String, String> props = new HashMap<String, String>();
	
	@Autowired ApplicationPropertyDAO dao;
	
	@PostConstruct
	public void loadProperties(){
		List<ApplicationSupraPropertyEntity> list = dao.getPropertiesfromDB();
		if(list!=null && list.size()>0){
			for(int i=0; i<list.size(); i++){
				props.put(list.get(i).getName().trim(), list.get(i).getValue().trim());
			}
			System.out.println("app prop List="+list);
		}
	}
}
