package com.electric_diary.services.impl;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.electric_diary.controllers.util.RESTError;
import com.electric_diary.services.ErrorMessagesService;

public class ErrorMessagesServiceImpl implements ErrorMessagesService {

	@Override
	public ResponseEntity<?> createNotFoundResponse(String entityName, int entityId) {
		return new ResponseEntity<>(new RESTError(1, String.format("%s with ID %s not found", entityName, entityId)), HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<?> createErrorResponse(Exception e) {
		return new ResponseEntity<>(new RESTError(2, "Exception occurred: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<?> createBadRequestResponse(String message) {
		return new ResponseEntity<>(new RESTError(3, message), HttpStatus.BAD_REQUEST);
	}

	@Override
	public String createErrorMessage(BindingResult result) {
		return result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(" "));
	}
}
