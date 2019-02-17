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

import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.astrika.abg.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Audited
@Table(name="area_")
@EntityListeners(AuditingEntityListener.class)
public class AreaMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long areaId;

	@Column(nullable = false, length = 75)
	private String areaName;

	@Column(columnDefinition = "boolean default true", nullable = false)
	private boolean active = true;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private CityMaster city;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private StateMaster state;

	@CreatedDate
	private Date createdOn;

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

	@Column(nullable = false)
	private double latitude;

	@Column(nullable = false)
	private double longitude;

	public AreaMaster() {
		super();
	}

	public AreaMaster(String areaName, CityMaster city,StateMaster state
			,double latitude, double longitude) {
		super();
		this.areaName = areaName;
		this.city = city;
		this.state=state;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
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
	public User getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}
	public User getLastModifiedBy() {
		return lastModifiedBy;
	}
	public void setLastModifiedBy(User lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public CityMaster getCity() {
		return city;
	}

	public void setCity(CityMaster city) {
		this.city = city;
	}
	
	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public StateMaster getState() {
		return state;
	}

	public void setState(StateMaster state) {
		this.state = state;
	}
	
	

}
