package com.astrika.abg.enums;

public enum SystemKeyEnum {
	DOMAIN_ALLOWED("DOMAIN_ALLOWED");
	
	
	
	private final String name;
	
	public String getName() {
		return name;
	}
	private SystemKeyEnum(String name) {
		this.name = name;
	}
	
	public static SystemKeyEnum  getSystemKeyEnum(String name) {
		switch (name) {
		case "DOMAIN_ALLOWED":
			return SystemKeyEnum.DOMAIN_ALLOWED;
		default:
			return null;
		}
	}
}
