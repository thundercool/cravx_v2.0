package com.astrika.abg.exception;

import com.astrika.abg.exception.BusinessException;
import com.astrika.abg.exception.CustomException;

public class DuplicateCorporateException extends BusinessException {
	
	
	public DuplicateCorporateException() {
		super(CustomException.DUPLICATE_CORPORATE.getCode());
	}

	public DuplicateCorporateException(String errorCode) {
		super(errorCode);
	}

}
