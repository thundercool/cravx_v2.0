package com.astrika.abg.exception;

import com.astrika.abg.exception.BusinessException;
import com.astrika.abg.exception.CustomException;

public class DuplicateStateException extends BusinessException{
	
	
	public DuplicateStateException() {
		super(CustomException.DUPLICATE_STATE.getCode());
	}

	public DuplicateStateException(String errorCode){
		super(errorCode);
	}
	 
}
