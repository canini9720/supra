package com.supra.dto.lost.dp;

import java.util.List;

public class LostAndFoundResponseDTO {
	
	private String refNo;
	private String dpRefNo;
	private String message;
	private List<String> subItemRefNos;
	private Integer lostItemId;
	private Boolean status;
	
	
	public String getRefNo() {
		return refNo;
	}
	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}
	public String getDpRefNo() {
		return dpRefNo;
	}
	public void setDpRefNo(String dpRefNo) {
		this.dpRefNo = dpRefNo;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public List<String> getSubItemRefNos() {
		return subItemRefNos;
	}
	public void setSubItemRefNos(List<String> subItemRefNos) {
		this.subItemRefNos = subItemRefNos;
	}
	public Integer getLostItemId() {
		return lostItemId;
	}
	public void setLostItemId(Integer lostItemId) {
		this.lostItemId = lostItemId;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "LostAndFoundResponseDTO [refNo=" + refNo + ", dpRefNo="
				+ dpRefNo + ", message=" + message + ", subItemRefNos="
				+ subItemRefNos + ", lostItemId=" + lostItemId + ", status="
				+ status + "]";
	}
	


}
