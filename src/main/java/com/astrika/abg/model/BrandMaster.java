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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Audited
@Table(name="brand_")
@EntityListeners(AuditingEntityListener.class)
public class BrandMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long brandId;

	@Column(nullable = false, length = 75)
	private String brandName;

	@Column( length = 75)
	private String brandAgreementStart;
	
	@Column( length = 75)
	private String  brandAgreementEnd;
	
	@Column
	private String agreementFileUlr;
	
	@Column
	private String agreementFileName;
	

	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	private CompanyMaster company;
	
	@JsonIgnore
	@OneToOne(fetch=FetchType.LAZY)
	private User brandAdmin;

	@Column(nullable = false, length = 75)
	private String brandTelephone;
	
	
	@Column( length = 75)
	private String brandEmail;
	
	@Column(length = 75)
	private String brandAddressLine1;
	
	@Column(length = 75)
	private String brandAddressLine2;
	
	
	@Column(nullable = false, length = 75)
	private String brandPincode;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private AreaMaster brandArea;
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	private CityMaster brandCity;

	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	private StateMaster brandState;

	@Column(columnDefinition = "boolean default true", nullable = false)
	private boolean active = true;

	@CreatedDate
	private Date createdOn;

	@LastModifiedDate
	private Date lastModifiedOn;

	@CreatedBy
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private User createdBy;

	@LastModifiedBy
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private User lastModifiedBy;
	

	public BrandMaster() {
		super();
	}
	
	


	public BrandMaster(String brandName, String brandAgreementStart, String brandAgreementEnd, String agreementFileUlr,
			String agreementFileName, CompanyMaster company, String brandTelephone, String brandEmail,
			String brandAddressLine1, String brandAddressLine2, String brandPincode, AreaMaster brandArea,
			CityMaster brandCity, StateMaster brandState, boolean active) {
		super();
		this.brandName = brandName;
		this.brandAgreementStart = brandAgreementStart;
		this.brandAgreementEnd = brandAgreementEnd;
		this.agreementFileUlr = agreementFileUlr;
		this.agreementFileName = agreementFileName;
		this.company = company;
		this.brandTelephone = brandTelephone;
		this.brandEmail = brandEmail;
		this.brandAddressLine1 = brandAddressLine1;
		this.brandAddressLine2 = brandAddressLine2;
		this.brandPincode = brandPincode;
		this.brandArea = brandArea;
		this.brandCity = brandCity;
		this.brandState = brandState;
		this.active = active;
	}




	public Long getBrandId() {
		return brandId;
	}


	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}


	public String getBrandName() {
		return brandName;
	}


	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}


	public String getBrandAgreementStart() {
		return brandAgreementStart;
	}


	public void setBrandAgreementStart(String brandAgreementStart) {
		this.brandAgreementStart = brandAgreementStart;
	}


	public String getBrandAgreementEnd() {
		return brandAgreementEnd;
	}


	public void setBrandAgreementEnd(String brandAgreementEnd) {
		this.brandAgreementEnd = brandAgreementEnd;
	}


	public String getAgreementFileUlr() {
		return agreementFileUlr;
	}


	public void setAgreementFileUlr(String agreementFileUlr) {
		this.agreementFileUlr = agreementFileUlr;
	}


	public String getAgreementFileName() {
		return agreementFileName;
	}


	public void setAgreementFileName(String agreementFileName) {
		this.agreementFileName = agreementFileName;
	}


	public CompanyMaster getCompany() {
		return company;
	}


	public void setCompany(CompanyMaster company) {
		this.company = company;
	}


	public User getBrandAdmin() {
		return brandAdmin;
	}


	public void setBrandAdmin(User brandAdmin) {
		this.brandAdmin = brandAdmin;
	}


	public String getBrandTelephone() {
		return brandTelephone;
	}


	public void setBrandTelephone(String brandTelephone) {
		this.brandTelephone = brandTelephone;
	}


	public String getBrandEmail() {
		return brandEmail;
	}


	public void setBrandEmail(String brandEmail) {
		this.brandEmail = brandEmail;
	}


	public String getBrandAddressLine1() {
		return brandAddressLine1;
	}


	public void setBrandAddressLine1(String brandAddressLine1) {
		this.brandAddressLine1 = brandAddressLine1;
	}


	public String getBrandAddressLine2() {
		return brandAddressLine2;
	}


	public void setBrandAddressLine2(String brandAddressLine2) {
		this.brandAddressLine2 = brandAddressLine2;
	}


	public String getBrandPincode() {
		return brandPincode;
	}


	public void setBrandPincode(String brandPincode) {
		this.brandPincode = brandPincode;
	}


	public AreaMaster getBrandArea() {
		return brandArea;
	}


	public void setBrandArea(AreaMaster brandArea) {
		this.brandArea = brandArea;
	}


	public CityMaster getBrandCity() {
		return brandCity;
	}


	public void setBrandCity(CityMaster brandCity) {
		this.brandCity = brandCity;
	}


	public StateMaster getBrandState() {
		return brandState;
	}


	public void setBrandState(StateMaster brandState) {
		this.brandState = brandState;
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

	
}
