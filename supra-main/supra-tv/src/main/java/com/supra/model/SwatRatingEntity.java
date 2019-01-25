package com.supra.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="SWAT_RATING")
public class SwatRatingEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID")
	private Long id;
	
	@Column(name="REFERENCE_NO")
	private Long refNo;

	@Column(name="FOR_DAY")
	private Long forDay;
	
	@Column(name="UPDATED_DATE")
	private Date updatedDate;
	
	@ManyToOne
    @JoinColumn(name="REFERENCE_NO",insertable=false,updatable=false)
    private SwatRequestEntity swatreq;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRefNo() {
		return refNo;
	}

	public void setRefNo(Long refNo) {
		this.refNo = refNo;
	}

	public Long getForDay() {
		return forDay;
	}

	public void setForDay(Long forDay) {
		this.forDay = forDay;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public SwatRequestEntity getSwatreq() {
		return swatreq;
	}

	public void setSwatreq(SwatRequestEntity swatreq) {
		this.swatreq = swatreq;
	}
	
	

		
}