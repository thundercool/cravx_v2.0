
package com.astrika.abg.exception;

import com.astrika.abg.exception.BusinessException;
import com.astrika.abg.exception.CustomException;
public class FileNameException extends BusinessException {

	

	public FileNameException() {
		super(CustomException.DUPLICATE_FILE_NAME.getCode());
	}

	public FileNameException(String msg) {
		super(msg);
	}

	public FileNameException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public FileNameException(Throwable cause) {
		super(cause);
	}

}