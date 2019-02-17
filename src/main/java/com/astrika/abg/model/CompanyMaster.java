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
@Table(name="company_")
@EntityListeners(AuditingEntityListener.class)
public class CompanyMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long companyId;

	@Column(unique = true, nullable = false, length = 75)
	private String companyName;
	
	
	@Column( length = 75)
	private String compAgreementStart;
	
	@Column( length = 75)
	private String  compAgreementEnd;
	
	@Column
	private String agreementFileUlr;
	
	@Column
	private String agreementFileName;
	
	//contact info
	

	@Column(length = 75)
	private String companyTelephone;
	

	
	@Column(length = 75)
	private String companyEmail;
	
	
	@Column( nullable = false, length = 75)
	private String companyAddressLine1;
	
	@Column
	private String companyAddressLine2;
	
	@Column(nullable = false, length = 75)
	private String companyPincode;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private AreaMaster companyArea;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private CityMaster companyCity;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private StateMaster companyState;

	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	private User companyAdmin;
	
	@Column(columnDefinition = "boolean default true", nullable = false)
	private boolean active = true;

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
	
	public CompanyMaster() {
		super();
	}
	
	

	public CompanyMaster(String companyName, String compAgreementStart, String compAgreementEnd,
			String agreementFileUlr, String agreementFileName, String companyTelephone, String companyEmail,
			String companyAddressLine1, String companyAddressLine2, String companyPincode, AreaMaster companyArea,
			CityMaster companyCity, StateMaster companyState) {
		super();
		this.companyName = companyName;
		this.compAgreementStart = compAgreementStart;
		this.compAgreementEnd = compAgreementEnd;
		this.agreementFileUlr = agreementFileUlr;
		this.agreementFileName = agreementFileName;
		this.companyTelephone = companyTelephone;
		this.companyEmail = companyEmail;
		this.companyAddressLine1 = companyAddressLine1;
		this.companyAddressLine2 = companyAddressLine2;
		this.companyPincode = companyPincode;
		this.companyArea = companyArea;
		this.companyCity = companyCity;
		this.companyState = companyState;
	}



	

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompAgreementStart() {
		return compAgreementStart;
	}

	public void setCompAgreementStart(String compAgreementStart) {
		this.compAgreementStart = compAgreementStart;
	}

	public String getCompAgreementEnd() {
		return compAgreementEnd;
	}

	public void setCompAgreementEnd(String compAgreementEnd) {
		this.compAgreementEnd = compAgreementEnd;
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

	public String getCompanyTelephone() {
		return companyTelephone;
	}

	public void setCompanyTelephone(String companyTelephone) {
		this.companyTelephone = companyTelephone;
	}

	public String getCompanyEmail() {
		return companyEmail;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	public String getCompanyAddressLine1() {
		return companyAddressLine1;
	}

	public void setCompanyAddressLine1(String companyAddressLine1) {
		this.companyAddressLine1 = companyAddressLine1;
	}

	public String getCompanyAddressLine2() {
		return companyAddressLine2;
	}

	public void setCompanyAddressLine2(String companyAddressLine2) {
		this.companyAddressLine2 = companyAddressLine2;
	}

	public String getCompanyPincode() {
		return companyPincode;
	}

	public void setCompanyPincode(String companyPincode) {
		this.companyPincode = companyPincode;
	}

	public AreaMaster getCompanyArea() {
		return companyArea;
	}

	public void setCompanyArea(AreaMaster companyArea) {
		this.companyArea = companyArea;
	}

	public CityMaster getCompanyCity() {
		return companyCity;
	}

	public void setCompanyCity(CityMaster companyCity) {
		this.companyCity = companyCity;
	}
	
	

	public StateMaster getCompanyState() {
		return companyState;
	}

	public void setCompanyState(StateMaster companyState) {
		this.companyState = companyState;
	}

	public User getCompanyAdmin() {
		return companyAdmin;
	}

	public void setCompanyAdmin(User companyAdmin) {
		this.companyAdmin = companyAdmin;
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
