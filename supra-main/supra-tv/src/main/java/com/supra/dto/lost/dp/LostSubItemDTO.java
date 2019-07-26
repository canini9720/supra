package com.supra.dto.lost.dp;


import java.io.Serializable;
import java.util.List;

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
	private List<AttachmentDTO> attachmentDTO;
	private List<AttrValueDTO> attrValues ;
	private List<ImageDTO> imageDTO;
	
	
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public Integer getSubCategoryId() {
		return subCategoryId;
	}
	public void setSubCategoryId(Integer subCategoryId) {
		this.subCategoryId = subCategoryId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/*public AttachmentDTO getAttachmentDTO() {
		return attachmentDTO;
	}
	public void setAttachmentDTO(AttachmentDTO attachmentDTO) {
		this.attachmentDTO = attachmentDTO;
	}*/
	
	
	public List<AttrValueDTO> getAttrValues() {
		return attrValues;
	}
	public List<AttachmentDTO> getAttachmentDTO() {
		return attachmentDTO;
	}
	public void setAttachmentDTO(List<AttachmentDTO> attachmentDTO) {
		this.attachmentDTO = attachmentDTO;
	}
	public void setAttrValues(List<AttrValueDTO> attrValues) {
		this.attrValues = attrValues;
	}
	
	
	public List<ImageDTO> getImageDTO() {
		return imageDTO;
	}
	public void setImageDTO(List<ImageDTO> imageDTO) {
		this.imageDTO = imageDTO;
	}
	@Override
	public String toString() {
		return "LostSubItemDTO [categoryId=" + categoryId + ", subCategoryId="
				+ subCategoryId + ", description=" + description + ", remarks="
				+ remarks + ", attachmentDTO=" + attachmentDTO
				+ ", attrValues=" + attrValues + "]";
	}
	
	
	
	
	
	

}
