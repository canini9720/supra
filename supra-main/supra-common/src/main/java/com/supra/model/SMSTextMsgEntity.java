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
@Table(name="SMS_TEXT_MSG")
public class SMSTextMsgEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="MSGCODE")
	private String msgCode;
	
	@Id
	@Column(name="LANGID")
	private Long langId;
	
	@Column(name="MSG")
	private String msg;

	public String getMsgCode() {
		return msgCode;
	}

	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}

	public Long getLangId() {
		return langId;
	}

	public void setLangId(Long langId) {
		this.langId = langId;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "SMSTextMsgEntity [msgCode=" + msgCode + ", langId=" + langId + ", msg=" + msg + "]";
	}

	
	
	
}