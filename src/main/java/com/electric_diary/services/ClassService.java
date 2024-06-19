package com.electric_diary.services;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.electric_diary.entities.ClassEntity;

public interface ClassService {
	public ResponseEntity<?> createClass(ClassEntity classBody, BindingResult result);

	public ResponseEntity<?> getAllClasses();

	public ResponseEntity<?> getClassById(String id);

	public ResponseEntity<?> updateClass(String id, ClassEntity classBody);

	public ResponseEntity<?> deleteClass(String id);
}
