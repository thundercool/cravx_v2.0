
package com.astrika.abg.exception;

import com.astrika.abg.exception.BusinessException;
import com.astrika.abg.exception.CustomException;

public class NoSuchBrandException extends BusinessException {
	
	public NoSuchBrandException() {
		super(CustomException.NO_SUCH_BRAND.getCode());
	}

	public NoSuchBrandException(String errorCode) {
		super(errorCode);
	}

}