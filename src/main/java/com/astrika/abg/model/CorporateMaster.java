package com.astrika.abg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.envers.Audited;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Audited
@Table(name="corporate_")
@EntityListeners(AuditingEntityListener.class)
public class CorporateMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long corporateId;

	@Column( nullable = false, length = 75)
	private String corporateName;

	@Column(length = 75)
	private String compAgreementStart;

	@Column(length = 75)
	private String compAgreementEnd;
	
	
	@Column(columnDefinition = "boolean default true", nullable = false)
	private boolean active = true;
		
	@Column
	private String agreementFileUlr;

	@Column
	private String agreementFileName;
	
	@Column
	private String corporateDocumentFileUrl;

	@Column
	private String corporateDocumentFileName;
	
	@Transient
	private MultipartFile logo;
	

	
	
	@OneToOne
	private ImageMaster logoImage;

	@Column( nullable = false, length = 75)
	private String corporateTelephone;
	
	@Column(nullable = false, length = 75)
	private String corporateEmail;


	@Column( nullable = false, length = 75)
	private String corporateAddressLine1;

	@Column
	private String corporateAddressLine2;

	@Column(nullable = false, length = 75)
	private String corporatePincode;
	
	@Column(length = 500)
	private String rejectReason;

	@ManyToOne(fetch =FetchType.LAZY)
	private AreaMaster corporateArea;

	@ManyToOne(fetch =FetchType.LAZY)
	private CityMaster corporateCity;

	@ManyToOne(fetch =FetchType.LAZY)
	private StateMaster corporateState;

	@OneToOne(fetch=FetchType.LAZY)
	private User corporateAdmin;

	@Column(nullable = false)
	private int noOfUsers;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private GenericMaster industryType;
	
	@Enumerated(EnumType.ORDINAL)
	private Status status;
	
	
	public CorporateMaster() {
		super();
	}

	public CorporateMaster(String corporateName,boolean active, String compAgreementStart, String compAgreementEnd,
			String agreementFileUlr, String corporateTelephone,
			String corporateEmail, String corporateAddressLine1, String corporateAddressLine2, String corporatePincode,
			AreaMaster corporateArea, CityMaster corporateCity, StateMaster corporateState, User corporateAdmin,
			int noOfUsers) {
		super();
		this.corporateName = corporateName;
		this.active=active;
		this.compAgreementStart = compAgreementStart;
		this.compAgreementEnd = compAgreementEnd;
		this.agreementFileUlr = agreementFileUlr;
		this.corporateTelephone = corporateTelephone;
		this.corporateEmail = corporateEmail;
		this.corporateAddressLine1 = corporateAddressLine1;
		this.corporateAddressLine2 = corporateAddressLine2;
		this.corporatePincode = corporatePincode;
		this.corporateArea = corporateArea;
		this.corporateCity = corporateCity;
		this.corporateState = corporateState;
		this.corporateAdmin = corporateAdmin;
		this.noOfUsers = noOfUsers;
	}

	public Long getCorporateId() {
		return corporateId;
	}

	public void setCorporateId(Long corporateId) {
		this.corporateId = corporateId;
	}

	public String getCorporateName() {
		return corporateName;
	}

	public void setCorporateName(String corporateName) {
		this.corporateName = corporateName;
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

	public String getCorporateDocumentFileUrl() {
		return corporateDocumentFileUrl;
	}

	public void setCorporateDocumentFileUrl(String corporateDocumentFileUrl) {
		this.corporateDocumentFileUrl = corporateDocumentFileUrl;
	}

	public String getCorporateDocumentFileName() {
		return corporateDocumentFileName;
	}

	public void setCorporateDocumentFileName(String corporateDocumentFileName) {
		this.corporateDocumentFileName = corporateDocumentFileName;
	}

	public MultipartFile getLogo() {
		return logo;
	}

	public void setLogo(MultipartFile logo) {
		this.logo = logo;
	}

	public ImageMaster getLogoImage() {
		return logoImage;
	}

	public void setLogoImage(ImageMaster logoImage) {
		this.logoImage = logoImage;
	}

	public String getCorporateTelephone() {
		return corporateTelephone;
	}

	public void setCorporateTelephone(String corporateTelephone) {
		this.corporateTelephone = corporateTelephone;
	}

	public String getCorporateEmail() {
		return corporateEmail;
	}

	public void setCorporateEmail(String corporateEmail) {
		this.corporateEmail = corporateEmail;
	}

	public String getCorporateAddressLine1() {
		return corporateAddressLine1;
	}

	public void setCorporateAddressLine1(String corporateAddressLine1) {
		this.corporateAddressLine1 = corporateAddressLine1;
	}

	public String getCorporateAddressLine2() {
		return corporateAddressLine2;
	}

	public void setCorporateAddressLine2(String corporateAddressLine2) {
		this.corporateAddressLine2 = corporateAddressLine2;
	}

	public String getCorporatePincode() {
		return corporatePincode;
	}

	public void setCorporatePincode(String corporatePincode) {
		this.corporatePincode = corporatePincode;
	}

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	public AreaMaster getCorporateArea() {
		return corporateArea;
	}

	public void setCorporateArea(AreaMaster corporateArea) {
		this.corporateArea = corporateArea;
	}

	public CityMaster getCorporateCity() {
		return corporateCity;
	}

	public void setCorporateCity(CityMaster corporateCity) {
		this.corporateCity = corporateCity;
	}

	public StateMaster getCorporateState() {
		return corporateState;
	}

	public void setCorporateState(StateMaster corporateState) {
		this.corporateState = corporateState;
	}

	public User getCorporateAdmin() {
		return corporateAdmin;
	}

	public void setCorporateAdmin(User corporateAdmin) {
		this.corporateAdmin = corporateAdmin;
	}

	public int getNoOfUsers() {
		return noOfUsers;
	}

	public void setNoOfUsers(int noOfUsers) {
		this.noOfUsers = noOfUsers;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public GenericMaster getIndustryType() {
		return industryType;
	}

	public void setIndustryType(GenericMaster industryType) {
		this.industryType = industryType;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	

}
