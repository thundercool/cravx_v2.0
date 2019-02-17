package com.astrika.abg.exception;

public enum CustomException {

	RECORD_LOCK_EXCEPTION("000"),
	PASSWORD_EXCEPTION("001"),
	OLD_PASSWORD("002"),
	PASSWORD_LENGTH("003"),
	AUTHENTICATION_FAILURE("004"),
	USER_DEACTIVATED("005"),
	CAPTCHA_MISMATCH("006"),
	SESSION_EXPIRED_EXCEPTION("007"),
	SEND_PASSWORD_EXCEPTION("008"),
	UNIVERSITY_ADMIN_DEACTIVATED("009"),
	BRAND_ADMIN_DEACTIVATED("010"),
	REST_ADMIN_DEACTIVATED("011"),
	CORPORATE_ADMIN_DEACTIVATED("012"),
	COOKIE_NOT_SUPPORTED("021"),
	DUPLICATE_ADDRESS_CITY_EXCEPTION("031"),
	DUPLICATE_AREA("101"),
	NO_SUCH_STATE("102"),
	NO_SUCH_AREA("102"),
	DUPLICATE_CITY("110"),
	NO_SUCH_CITY("111"),
	NO_SUCH_CITY_WISE_RATE("112"),
	DUPLICATE_COUNTRY("120"),
	NO_SUCH_COUNTRY("121"),
	COUNTRY_NAME_EXCEPTION("122"),
	NO_SUCH_COUNTRY_WISE_RATE("123"),
	DUPLICATE_UNIVERSITY("130"),
	NO_SUCH_COMPANY("131"),
	DUPLICATE_DELETED_UNIVERSITY("132"),
	DUPLICATE_BRAND("133"),
	NO_SUCH_BRAND("134"),
	DUPLICATE_DELETED_BRAND("135"),
	NO_SUCH_BRAND_WISE_RATE("136"),
	DUPLICATE_CUISINE("140"),
	NO_SUCH_CUISINE("141"),
	DUPLICATE_EMAIL("150"),
	DUPLICATE_ADMIN_EMAIL("152"),
	DUPLICATE_LOGIN("151"),
	DOMAIN_EXCEPTION("153"),
	EMAIL_ADDRESS("154"),
	DUPLICATE_EXCHANGERATE("160"),
	NO_SUCH_EXCHANGERATE("161"),
	INVALID_EXCHANGERATE("162"),
	NO_SUCH_CURRENCY_EXCHANGE_RATE("163"),
	INVALID_RATE("164"),
	INVALID_MAX_RANGE("165"),
	DUPLICATE_CURRENCY("170"),
	NO_SUCH_CURRENCY("171"),
	DUPLICATE_CURRENCY_CODE("172"),
	NO_SUCH_LANGUAGE("181"),
	DUPLICATE_LANGUAGE("180"),
	DUPLICATE_LANGUAGE_CODE("182"),
	NO_SUCH_USER("190"),
	NO_SUCH_OFFER("200"),
	DUPLICATE_CORPORATE("210"),
	NO_SUCH_CORPORATE("211"),
	NO_SUCH_CORPORATE_CODE("212"),
	NO_SUCH_PROMOCODE_DESCRIPTION("220"),
	NO_SUCH_VOUCHER("230"),
	DUPLICATE_VOUCHER("231"),
	DISCOUNT_SLAB_OVERLAP("232"),
	NO_SUCH_COUNT_DISCOUNT("233"),
	VOUCHER_EXCEPTION("234"),
	DUPLICATE_AD_LOCATION("240"),
	NO_SUCH_AD_LOCATION("241"),
	DUPLICATE_INACTIVE_AD_LOCATION("242"),
	DUPLICATE_MENU_CATEGORY("250"),
	NO_SUCH_MEAL_MENU("251"),
	NO_SUCH_MENU_CATEGORY("252"),
	NO_SUCH_MENU_IMAGE("253"),
	NO_SUCH_MENU_ITEM("254"),
	NO_SUCH_EVITETEMPLATE("260"),
	FILE_EXTENSION_NOT_SUPPORTED("270"),
	PROMOCODE_EXTENSION_NOT_SUPPORTED("271"),
	NO_SUCH_PROMOCODE("272"),
	FILE_SIZE_EXCEPTION("273"),
	DUPLICATE_FILE_NAME("274"),
	BUY_MEMBERSHIP_TO_AVAIL_THIS_SERVICE("300"),
	REST_CUSTOMER_NOT_CONFIRMED("301"),
	REST_CUSTOMER_CHECKED_OUT("302"),
	REST_TAB_UNAVAILABLE("303"),
	DUPLICATE_USER_ID("401"),
	USER_ACTIVE_EXCEPTION("402"),
	USER_PORTRAIT_SIZE_EXCEPTION("403"),
	USER_PORTRAIT_TYPE_EXCEPTION("404"),
	USER_ID_EXCEPTION("405"),
	USER_EMAIL_ADDRESS_EXCEPTION("406"),
	WEBSITE_URL_EXCEPTION("431"),
	NO_SUCH_WEBSITE("432"),
	PHONE_NUMBER_EXCEPTION("451"),
	NO_SUCH_PHONE("452"),
	TERMS_OF_USE_EXCEPTION("501"),
	INVALID_RANGE("521"),
	NO_SUCH_MODEL("541"),
	NO_SUCH_IMAGE("551"),
	LOCALE_EXCEPTION("561"),
	DUPLICATE_POLL("581"),
	NO_SUCH_POLL("582"),
	NO_SUCH_POLL_CHOICE("583"),
	NO_SUCH_POLL_VOTE("584"),
	NO_SUCH_MEMBER("601"),
	NO_SUCH_TENURE_DISCOUNT_SLAB("611"),
	DUPLICATE_OUTLET("651"),
	NO_SUCH_OUTLET("652"),
	NO_SUCH_RESTAURANT("653"),
	NO_SUCH_OUTLET_RATING("654"),
	NO_SUCH_FEEDBACK("681"),
	GMAIL_AUTHENTICATION_FAILURE("670"),
	DUPLICATE_CONTACT("690"),
	DUPLICATE_COURSE_CATEGORY("10001"),
	DUPLICATE_COURSE_CATEGORY_CODE("10002"),
	NO_SUCH_COURSE_CATEGORY("10003"),
	DUPLICATE_COURSE("10004"),
	DUPLICATE_DELETED_COURSE("10005"),
	DUPLICATE_DEPARTMENT("10100"),
	DUPLICATE_STATE("10101"),
	USERID_STATUS_PENDING("10102"),
	USERID_INVITE_SENT("10103"),
	NO_SUCH_EVENT("10104"),
	DUPLICATE_EVENT_EXCPETION("10105"),
	DUPLICATE_ITINEARY_EXCPETION("10106"),
	NO_SUCH_ITINEARY_EXCEPTION("10107"),
	DUPLICATE_DOCUMENT_EXCPETION("10108"),
	NO_SUCH_DOCUMENT_EXCEPTION("10109"),
	DUPLICATE_MOBILE_EXCEPTION("101010"),
	OTP("101011"),
	OTP_EXP("101016"),
	NO_SUCH_MOBILE_EXCEPTION("101012"),
	DUPLICATE_USER_EXCEPTION("101013"),
	DUPLICATE_HOTEL_EXCEPTION("101014"),
	INVALID_FILE_EXCEPTION("101015");
	
	
	private final String code;
	
	private CustomException(String code){
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
}
