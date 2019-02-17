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

/**
 * The base class for all exceptions related to business logic. Examples include
 * invalid input, servlet errors, and references to non existent database
 * records.
 *
 * <p>
 * Permission exceptions are generally caused by user error, and do not indicate
 * that anything is wrong with the application/server itself.
 * </p>
 *
 * @author Priyanka
 * @see    SystemException
 */
public class PermissionException extends NestableException {

	public PermissionException() {
		super();
	}

	public PermissionException(String msg) {
		super(msg);
	}

	public PermissionException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public PermissionException(Throwable cause) {
		super(cause);
	}

}