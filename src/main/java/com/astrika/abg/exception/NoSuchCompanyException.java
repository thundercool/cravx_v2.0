
package com.astrika.abg.exception;

import com.astrika.abg.exception.NoSuchModelException;
import com.astrika.abg.exception.CustomException;

public class NoSuchCompanyException extends NoSuchModelException {

	public NoSuchCompanyException() {
		super(CustomException.NO_SUCH_COMPANY.getCode());
	}

	public NoSuchCompanyException(String msg) {
		super(msg);
	}

	public NoSuchCompanyException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchCompanyException(Throwable cause) {
		super(cause);
	}

}