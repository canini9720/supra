package com.supra.implementation;
import org.springframework.stereotype.Repository;

import com.supra.common.implementation.JdbcCommonDao;
import com.supra.dto.TestDTO;
import com.supra.exception.BusinessException;
import com.supra.model.TestEntity;

@Repository
public class TestDAOImpl extends JdbcCommonDao implements TestDAO{

	//@Override
	public void saveTest(TestEntity entity,TestDTO dto) throws Exception {
		if(dto.getEmiratesID().longValue()==5l){
			throw new BusinessException("busin exp");			
			//throw new Exception("busin exp");
		}

		this.getEm().persist(entity);
		System.out.println("tested");
	}



	/*@Override
	public void saveTest(TestEntity entity) throws Exception {
		this.getEm().persist(entity);
		
	}*/


}