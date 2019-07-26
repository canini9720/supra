package com.supra.dto.lost.up;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class LostItemApplicationDTO  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long emiratesId;
	private String email;
	private LostItemDTO lostItemDTO;
	
	
	public Long getEmiratesId() {
		return emiratesId;
	}
	public void setEmiratesId(Long emiratesId) {
		this.emiratesId = emiratesId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LostItemDTO getLostItemDTO() {
		return lostItemDTO;
	}
	public void setLostItemDTO(LostItemDTO lostItemDTO) {
		this.lostItemDTO = lostItemDTO;
	}
	
	

}
