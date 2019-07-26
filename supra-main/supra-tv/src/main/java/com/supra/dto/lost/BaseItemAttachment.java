package com.supra.dto.lost;


import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
//@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class BaseItemAttachment  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Integer tempId;
	public String fileName;
	public String mimeType;
	public Integer getTempId() {
		return tempId;
	}
	public void setTempId(Integer tempId) {
		this.tempId = tempId;
	}
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
	
	

}