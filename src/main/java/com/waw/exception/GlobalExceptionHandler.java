package com.waw.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.waw.constant.Constant;
import com.waw.dto.ErrorDto;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(LoanRequestNotFoundException.class)
	public ResponseEntity<ErrorDto> loanRequestNotFoundException(LoanRequestNotFoundException e){
	ErrorDto errorDto = new ErrorDto();
	errorDto.setErrorMessage(e.getMessage());
	errorDto.setErrorCode(Constant.LOANREQUEST_NOT_FOUND);
	return new ResponseEntity<>(errorDto,HttpStatus.OK);
	}
	
	@ExceptionHandler(LoanRequestRuleNotFoundException.class)
	public ResponseEntity<ErrorDto> loanRequestRuleNotFoundException(LoanRequestRuleNotFoundException e){
	ErrorDto errorDto = new ErrorDto();
	errorDto.setErrorMessage(e.getMessage());
	errorDto.setErrorCode(Constant.LOANREQUESTRULE_NOT_FOUND);
	return new ResponseEntity<>(errorDto,HttpStatus.OK);
	}
	
	

}
