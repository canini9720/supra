package com.supra.dto.lost;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Attachment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public BaseItemAttachment baseItemAttachment;

	public BaseItemAttachment getBaseItemAttachment() {
		return baseItemAttachment;
	}

	public void setBaseItemAttachment(BaseItemAttachment baseItemAttachment) {
		this.baseItemAttachment = baseItemAttachment;
	}

	@Override
	public String toString() {
		return "\tAttachment [baseItemAttachment=" + baseItemAttachment + "]";
	}
	
	
	
	

}