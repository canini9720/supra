package com.supra.common.implementation;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.supra.model.ApplicationSupraPropertyEntity;

@Repository
public class ApplicationPropertyDAOImpl extends JdbcCommonDao implements ApplicationPropertyDAO{

	@Override
	@Transactional
	public List<ApplicationSupraPropertyEntity> getPropertiesfromDB() {
		// TODO Auto-generated method stub
		
		List<ApplicationSupraPropertyEntity> list = null;
		list = this.getEm().createQuery("select a from ApplicationSupraPropertyEntity a", ApplicationSupraPropertyEntity.class)
				.getResultList();
		
		return list;
	}

	
}
