package com.supra.common.implementation;

import org.springframework.stereotype.Repository;

import com.supra.model.SupraLogEntity;


@Repository
public class CommonDAOImpl extends JdbcCommonDao implements CommonDAO {

	public CommonDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void saveSupraLog(SupraLogEntity entity) throws Exception {
		this.getEm().persist(entity);
		System.out.println("SupraLogEntity saved");
	}
	

}
