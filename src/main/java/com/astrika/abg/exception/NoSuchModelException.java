/**
 * Copyright (c) 2014 Astrika, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.astrika.abg.exception;

import com.astrika.abg.exception.BusinessException;
import com.astrika.abg.exception.CustomException;

/**
 * @author Priyanka
 */
public class NoSuchModelException extends BusinessException {

	public NoSuchModelException() {
		super(CustomException.NO_SUCH_MODEL.getCode());
	}

	public NoSuchModelException(String msg) {
		super(msg);
	}

	public NoSuchModelException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchModelException(Throwable cause) {
		super(cause);
	}

}