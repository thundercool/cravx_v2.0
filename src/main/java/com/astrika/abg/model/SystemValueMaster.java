package com.astrika.abg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

@Audited
@Entity
public class SystemValueMaster {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long serialID;
	
	@Column
	private Integer sequence_Count;

	@Column(length = 25)
	private String name;
	
	@Column(length = 100)
	private String value;
	
	@Column
	@Type(type="org.hibernate.type.NumericBooleanType")
	private Boolean active=true;


	public Long getSerialID() {
		return serialID;
	}

	public void setSerialID(Long serialID) {
		this.serialID = serialID;
	}

	public Integer getSequence_Count() {
		return sequence_Count;
	}

	public void setSequence_Count(Integer sequence_Count) {
		this.sequence_Count = sequence_Count;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	
}
