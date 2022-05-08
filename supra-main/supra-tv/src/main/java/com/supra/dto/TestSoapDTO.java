package com.supra.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="TestSoapDTO")
@XmlAccessorType(XmlAccessType.FIELD)
public class TestSoapDTO {

	private static final long serialVersionUID = 1L;
	
	@XmlElement(required = false)
	private String name;

	@XmlElement(required = false)
	private Integer age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	
	
	
	
}