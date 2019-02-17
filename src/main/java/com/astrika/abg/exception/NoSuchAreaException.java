
package com.astrika.abg.exception;

import com.astrika.abg.exception.CustomException;


public class NoSuchAreaException extends NoSuchModelException {

	public NoSuchAreaException() {
		super(CustomException.NO_SUCH_AREA.getCode());
	}

	public NoSuchAreaException(String msg) {
		super(msg);
	}

	public NoSuchAreaException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchAreaException(Throwable cause) {
		super(cause);
	}

}