package com.supra.dto.lost;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AttrValue implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Integer id;
	public String value;
	public LnfSubcatAttribute lnfSubcatAttribute;

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

	public LnfSubcatAttribute getLnfSubcatAttribute() {
		return lnfSubcatAttribute;
	}

	public void setLnfSubcatAttribute(LnfSubcatAttribute lnfSubcatAttribute) {
		this.lnfSubcatAttribute = lnfSubcatAttribute;
	}

	@Override
	public String toString() {
		return "AttrValue [\nid=" + id + ", \nvalue=" + value + ",\n lnfSubcatAttribute=" + lnfSubcatAttribute + "]";
	}

	

}