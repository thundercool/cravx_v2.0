package com.astrika.abg.exception;

import com.astrika.abg.exception.BusinessException;
import com.astrika.abg.exception.CustomException;

public class DuplicateBrandException extends BusinessException {
	
	public DuplicateBrandException() {
		super(CustomException.DUPLICATE_BRAND.getCode());
	}

	public DuplicateBrandException(String errorCode) {
		super(errorCode);
	}

}
