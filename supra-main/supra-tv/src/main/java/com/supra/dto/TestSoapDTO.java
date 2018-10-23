package com.supra.dto;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TestSoapDTO {

	private static final long serialVersionUID = 1L;
	
	@XmlElement(required = false)
	private Long emiratesID;

	@XmlElement(required = false)
	private Long referenceNo;

	public Long getEmiratesID() {
		return emiratesID;
	}
	public void setEmiratesID(Long emiratesID) {
		this.emiratesID = emiratesID;
	}
	public Long getReferenceNo() {
		return referenceNo;
	}
	public void setReferenceNo(Long referenceNo) {
		this.referenceNo = referenceNo;
	}
	
	
}