package com.supra.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="SWAT_REQUEST")
public class SwatRequestEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="REFERENCE_NO")
	private Long refNo;
	
	@Column(name="TEAM_NAME")
	private String teamName;

	@OneToMany(mappedBy="swatreq")
    private Set<SwatRatingEntity> ratingEntity;
	
	public Long getRefNo() {
		return refNo;
	}

	public void setRefNo(Long refNo) {
		this.refNo = refNo;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Set<SwatRatingEntity> getRatingEntity() {
		return ratingEntity;
	}

	public void setRatingEntity(Set<SwatRatingEntity> ratingEntity) {
		this.ratingEntity = ratingEntity;
	}

		
}