package  com.astrika.abg.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.astrika.abg.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Audited
public class ImageMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long imageId;
	
	@Column(nullable = false,length = 75)
	private String imageName;
	
	@Column(unique = true, nullable = false,length = 200)
	private String imagePath;
	
	@Column(nullable = false,length = 75)
	private String extension;
	
	@Column(nullable = false,length = 75)
	private long size;
	
	@Column(columnDefinition = "boolean default true" , nullable = false)
	private boolean active=true;
	
	@Column(unique = true,nullable = true,length = 200)
	private String thumbImagePath;
	
	@Column(nullable = true,length = 250)
	private String description;
	
	@Column(unique = true,nullable = true,length = 200)
	private String mobileThumbImagePath;
	
	@Column(unique = true, nullable = true,length = 200)
	private String mobilePath;
	
	@Column(unique = true, nullable = true,length = 200)
	private String originalImagePath;
	
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
	
	@Column(nullable = true,length = 200)
	private String username;
	
	
	/*@OneToMany(fetch = FetchType.LAZY)
	private List<LikeMaster> follow;*/
	
	public String getOriginalImagePath() {
		return originalImagePath;
	}

	public void setOriginalImagePath(String originalImagePath) {
		this.originalImagePath = originalImagePath;
	}

	public String getMobilePath() {
		return mobilePath;
	}

	public void setMobilePath(String mobilePath) {
		this.mobilePath = mobilePath;
	}

	public String getMobileThumbImagePath() {
		return mobileThumbImagePath;
	}

	public void setMobileThumbImagePath(String mobileThumbImagePath) {
		this.mobileThumbImagePath = mobileThumbImagePath;
	}

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}


	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
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
	@JsonIgnore
	public User getLastModifiedBy() {
		return lastModifiedBy;
	}
	@JsonIgnore
	public void setLastModifiedBy(User lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public String getThumbImagePath() {
		return thumbImagePath;
	}

	public void setThumbImagePath(String thumbImagePath) {
		this.thumbImagePath = thumbImagePath;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
		
}
