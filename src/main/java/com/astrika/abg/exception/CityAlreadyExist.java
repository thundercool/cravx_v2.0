package com.astrika.abg.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.METHOD_NOT_ALLOWED, reason="A City with same name already exist")
public class CityAlreadyExist extends RuntimeException{

	private static final long serialVersionUID = 1L;
	 
}
