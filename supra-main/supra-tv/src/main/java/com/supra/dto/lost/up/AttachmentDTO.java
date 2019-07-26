package com.supra.dto.lost.up;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


@XmlAccessorType(XmlAccessType.FIELD)
public class AttachmentDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	public BaseItemAttachmentDTO baseItemAttachmentDto;
	
	
	public BaseItemAttachmentDTO getBaseItemAttachmentDto() {
		return baseItemAttachmentDto;
	}
	public void setBaseItemAttachmentDto(BaseItemAttachmentDTO baseItemAttachmentDto) {
		this.baseItemAttachmentDto = baseItemAttachmentDto;
	}


}
