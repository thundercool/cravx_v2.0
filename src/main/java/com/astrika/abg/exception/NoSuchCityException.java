package com.astrika.abg.exception;

import com.astrika.abg.exception.CustomException;

public class NoSuchCityException extends NoSuchModelException {

	public NoSuchCityException() {
		super(CustomException.NO_SUCH_CITY.getCode());
	}

	public NoSuchCityException(String msg) {
		super(msg);
	}

	public NoSuchCityException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchCityException(Throwable cause) {
		super(cause);
	}

}