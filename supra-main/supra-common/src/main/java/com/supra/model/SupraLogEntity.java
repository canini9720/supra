package com.supra.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="SUPRA_LOG_ENTRY")
public class SupraLogEntity {

	@Id
	@SequenceGenerator(name="testSeq", sequenceName="TEST_SEQ", allocationSize=1)
	@GeneratedValue( strategy=GenerationType.SEQUENCE,generator="testSeq")
	@Column(name="ID")
	private Long id;
	
	@Column(name="LOG_REQUEST")
	private String log;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}
	

}
