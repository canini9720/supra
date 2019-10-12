package com.supra.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="SMS_INTERNAL_ALERT")
public class SMSInternalAlertEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="SERVICEID")
	private Long serviceId;
	
	
	@Column(name="LANGID")
	private Long langId;
	
	@Column(name="MSGCODE")
	private String msgCode;
	
	@Column(name="MOBILENO")
	private Long mobileNo;

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public Long getLangId() {
		return langId;
	}

	public void setLangId(Long langId) {
		this.langId = langId;
	}

	public String getMsgCode() {
		return msgCode;
	}

	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}

	public Long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}

	@Override
	public String toString() {
		return "SMSInternalAlertEntity [serviceId=" + serviceId + ", langId=" + langId + ", msgCode=" + msgCode
				+ ", mobileNo=" + mobileNo + "]";
	}

	
	
	
	
}