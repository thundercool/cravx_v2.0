package com.astrika.abg.enums;

public enum SystemNameEnum {
	MALE("MALE"),
	WEAK_PROFILE("WEAK PROFILE");


	private final String name;
	
	
	public String getName() {
		return name;
	}
	private SystemNameEnum(String name) {
		this.name = name;
	}

}
