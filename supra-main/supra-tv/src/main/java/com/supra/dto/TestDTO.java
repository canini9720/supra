package com.supra.dto;
import java.io.Serializable;

public class TestDTO implements Serializable{

	private Long emiratesID;
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