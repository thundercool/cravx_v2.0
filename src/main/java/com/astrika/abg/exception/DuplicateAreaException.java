package com.astrika.abg.exception;

import com.astrika.abg.exception.BusinessException;
import com.astrika.abg.exception.CustomException;

public class DuplicateAreaException extends BusinessException{
	
	
	public DuplicateAreaException() {
		super(CustomException.DUPLICATE_AREA.getCode());
	}

	public DuplicateAreaException(String errorCode){
		super(errorCode);
	}
	 
}
