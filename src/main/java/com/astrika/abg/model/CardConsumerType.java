package com.astrika.abg.model;

public enum CardConsumerType {
	EMPLOYEE(0,"Employee"),
	CUSTOMER(1,"Customer");
	
	private int id;
	private String name;
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	private CardConsumerType(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public static CardConsumerType fromInt(int id){
		
		switch(id){
		case 0:
			return EMPLOYEE;
		case 1:
			return CardConsumerType.CUSTOMER;
		default:
			return null;
		}
	}
}
