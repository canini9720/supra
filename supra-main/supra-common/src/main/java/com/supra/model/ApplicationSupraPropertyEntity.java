package com.supra.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SYSTEM_PROPERTIES")
public class ApplicationSupraPropertyEntity {

	@Id
	@Column(name="ID")
	public long id;
	
	@Column(name="NAME")
	public String name;
	
	@Column(name="VALUE")
	public String value;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}

