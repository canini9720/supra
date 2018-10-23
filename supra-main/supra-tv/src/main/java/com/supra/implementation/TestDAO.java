package com.supra.implementation;
import com.supra.dto.TestDTO;
import com.supra.model.TestEntity;

public interface TestDAO {


	

	public void saveTest(TestEntity testEntity, TestDTO dto)throws Exception;

}