package com.astrika.abg.exception;

import com.astrika.abg.exception.BusinessException;
import com.astrika.abg.exception.CustomException;

public class NoSuchCorporateException extends BusinessException {
	
	public NoSuchCorporateException() {
		super(CustomException.NO_SUCH_CORPORATE.getCode());
	}

	public NoSuchCorporateException(String errorCode) {
		super(errorCode);
	}

}
