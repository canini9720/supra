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

	@XmlElement(required = false)
	private Long reqTypeId;

	@XmlElement(required = false)
	private String fromDate;
	
	@XmlElement(required = false)
	private String toDate;
	
	@XmlElement(required = false)
	private Long dpUserId;
	
	@XmlElement(required = false)
	private Integer startPage;
	
	@XmlElement(required = false)
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
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
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