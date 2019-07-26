package com.supra.dto.lost.dp;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class ImageDTO implements Serializable{
	
	private String fileName;
	private String mimeType;
	private String attachmentBase64;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public String getAttachmentBase64() {
		return attachmentBase64;
	}
	public void setAttachmentBase64(String attachmentBase64) {
		this.attachmentBase64 = attachmentBase64;
	}
	
	

}
