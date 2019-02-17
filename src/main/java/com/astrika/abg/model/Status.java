package com.astrika.abg.model;


public enum Status {
	INACTIVE(0),
	ACTIVE(1),
	AWAITING(2),
	APPROVED(3),
	REJECTED(4);
	
	private final int id;
	
	private Status(int id){
		this.id = id;
	}
	
	public int getId() {
		return id;
	}


	public static Status fromInt(int id) {
		switch (id) {
		case 0:
			return INACTIVE;
		case 1:
			return ACTIVE;
		case 2:
			return AWAITING;
		case 3:
			return APPROVED;
		case 4:
			return REJECTED;
		default:
			return null;
		}
	}
}