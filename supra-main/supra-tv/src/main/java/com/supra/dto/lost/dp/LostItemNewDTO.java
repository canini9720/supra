package com.supra.dto.lost.dp;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
@XmlAccessorType(XmlAccessType.FIELD)


public class LostItemNewDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private Integer categoryId;
	private Integer subCategoryId;
	private String description;
	private String lostDate;
	private String remarks;
	private List<LocationDTO> locationDTO;
	private List<AttachmentDTO> attachmentDTO;
	private List<ImageDTO> imageDTO;
	private List<LostSubItemDTO> listSubItems;
	private List<AttrValueDTO> attrValues ;
	
	
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
	public String getLostDate() {
		return lostDate;
	}
	public void setLostDate(String lostDate) {
		this.lostDate = lostDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public List<LocationDTO> getLocationDTO() {
		return locationDTO;
	}
	public void setLocationDTO(List<LocationDTO> locationDTO) {
		this.locationDTO = locationDTO;
	}
	/*public AttachmentDTO getAttachmentDTO() {
		return attachmentDTO;
	}
	public void setAttachmentDTO(AttachmentDTO attachmentDTO) {
		this.attachmentDTO = attachmentDTO;
	}*/
	public List<LostSubItemDTO> getListSubItems() {
		return listSubItems;
	}
	public void setListSubItems(List<LostSubItemDTO> listSubItems) {
		this.listSubItems = listSubItems;
	}
	
	public List<AttrValueDTO> getAttrValues() {
		return attrValues;
	}
	public void setAttrValues(List<AttrValueDTO> attrValues) {
		this.attrValues = attrValues;
	}
	
	public List<AttachmentDTO> getAttachmentDTO() {
		return attachmentDTO;
	}
	public void setAttachmentDTO(List<AttachmentDTO> attachmentDTO) {
		this.attachmentDTO = attachmentDTO;
	}
	
	
	
	public List<ImageDTO> getImageDTO() {
		return imageDTO;
	}
	public void setImageDTO(List<ImageDTO> imageDTO) {
		this.imageDTO = imageDTO;
	}
	@Override
	public String toString() {
		return "\n\tLostItemNewDTO [categoryId=" + categoryId + ", subCategoryId="
				+ subCategoryId + ", description=" + description
				+ ", lostDate=" + lostDate + ", remarks=" + remarks
				+ ", locationDTO=" + locationDTO + ", \t\tattachmentDTO="
				+ attachmentDTO + ", \t\tlistSubItems=" + listSubItems
				+ ",\t\t attrValues=" + attrValues + "]";
	}
	
	

	
	
	

}
