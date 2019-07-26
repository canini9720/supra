package com.supra.dto.lost.dp;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


@XmlAccessorType(XmlAccessType.FIELD)
public class AttachmentDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private BaseItemAttachmentDTO baseItemAttachment;
	public BaseItemAttachmentDTO getBaseItemAttachment() {
		return baseItemAttachment;
	}
	public void setBaseItemAttachment(BaseItemAttachmentDTO baseItemAttachment) {
		this.baseItemAttachment = baseItemAttachment;
	}
	/*public BaseItemAttachmentDTO[] getBaseItemAttachment() {
		return baseItemAttachment;
	}
	public void setBaseItemAttachment(BaseItemAttachmentDTO[] baseItemAttachment) {
		this.baseItemAttachment = baseItemAttachment;
	}*/
	
	
	
	
	/*private static final long serialVersionUID = 1L;

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
	@Override
	public String toString() {
		return "\t\t\tAttachmentDTO [\ntempId=" + tempId + ", \nfileName=" + fileName
				+ ", \nmimeType=" + mimeType + "]";
	}*/
	


}
