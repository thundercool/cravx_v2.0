package com.astrika.abg.exception;

import com.astrika.abg.exception.BusinessException;
import com.astrika.abg.exception.CustomException;


public class DuplicateLoginException extends BusinessException{
	
	
	public DuplicateLoginException() {
		super(CustomException.DUPLICATE_LOGIN.getCode());
	}

	public DuplicateLoginException(String errorCode){
		super(errorCode);
	}
}
