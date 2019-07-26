package com.supra.dto.lost.dp;
import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

//import ae.dubaipolice.common.dto.CommonAppBasicDtlsDTO;
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class LostItemApplicationDTO implements Serializable{// extends CommonAppBasicDtlsDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long emiratesId;
	private String email;
	private LostItemNewDTO lostItemDTO;
	private Long referenceNo;
	
	
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
	public LostItemNewDTO getLostItemDTO() {
		return lostItemDTO;
	}
	public void setLostItemDTO(LostItemNewDTO lostItemDTO) {
		this.lostItemDTO = lostItemDTO;
	}
	
	public Long getReferenceNo() {
		return referenceNo;
	}
	public void setReferenceNo(Long referenceNo) {
		this.referenceNo = referenceNo;
	}
	@Override
	public String toString() {
		return "\nLostItemApplicationDTO [emiratesId=" + emiratesId + ", email="
				+ email + ", lostItemDTO=" + lostItemDTO + "]";
	}
	
	

}
