package com.astrika.abg.exception;

public class AccessDeniedException extends Exception{
	

	public AccessDeniedException() {
		super("Access Denied");
	}

	public AccessDeniedException(String errorCode){
		super(errorCode);
	}
	
}