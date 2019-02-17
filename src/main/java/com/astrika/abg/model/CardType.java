package com.astrika.abg.model;

public enum CardType {

	
	REGULAR(0,"Regular"),
	VIP(1,"Vip"),
	RESTAURNAT(2,"Restaurant");
	
	private int id;
	private String name;
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	private CardType(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public static CardType fromInt(int id){
		
		switch(id){
		case 0:
			return REGULAR;
		case 1:
			return VIP;
		case 2:
			return CardType.RESTAURNAT;
		default:
			return null;
		}
	}
}
