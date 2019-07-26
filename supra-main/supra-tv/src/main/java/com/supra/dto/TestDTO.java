package com.supra.dto;
import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;

public class TestDTO implements Serializable{

	private Long emiratesID;
	private Long referenceNo;
	
	private Long reqTypeId;

	
	private Date fromDate;
	
	
	private Date toDate;
	
	
	private Long dpUserId;
	
	private Integer startPage;
	
	private Integer endPage;
	
	
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
	public Long getReqTypeId() {
		return reqTypeId;
	}
	public void setReqTypeId(Long reqTypeId) {
		this.reqTypeId = reqTypeId;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public Long getDpUserId() {
		return dpUserId;
	}
	public void setDpUserId(Long dpUserId) {
		this.dpUserId = dpUserId;
	}
	public Integer getStartPage() {
		return startPage;
	}
	public void setStartPage(Integer startPage) {
		this.startPage = startPage;
	}
	public Integer getEndPage() {
		return endPage;
	}
	public void setEndPage(Integer endPage) {
		this.endPage = endPage;
	}
	
	
}