package com.supra.dto.lost;


import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

//@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Nationality  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Integer id;
	public String name;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}