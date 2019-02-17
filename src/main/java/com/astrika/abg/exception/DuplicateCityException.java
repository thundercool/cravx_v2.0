package com.astrika.abg.exception;

import com.astrika.abg.exception.BusinessException;
import com.astrika.abg.exception.CustomException;

public class DuplicateCityException extends BusinessException{
	
	
	public DuplicateCityException() {
		super(CustomException.DUPLICATE_CITY.getCode());
	}

	public DuplicateCityException(String errorCode){
		super(errorCode);
	}
	 
}
