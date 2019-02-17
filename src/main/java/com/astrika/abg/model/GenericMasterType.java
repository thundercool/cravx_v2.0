package com.astrika.abg.model;


public enum GenericMasterType {
	
	MARTIAL_STATUS(1,"Marital status"),
	GENDER(2,"Gender"),
	INDUSTRY_TYPE(3,"Industry Type"),
	SIZE(4,"Size"),
	TOPPINGS(5,"Toppings"),
	INGREDIENT(6,"Ingredient"),
	OTHERS(7,"Others");

	private final int id;
	private final String name;
	
	private GenericMasterType(int id,String name){
		this.id = id;
		this.name=name;
	}
	
	public int getId() {
		return id;
	}


	public String getName() {
		return name;
	}

	public static GenericMasterType fromInt(int id) {
		switch (id) {
		case 1:
			return MARTIAL_STATUS;
		case 2:
			return GENDER;
		case 3:
			return INDUSTRY_TYPE;
		case 4:
			return SIZE;
		case 5:
			return TOPPINGS;
		case 6:
			return INGREDIENT;
		case 7:
			return OTHERS;
		default:
			return null;
		}
	}
}
