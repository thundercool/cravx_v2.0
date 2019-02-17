package com.astrika.abg.exception;

import com.astrika.abg.exception.BusinessException;
import com.astrika.abg.exception.CustomException;

public class DuplicateGenericMasterException extends BusinessException{
	
	
	public DuplicateGenericMasterException() {
		super(CustomException.DUPLICATE_ADDRESS_CITY_EXCEPTION.getCode());
	}

	public DuplicateGenericMasterException(String errorCode){
		super(errorCode);
	}
	 
}
