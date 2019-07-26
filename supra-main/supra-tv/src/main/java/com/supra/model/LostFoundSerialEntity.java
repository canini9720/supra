package com.supra.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="LOST_FOUND_SERIAL")
public class LostFoundSerialEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID")
	private Integer id;
	
	@Column(name="SERIALIZED_OBJECT")
	@Lob
	private byte[] lfObject;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public byte[] getLfObject() {
		return lfObject;
	}

	public void setLfObject(byte[] lfObject) {
		this.lfObject = lfObject;
	}

	
	
	

}
