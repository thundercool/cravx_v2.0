package com.astrika.abg.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:handler.properties", ignoreResourceNotFound = true)
@ConfigurationProperties
public class HandlerValue {

	@Value("${email.newUserRegistration}")
	public String email_newUserRegistration;
	
	@Value("${context_url}")
	public String context_url;
	
	@Value("${pagination.start}")
	public int PAGINATION_SART;

	@Value("${pagination.end}")
	public int PAGINATION_END;

	// Image editing properties
	@Value("${image.validateAspectRatio}")
	public boolean IMAGE_VALIDATE_ASPECT;

	@Value("${image.isWatermarkToApply}")
	public boolean IMAGE_WATERMARK_TO_APPLY;

	@Value("${image.validateSize}")
	public boolean IMAGE_VALIDATE_FOR_COMRESS;

	@Value("${image.isChangeExtention}")
	public boolean IMAGE_CHANGE_EXTENTION;

	@Value("${image.createThumbnail}")
	public boolean IMAGE_CREATE_THUMBNAIL;

	@Value("${image.aspectRatioValueCompare}")
	public String IMAGE_ASPECT_RATIO_VALUE_COMPARE;

	@Value("${image.aspectRatioValueCompareOperator}")
	public String IMAGE_ASPECT_RATIO_VALUE_COMPARE_OPERATOR;

	// image change values

	@Value("${image.aspectRatioValue}")
	public String IMAGE_ASPECT_RATIO_VALUE_CHANGE;

	@Value("${image.watermarkValue}")
	public String IMAGE_WATERMARK_VALUE;

	@Value("${image.compressValue}")
	public long IMAGE_COMPRESS_VALUE;

	@Value("${image.compressQuality}")
	public float IMAGE_COMPRESS_QUALITY;

	@Value("${image.extentionValue}")
	public String IMAGE_EXTENTION_VALUE;

	@Value("${image.copyDest}")
	public String IMAGE_COPY_DEST_LOCATION;

	@Value("${image.thumbnailValues}")
	public String IMAGE_THUMBNAIL_VALUES;
	
	@Value("${image.saveRootDirName}")
	public String IMAGE_SAVE_ROOT_DIR;
	
	
	@Value("${root.url}")
	public String ROOT_URL;
	

	@Value("${context.url}")
	public String CONTEXT_URL;
	
	@Value("${website.name}")
	public String WEBSITE_NAME;

	@Value("${root.restpasswordurl}")
	public String RESET_PASSWPRD_URL;

	
//	@Value("${gourmet7Properties['g7.base.currency}")
//	public String BASE_CURRENCY;

	@Value("${member.firstCardNumber}")
	public String FIRST_CARD_NUMBER;
	
	@Value("${evite.eviteInvitation}")
	public String EVITE_INVITATION;
	
	@Value("${evite.eviteCancellation}")
	public String EVITE_CANCELLATION;
	
	@Value("${evite.eviteEdit}")
	public String EVITE_EDIT;
	
	@Value("${documents.root}")
	public String DOCUMENTS_ROOT;
	
	@Value("${documents.url}")
	public String DOCUMENT_URL;
	
	@Value("${promocode.timeDifference}")
	public long  PROMOCODE_TIME_DIFF;
	
	
	
	@Value("${base.currency}")
	public int BASE_CURRENCY;

	@Value("${pagination.interval}")
	public int PAGINATION_INTERVAL;
	
	@Value("${simple.dateformate}")
	public String SIMPLE_DATE_FORMAT;
	
	@Value("${simple.timeformate}")
	public String SIMPLE_TIME_FORMAT;
	
	@Value("${simple.datetimeformate}")
	public String SIMPLE_DATETIME_FORMAT;
	
	@Value("${simple.datetimezoneformate}")
	public String SIMPLE_DATE_TIMEZONE_FORMAT;
	
	@Value("${special.dateformate}")
	public String SPECIAL_DATE_FORMAT;
	
	@Value("${slash.dateformat}")
	public String DATE_FORMAT;
	
	
	
	@Value("${point.membershipDefaultValue}")
	public int MEMBERSHIP_DEFAULT_POINTVALUE;
	
	@Value("${point.membershipCancellationAmount}")
	public int MEMBERSHIP_CANCELLATION_VALUE;
	
	@Value("${point.restaurantDefaultValue}")
	public int RESTAURANT_DEFAULT_POINTVALUE;
	
	@Value("${imagesupportedformat}")
	public String IMAGE_SUPPORTED_FORMAT;
	
	@Value("${default.timezone}")
	public String DEFAULT_TIMEZONE;
	
	
	@Value("${notification.server.key}")
	public String NOTIFICATION_SERVER_KEY;
	
	@Value("${session.warning}")
	public int CONCURRENCY_SESSION_WARNING;
	
	
	@Value("${session.expire}")
	public int CONCURRENCY_SESSION_EXPIRE;
	
	@Value("${prefix.company}")
	public String COMPANY_LOGIN_PREFIX;
	
	@Value("${prefix.brand}")
	public String BRAND_LOGIN_PREFIX;
	
	@Value("${prefix.rest}")
	public String RESTAURANT_LOGIN_PREFIX;
	
	@Value("${prefix.corporate}")
	public String CORPORATE_LOGIN_PREFIX;
	
	@Value("${trial.tenure}")
	public String TRIAL_TENURE;
	
	@Value("${trial.cities}")
	public String TRIAL_CITIES;
	

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}

}
