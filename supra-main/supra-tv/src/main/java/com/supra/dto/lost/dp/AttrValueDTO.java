package com.supra.dto.lost.dp;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;


@XmlAccessorType(XmlAccessType.FIELD)
public class AttrValueDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String value;
	private LnfSubcatAttributeDTO lnfSubcatAttribute;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	

	public LnfSubcatAttributeDTO getLnfSubcatAttribute() {
		return lnfSubcatAttribute;
	}

	public void setLnfSubcatAttribute(LnfSubcatAttributeDTO lnfSubcatAttribute) {
		this.lnfSubcatAttribute = lnfSubcatAttribute;
	}

	@Override
	public String toString() {
		return "AttrValueDTO [id=" + id + ", value=" + value
				+ ", lnfSubcatAttribute=" + lnfSubcatAttribute + "]";
	}

	

	
	

}