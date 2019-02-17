package com.astrika.abg.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.astrika.abg.model.ImageMaster;
import com.astrika.abg.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Audited
@Table(name="cardmaster_")
@EntityListeners(AuditingEntityListener.class)
public class CardMaster {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long cardId;
	
	@Type(type = "org.hibernate.type.NumericBooleanType")
	@Column
	private Boolean assigned = false;
	
	@Type(type = "org.hibernate.type.NumericBooleanType")
	@Column
	private Boolean active = false;
	
	@Enumerated(EnumType.ORDINAL)
	private CardType cardType;
	
	@Enumerated(EnumType.ORDINAL)
	private CardConsumerType cardConsumerType;
	
	@Column
	private Long cardNumber;
	
	@OneToOne(fetch=FetchType.LAZY)
	private ImageMaster qrImage;
	
/*	@OneToMany(fetch=FetchType.LAZY)
	private CorporateMaster corporate;*/
	
	@JsonIgnore
	@CreatedBy
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn( updatable=false)
	private User createdBy;

	@JsonIgnore
	@LastModifiedBy
	@ManyToOne(fetch = FetchType.LAZY)
	private User lastModifiedBy;

	@CreatedDate
	private Date createdOn;

	@LastModifiedDate
	private Date lastModifiedOn;
	
	public CardMaster() {
		super();
	}

	public Long getCardId() {
		return cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	public Boolean getAssigned() {
		return assigned;
	}

	public void setAssigned(Boolean assigned) {
		this.assigned = assigned;
	}

	public CardType getCardType() {
		return cardType;
	}

	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}

	public CardConsumerType getCardConsumerType() {
		return cardConsumerType;
	}

	public void setCardConsumerType(CardConsumerType cardConsumerType) {
		this.cardConsumerType = cardConsumerType;
	}

	public Long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(Long cardNumber) {
		this.cardNumber = cardNumber;
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

	public ImageMaster getQrImage() {
		return qrImage;
	}

	public void setQrImage(ImageMaster qrImage) {
		this.qrImage = qrImage;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

/*	public CorporateMaster getCorporate() {
		return corporate;
	}

	public void setCorporate(CorporateMaster corporate) {
		this.corporate = corporate;
	}*/
	
	
	

}
