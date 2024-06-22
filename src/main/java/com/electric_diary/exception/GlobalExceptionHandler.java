package com.electric_diary.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({ ParentNotFoundException.class })
	public ResponseEntity<Object> handleParentNotFoundException(ParentNotFoundException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
	}

	@ExceptionHandler({ NumberFormatException.class })
	public ResponseEntity<Object> handleNumberFormatException(NumberFormatException exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid ID format.");
	}
}
