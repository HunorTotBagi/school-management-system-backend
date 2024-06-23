package com.electric_diary.services;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.electric_diary.entities.ClassEntity;

public interface ClassService {
	public ResponseEntity<ClassEntity> createClass(ClassEntity classBody, BindingResult result);

	public ResponseEntity<Iterable<ClassEntity>> getAllClasses();

	public ResponseEntity<ClassEntity> getClassById(String id);

	public ResponseEntity<ClassEntity> updateClass(String id, ClassEntity classBody);

	public ResponseEntity<ClassEntity> deleteClass(String id);
}
