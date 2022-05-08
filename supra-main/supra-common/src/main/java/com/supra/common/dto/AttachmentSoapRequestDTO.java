package com.supra.common.dto;


import java.io.Serializable;
import java.rmi.Remote;

import javax.security.auth.callback.Callback;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AttachmentSoapRequestDTO implements Serializable,Callback,Remote{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlElement(required = true)
	private String attachName;
	@XmlElement(required = true)
    private byte[] attachment;
	@XmlElement(required = false)
	private String contentType;
	@XmlElement(required = false)
	private Integer attachmentTypeId;
	@XmlElement(required = false)
	private Long attachId;
	
	public String getAttachName() {
		return attachName;
	}
	public void setAttachName(String attachName) {
		this.attachName = attachName;
	}
	public byte[] getAttachment() {
		return attachment;
	}
	public void setAttachment(byte[] attachment) {
		this.attachment = attachment;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public Integer getAttachmentTypeId() {
		return attachmentTypeId;
	}
	public void setAttachmentTypeId(Integer attachmentTypeId) {
		this.attachmentTypeId = attachmentTypeId;
	}
	public Long getAttachId() {
		return attachId;
	}
	public void setAttachId(Long attachId) {
		this.attachId = attachId;
	}
	
	
	
}