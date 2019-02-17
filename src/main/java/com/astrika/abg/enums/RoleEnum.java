package com.astrika.abg.enums;

public enum RoleEnum {
	SUPER_ADMIN(1,"SUPERADMIN"),
	CRAVX_ADMIN(2,"CRAVXADMIN"),
	RESTAURANT_ADMIN(3,"RESTAURANTADMIN"),
	MANAGER(4,"MANAGER"),
	VAITOR(5,"VAITOR");

	private final int id;
	
	private final String name;

	private RoleEnum(int id,String name) {
		this.id = id;
		this.name=name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
