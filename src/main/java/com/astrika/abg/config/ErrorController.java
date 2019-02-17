package com.astrika.abg.config;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.astrika.abg.exception.AccessDeniedException;


@ControllerAdvice
public class ErrorController {
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)  
    //@ExceptionHandler(value=Exception.class)
	public ModelAndView catchCustomException(Exception ex) {
		System.out.println("Reached Excpetion Class");
		ModelAndView model = new ModelAndView("errors/error_400");
		ex.getStackTrace();
		model.addObject("errorMessage", ex.getMessage());
		return model;
	}
	

	@ResponseStatus(HttpStatus.FORBIDDEN)  
 //   @ExceptionHandler(value=Exception.class)
	public String error401() {
		return "errors/error_401";
	}

	@ResponseStatus(HttpStatus.UNAUTHORIZED)  
  //  @ExceptionHandler(value=Exception.class)
	public String error403() {
		return "errors/error_403";
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)  
 //   @ExceptionHandler(value=Exception.class)
	public String error404(HttpServletResponse res) {
		return "errors/error_404";
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)  
	@ExceptionHandler(value=Exception.class)
	public ModelAndView internalServerError(Exception ex,HttpServletResponse res) {
		System.out.println("Reached INTERNAL_SERVER_ERROR Excpetion Class");
		System.out.println("Response "+res.getStatus());
		ModelAndView model = null;
		if(ex instanceof AccessDeniedException){
			model=new ModelAndView(viewName(401));
		}else {
			model=new ModelAndView(viewName(500));
		}
		ex.printStackTrace();
		model.addObject("errorMessage", ex.getMessage());
		return model;
	}

	@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)  
   // @ExceptionHandler(value=Exception.class)
	public String error503() {
		return "errors/error_503";
	}
	
	
	
	String viewName(int res) {
		System.out.println("Status Code : "+res);
		switch(res) {
		case 400:
			return "errors/error_400";
		case 401:
			return "errors/error_401";
		case 403:
			return "errors/error_403";
		case 404:
			return "errors/error_404";
		case 500:
			return "errors/error_500";
		case 503:
			return "errors/error_500";
		default:
			return "errors/error_401";
		}
	}
}
