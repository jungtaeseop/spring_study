package com.springrecipe.sts.exceptionHandler.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestControllerAdvice
public class BaseExceptionHandlerConfig {
	private static final Logger logger = LoggerFactory.getLogger(BaseExceptionHandlerConfig.class);
	
	@ExceptionHandler({CustomException.class})
	protected ResponseEntity<ErrorCode> handleCustomException(CustomException ex){
		ErrorCode.;
		
		return new ResponseEntity<ErrorCode>(error);
	}
}
