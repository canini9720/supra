package com.supra.common.implementation;

import java.sql.Connection;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

public class JdbcCommonDao {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall simpleJdbcCall;
	@PersistenceContext
	private EntityManager em;
	
	
	@Autowired
	@Qualifier("dataSource")
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.simpleJdbcCall = new SimpleJdbcCall(dataSource);
		
	}
	
	
	public DataSource getDataSource() {
		return dataSource;
	}
	
	public Connection getConnection() throws SQLException {
		return this.dataSource.getConnection();
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	public SimpleJdbcCall getSimpleJdbcCall() {
		return simpleJdbcCall;
	}

	public EntityManager getEm() {
		return em;
	}


	

}