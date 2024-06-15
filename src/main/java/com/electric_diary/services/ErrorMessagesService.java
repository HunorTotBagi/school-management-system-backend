package com.electric_diary.services;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface ErrorMessagesService {
	public ResponseEntity<?> createNotFoundResponse(String entityName, int entityId);

	public ResponseEntity<?> createErrorResponse(Exception e);

	public ResponseEntity<?> createBadRequestResponse(String message);

	public String createErrorMessage(BindingResult result);
}