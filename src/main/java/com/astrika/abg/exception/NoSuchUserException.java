

package com.astrika.abg.exception;

/**
 * @author Priyanka
 */
public class NoSuchUserException extends Exception {

	public NoSuchUserException() {
		super("No such user exists");
	}

	public NoSuchUserException(String msg) {
		super(msg);
	}

	public NoSuchUserException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchUserException(Throwable cause) {
		super(cause);
	}

}