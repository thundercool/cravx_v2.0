package com.astrika.abg.exception;

import com.astrika.abg.exception.BusinessException;
import com.astrika.abg.exception.CustomException;

public class DuplicateCompanyException extends BusinessException {
	
	public DuplicateCompanyException() {
		super(CustomException.DUPLICATE_BRAND.getCode());
	}

	public DuplicateCompanyException(String errorCode) {
		super(errorCode);
	}

}
