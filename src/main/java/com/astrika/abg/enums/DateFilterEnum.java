package com.astrika.abg.enums;

public enum DateFilterEnum { 	// Will be used for Date Filter in Application Listing.
	TODAY("TODAY"),
	YESTERDAY("YESTERDAY"),
	THIS_WEEK("THIS WEEK"),
	LAST_WEEK("LAST WEEK"),
	THIS_MONTH("THIS MONTH"),
	LAST_MONTH("LAST MONTH");
	
	private final String name;

	public String getName() {
		return name;
	}

	private DateFilterEnum(String name) {
		this.name = name;
	}


}
