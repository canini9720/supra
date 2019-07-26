package com.supra.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="DIP_COMM_VW")
public class DipCommVWEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="dip_id")
	private Long dipId;
	
	@Column(name="req_type")
	private Long reqType;

	@Column(name="det")
	private String det;
	
	@Column(name="cr_date")
	private Date crDate;
	
	@Column(name="dp_user_id")
	private Long dpUserId;
	
	@Lob
	@Column(name="attch")
	private byte[] attach;
	
	
	@Column(name="ref_no")
	private Long refNo;
	

	public Long getDipId() {
		return dipId;
	}

	public void setDipId(Long dipId) {
		this.dipId = dipId;
	}

	public Long getReqType() {
		return reqType;
	}

	public void setReqType(Long reqType) {
		this.reqType = reqType;
	}

	public String getDet() {
		return det;
	}

	public void setDet(String det) {
		this.det = det;
	}

	public Date getCrDate() {
		return crDate;
	}

	public void setCrDate(Date crDate) {
		this.crDate = crDate;
	}

	public Long getDpUserId() {
		return dpUserId;
	}

	public void setDpUserId(Long dpUserId) {
		this.dpUserId = dpUserId;
	}

	public byte[] getAttach() {
		return attach;
	}

	public void setAttach(byte[] attach) {
		this.attach = attach;
	}

	public Long getRefNo() {
		return refNo;
	}

	public void setRefNo(Long refNo) {
		this.refNo = refNo;
	}	
	
	
}