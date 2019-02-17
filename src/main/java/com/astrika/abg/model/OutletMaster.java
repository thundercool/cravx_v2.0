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
@Table(name="outlet_")
@EntityListeners(AuditingEntityListener.class)
public class OutletMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long outletId;
	
	@Column(nullable = false, length = 75)
	private String outletName;
	
	
	@Column( length = 75)
	private String outletAgreementStart;
	
	@Column( length = 75)
	private String  outletAgreementEnd;
	
	@Column
	private String agreementFileUlr;
	
	@Column
	private String agreementFileName;
	

	@Column(nullable = false, length = 75)
	private String outletTelephone;
	
	
	@Column( length = 75)
	private String outletEmail;
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	private CompanyMaster company;
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	private BrandMaster brand;
	
	@JsonIgnore
	@OneToOne(fetch=FetchType.LAZY)
	private User outletAdmin;
	
	@Column(length = 75)
	private String outletAddressLine1;
	
	@Column(length = 75)
	private String outletAddressLine2;
	
	
	@Column(nullable = false, length = 75)
	private String outletPincode;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private AreaMaster outletArea;
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	private CityMaster outletCity;

	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	private StateMaster outletState;

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
	
	@Column
	private int userCount;
	
	@Column(nullable = false)
	private boolean multiscreen = false;
	
	@Column(nullable = false)
	private boolean restaurantLoyalityCards = false;
	
	@Column(nullable = false)
	private boolean restaurantPersonalDeals = false;
	
	
	@Column(nullable = false)
	private boolean askForDiscountOnline = false;
	
	@Column(nullable = false)
	private boolean personalRecordManagement = false;
	
	
	@Column(nullable = false)
	private boolean userEngagement = false;
	
	
	

	public OutletMaster() {
		super();
	}

	
	
	public OutletMaster(String outletName, CompanyMaster company, BrandMaster brand, 
			String outletAddressLine1, String outletAddressLine2, String outletPincode, AreaMaster outletArea,
			CityMaster outletCity, StateMaster outletState, boolean active, int userCount, boolean multiscreen,
			boolean restaurantLoyalityCards, boolean restaurantPersonalDeals, boolean askForDiscountOnline,
			boolean personalRecordManagement, boolean userEngagement) {
		super();
		this.outletName = outletName;
		this.company = company;
		this.brand = brand;
		this.outletAdmin = outletAdmin;
		this.outletAddressLine1 = outletAddressLine1;
		this.outletAddressLine2 = outletAddressLine2;
		this.outletPincode = outletPincode;
		this.outletArea = outletArea;
		this.outletCity = outletCity;
		this.outletState = outletState;
		this.active = active;
		this.userCount = userCount;
		this.multiscreen = multiscreen;
		this.restaurantLoyalityCards = restaurantLoyalityCards;
		this.restaurantPersonalDeals = restaurantPersonalDeals;
		this.askForDiscountOnline = askForDiscountOnline;
		this.personalRecordManagement = personalRecordManagement;
		this.userEngagement = userEngagement;
	}



	public Long getOutletId() {
		return outletId;
	}

	public void setOutletId(Long outletId) {
		this.outletId = outletId;
	}

	public String getOutletName() {
		return outletName;
	}

	public void setOutletName(String outletName) {
		this.outletName = outletName;
	}

	public CompanyMaster getCompany() {
		return company;
	}

	public void setCompany(CompanyMaster company) {
		this.company = company;
	}

	public BrandMaster getBrand() {
		return brand;
	}

	public void setBrand(BrandMaster brand) {
		this.brand = brand;
	}

	public User getOutletAdmin() {
		return outletAdmin;
	}

	public void setOutletAdmin(User outletAdmin) {
		this.outletAdmin = outletAdmin;
	}

	public String getOutletAddressLine1() {
		return outletAddressLine1;
	}

	public void setOutletAddressLine1(String outletAddressLine1) {
		this.outletAddressLine1 = outletAddressLine1;
	}

	public String getOutletAddressLine2() {
		return outletAddressLine2;
	}

	public void setOutletAddressLine2(String outletAddressLine2) {
		this.outletAddressLine2 = outletAddressLine2;
	}

	public String getOutletPincode() {
		return outletPincode;
	}

	public void setOutletPincode(String outletPincode) {
		this.outletPincode = outletPincode;
	}

	public AreaMaster getOutletArea() {
		return outletArea;
	}

	public void setOutletArea(AreaMaster outletArea) {
		this.outletArea = outletArea;
	}

	public CityMaster getOutletCity() {
		return outletCity;
	}

	public void setOutletCity(CityMaster outletCity) {
		this.outletCity = outletCity;
	}

	public StateMaster getOutletState() {
		return outletState;
	}

	public void setOutletState(StateMaster outletState) {
		this.outletState = outletState;
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

	public int getUserCount() {
		return userCount;
	}

	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}

	public boolean isMultiscreen() {
		return multiscreen;
	}

	public void setMultiscreen(boolean multiscreen) {
		this.multiscreen = multiscreen;
	}

	public boolean isRestaurantLoyalityCards() {
		return restaurantLoyalityCards;
	}

	public void setRestaurantLoyalityCards(boolean restaurantLoyalityCards) {
		this.restaurantLoyalityCards = restaurantLoyalityCards;
	}

	public boolean isRestaurantPersonalDeals() {
		return restaurantPersonalDeals;
	}

	public void setRestaurantPersonalDeals(boolean restaurantPersonalDeals) {
		this.restaurantPersonalDeals = restaurantPersonalDeals;
	}

	public boolean isAskForDiscountOnline() {
		return askForDiscountOnline;
	}

	public void setAskForDiscountOnline(boolean askForDiscountOnline) {
		this.askForDiscountOnline = askForDiscountOnline;
	}

	public boolean isPersonalRecordManagement() {
		return personalRecordManagement;
	}

	public void setPersonalRecordManagement(boolean personalRecordManagement) {
		this.personalRecordManagement = personalRecordManagement;
	}

	public boolean isUserEngagement() {
		return userEngagement;
	}

	public void setUserEngagement(boolean userEngagement) {
		this.userEngagement = userEngagement;
	}



	public String getOutletAgreementStart() {
		return outletAgreementStart;
	}



	public void setOutletAgreementStart(String outletAgreementStart) {
		this.outletAgreementStart = outletAgreementStart;
	}



	public String getOutletAgreementEnd() {
		return outletAgreementEnd;
	}



	public void setOutletAgreementEnd(String outletAgreementEnd) {
		this.outletAgreementEnd = outletAgreementEnd;
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



	public String getOutletTelephone() {
		return outletTelephone;
	}



	public void setOutletTelephone(String outletTelephone) {
		this.outletTelephone = outletTelephone;
	}



	public String getOutletEmail() {
		return outletEmail;
	}



	public void setOutletEmail(String outletEmail) {
		this.outletEmail = outletEmail;
	}

	
	
}
