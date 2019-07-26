package com.supra.dto.lost.up;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
@XmlAccessorType(XmlAccessType.FIELD)
public class LostItemDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer categoryId;
	private Integer subCategoryId;
	private String description;
	private String lostDate;
	private String remarks;
	private LocationDTO locationDTO;
	private AttachmentDTO attachmentDTO;
	private List<LostSubItemDTO> lisrSubItems;
	
	
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
	public LocationDTO getLocationDTO() {
		return locationDTO;
	}
	public void setLocationDTO(LocationDTO locationDTO) {
		this.locationDTO = locationDTO;
	}
	public AttachmentDTO getAttachmentDTO() {
		return attachmentDTO;
	}
	public void setAttachmentDTO(AttachmentDTO attachmentDTO) {
		this.attachmentDTO = attachmentDTO;
	}
	public List<LostSubItemDTO> getLisrSubItems() {
		return lisrSubItems;
	}
	public void setLisrSubItems(List<LostSubItemDTO> lisrSubItems) {
		this.lisrSubItems = lisrSubItems;
	}
	
	
	

}
