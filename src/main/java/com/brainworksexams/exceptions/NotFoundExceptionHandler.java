package com.brainworksexams.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.brainworksexams.models.ErrorDto;

@ControllerAdvice
public class NotFoundExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorDto> handle(Exception e) {
		
		ErrorDto errorDto = new ErrorDto(e.getMessage());
		return new ResponseEntity<ErrorDto>(errorDto, HttpStatus.NOT_FOUND);
	}

}
