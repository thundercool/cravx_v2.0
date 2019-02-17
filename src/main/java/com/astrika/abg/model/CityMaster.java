package com.astrika.abg.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.astrika.abg.model.User;

@Entity
@Audited
@Table(name="city_")
@EntityListeners(AuditingEntityListener.class)
public class CityMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long cityId;

	@Column(nullable = false, length = 75)
	private String cityName;

	@Column(columnDefinition = "boolean default true", nullable = false)
	private boolean active = true;


	@ManyToOne(fetch = FetchType.LAZY)
	private StateMaster state;
	
	@JsonIgnore
	@CreatedDate
	private Date createdOn;
	
	@JsonIgnore
	@LastModifiedDate
	private Date lastModifiedOn;

	@JsonIgnore
	@CreatedBy
	@ManyToOne(fetch = FetchType.LAZY)
	private User createdBy;

	@JsonIgnore
	@LastModifiedBy
	@ManyToOne(fetch = FetchType.LAZY)
	private User lastModifiedBy;

	public CityMaster() {
		super();
	}

	public CityMaster(String cityName, StateMaster state) {
		super();
		this.cityName = cityName;
		this.state = state;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getLastModifiedOn() {
		return lastModifiedOn;
	}

	public void setLastModifiedOn(Date lastModifiedOn) {
		this.lastModifiedOn = lastModifiedOn;
	}

	@JsonIgnore
	public User getCreatedBy() {
		return createdBy;
	}

	@JsonIgnore
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	@JsonIgnore
	public User getLastModifiedBy() {
		return lastModifiedBy;
	}

	@JsonIgnore
	public void setLastModifiedBy(User lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	
	public StateMaster getState() {
		return state;
	}

	public void setState(StateMaster state) {
		this.state = state;
	}

	
}
