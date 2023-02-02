package com.springrecipe.sts.exceptionHandler.config;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
	
	SAVE_FAIL(404 ,"저장 실패하였습니다.");
	
	private int code;
	private String message;
}
