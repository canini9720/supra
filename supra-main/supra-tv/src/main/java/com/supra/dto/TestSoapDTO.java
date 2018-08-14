package com.supra.dto;

import javax.xml.bind.annotation.XmlElement;

public class TestSoapDTO {

	private static final long serialVersionUID = 1L;
	
	@XmlElement(required = false)
	private Long emiratesID;

	@XmlElement(required = false)
	private Long referenceNo;
}