package com.springrecipe.sts.exceptionHandler.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomException extends RuntimeException{
	private final ErrorCode errorCode;
}
