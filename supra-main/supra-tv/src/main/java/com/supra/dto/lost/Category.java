package com.supra.dto.lost;


import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

//@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Category  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Integer id;
	public String name;
	public Boolean isSelected;
	
	
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
	public Boolean getIsSelected() {
		return isSelected;
	}
	public void setIsSelected(Boolean isSelected) {
		this.isSelected = isSelected;
	}
	@Override
	public String toString() {
		return "\tCategory [id=" + id + ", name=" + name + ", isSelected=" + isSelected + "]";
	}
	
	

}