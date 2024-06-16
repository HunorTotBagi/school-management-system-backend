package com.electric_diary.services;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.electric_diary.entities.StudentEntity;

public interface StudentService {
	public ResponseEntity<?> createStudent(StudentEntity studentBody, BindingResult result);

	public ResponseEntity<?> getAllStudents();

	public ResponseEntity<?> getStudentById(String id);

	public ResponseEntity<?> updateStudent(String id, StudentEntity studentBody);

	public ResponseEntity<?> deleteStudent(String id);
}
