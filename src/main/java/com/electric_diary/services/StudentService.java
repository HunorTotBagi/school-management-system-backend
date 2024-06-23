package com.electric_diary.services;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.electric_diary.entities.StudentEntity;

public interface StudentService {
	public ResponseEntity<StudentEntity> createStudent(StudentEntity studentBody, BindingResult result);

	public ResponseEntity<Iterable<StudentEntity>> getAllStudents();

	public ResponseEntity<StudentEntity> getStudentById(String id);

	public ResponseEntity<StudentEntity> updateStudent(String id, StudentEntity studentBody);

	public ResponseEntity<StudentEntity> deleteStudent(String id);
}
