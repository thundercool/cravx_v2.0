package com.astrika.abg.exception;

import com.astrika.abg.exception.BusinessException;
import com.astrika.abg.exception.CustomException;


public class DuplicateEmailException extends BusinessException{
	
	
	public DuplicateEmailException() {
		super(CustomException.DUPLICATE_EMAIL.getCode());
	}

	public DuplicateEmailException(String errorCode){
		super(errorCode);
	}
}
