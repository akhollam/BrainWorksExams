package com.brainworksexams.exceptions;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.brainworksexams.models.ErrorDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
@RestController
public class ConstraintViolationExceptionHandler {

	@ExceptionHandler(DataIntegrityViolationException.class)
	public Object handleConstraintViolation(DataIntegrityViolationException e) {
		ErrorDto errorDto = new ErrorDto(e.getMessage());
		log.error("Exception - ", e);
		return new ResponseEntity<ErrorDto>(errorDto, HttpStatus.UNPROCESSABLE_ENTITY);
	}

}
