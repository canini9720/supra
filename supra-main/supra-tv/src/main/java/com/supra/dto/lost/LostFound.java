package com.supra.dto.lost;


import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.txw2.annotation.XmlElement;
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class LostFound  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public String description;
	public Integer id;
	public String lstDateFrom;
	public String lstDateTo;
	public String remarkEn;
	public String type;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLstDateFrom() {
		return lstDateFrom;
	}
	public void setLstDateFrom(String lstDateFrom) {
		this.lstDateFrom = lstDateFrom;
	}
	public String getLstDateTo() {
		return lstDateTo;
	}
	public void setLstDateTo(String lstDateTo) {
		this.lstDateTo = lstDateTo;
	}
	public String getRemarkEn() {
		return remarkEn;
	}
	public void setRemarkEn(String remarkEn) {
		this.remarkEn = remarkEn;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public List<AttrValue> attrValues = null;
	public Category category;
	public List<Attachment> attachments = null;
	public List<Location> locations = null;
	public LostOrFoundBy lostOrFoundBy;
	public SubCategory subCategory;
	
	
	
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public List<AttrValue> getAttrValues() {
		return attrValues;
	}
	public void setAttrValues(List<AttrValue> attrValues) {
		this.attrValues = attrValues;
	}
	public List<Attachment> getAttachments() {
		return attachments;
	}
	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}
	public List<Location> getLocations() {
		return locations;
	}
	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}
	@Override
	public String toString() {
		return "LostFound [\ndescription=" + description + ", \nid=" + id + ",\n lstDateFrom=" + lstDateFrom + ", \nlstDateTo="
				+ lstDateTo + ",\n remarkEn=" + remarkEn + ",\n type=" + type + ",\n\t attrValues=" + attrValues + ",\n\t category="
				+ category + ",\n\t attachments=" + attachments + ",\n\t locations=" + locations + ", \n\tlostOrFoundBy="
				+ lostOrFoundBy + ",\n\t subCategory=" + subCategory + "]";
	}
	
	
	


}
