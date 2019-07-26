package com.supra.dto.lost.up;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
@XmlAccessorType(XmlAccessType.FIELD)
public class LostSubItemDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
			
	private Integer categoryId;
	private Integer subCategoryId;
	private String description;
	private String remarks;
	private AttachmentDTO attachmentDTO;
	
	
	
	

}
