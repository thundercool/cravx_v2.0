package com.astrika.abg.exception;

import com.astrika.abg.exception.BusinessException;
import com.astrika.abg.exception.CustomException;


public class DuplicateMobileException extends BusinessException{
	
	
	public DuplicateMobileException() {
		super(CustomException.DUPLICATE_MOBILE_EXCEPTION.getCode());
	}

	public DuplicateMobileException(String errorCode){
		super(errorCode);
	}
}
