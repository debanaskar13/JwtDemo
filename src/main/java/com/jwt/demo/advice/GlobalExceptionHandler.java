package com.jwt.demo.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private static GlobalExceptionHandler handler = null;

	@ExceptionHandler(Exception.class)
	public ProblemDetail handleException(Exception ex) {
		if (ex instanceof UsernameNotFoundException) {
			return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
		} else if (ex instanceof BadCredentialsException) {
			return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
		} else {
			return ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
		}
	}

	public static GlobalExceptionHandler getInstance() {
		handler = handler == null ? new GlobalExceptionHandler() : handler;
		return handler;
	}

}
