package com.supra.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="TESTTABLE")
public class TestEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="testSeq", sequenceName="TEST_SEQ", allocationSize=1)
	@GeneratedValue( strategy=GenerationType.SEQUENCE,generator="testSeq")
	@Column(name="ID")
	private Long id;
	
	@Column(name="NAME")
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}