package com.supra.implementation;
import org.springframework.stereotype.Repository;

import com.supra.common.implementation.JdbcCommonDao;
import com.supra.model.TestEntity;

@Repository
public class TestDAOImpl extends JdbcCommonDao implements TestDAO{

	//@Override
	public void saveTest(TestEntity entity) throws Exception {
		this.getEm().persist(entity);
		System.out.println("tested");
	}

	


}